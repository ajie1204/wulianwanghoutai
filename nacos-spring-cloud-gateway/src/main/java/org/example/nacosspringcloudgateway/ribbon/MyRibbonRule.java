package org.example.nacosspringcloudgateway.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;



//@Component
@Slf4j
public class MyRibbonRule extends RoundRobinRule {


    String name = "unknown";
    protected AtomicBoolean serverWeightAssignmentInProgress = new AtomicBoolean(false);
    protected Timer serverWeightTimer = null;
    public static final int DEFAULT_TIMER_INTERVAL = 00 * 1000;
    private int serverWeightTaskTimerInterval = DEFAULT_TIMER_INTERVAL;

    private volatile List<Double> accumulatedWeights = new ArrayList<Double>();


    @Override
    public void setLoadBalancer(ILoadBalancer lb){
        super.setLoadBalancer(lb);
        if (lb instanceof BaseLoadBalancer) {
            name = ((BaseLoadBalancer) lb).getName();
        }
        initialize(lb);
    }

    void initialize(ILoadBalancer lb) {
        if (serverWeightTimer != null) {
            serverWeightTimer.cancel();
        }
        serverWeightTimer = new Timer("NFLoadBalancer-serverWeightTimer-"
                + name, true);
        serverWeightTimer.schedule(new MyRibbonRule.DynamicServerWeightTask(), 0,
                serverWeightTaskTimerInterval);
        // do a initial run
        MyRibbonRule.ServerWeight sw = new MyRibbonRule.ServerWeight();
        sw.maintainWeights();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                log
                        .info("Stopping NFLoadBalancer-serverWeightTimer-"
                                + name);
                serverWeightTimer.cancel();
            }
        }));
    }

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            log.warn("no load balancer");
            return null;
        }

        Server server = null;
        int count = 0;
        while (server == null && count++ < 10) {
            List<Server> reachableServers = lb.getReachableServers();
            List<Server> allServers = lb.getAllServers();
            //活着的服务实例个数
            int upCount = reachableServers.size();
            //总的实例个数
            int serverCount = allServers.size();

            if ((upCount == 0) || (serverCount == 0)) {
                log.warn("No up servers available from load balancer: " + lb);
                return null;
            }

            if (server == null) {
                /* Transient. */
                Thread.yield();
                continue;
            }

            if (server.isAlive() && (server.isReadyToServe())) {
                return (server);
            }

            // Next.
            server = null;
        }

        if (count >= 10) {
            log.warn("No available alive servers after 10 tries from load balancer: "
                    + lb);
        }
        return server;
    }

    @Override
    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    class DynamicServerWeightTask extends TimerTask {
        public void run() {
            MyRibbonRule.ServerWeight serverWeight = new MyRibbonRule.ServerWeight();
            try {
                serverWeight.maintainWeights();
            } catch (Exception e) {
                log.error("Error running DynamicServerWeightTask for {}", name, e);
            }
        }
    }


    class ServerWeight {

        public void maintainWeights() {
            ILoadBalancer lb = getLoadBalancer();
            if (lb == null) {
                return;
            }

            if (!serverWeightAssignmentInProgress.compareAndSet(false,  true))  {
                return;
            }

            try {
                //获取总的服务和活着的服务
                List<Server> allServers = lb.getAllServers();
                List<Server> reachableServers  = lb.getReachableServers();

            } catch (Exception e) {
                log.error("Error calculating server weights", e);
            } finally {
                serverWeightAssignmentInProgress.set(false);
            }

        }
    }

    void setWeights(List<Double> weights) {
        this.accumulatedWeights = weights;
    }
}
