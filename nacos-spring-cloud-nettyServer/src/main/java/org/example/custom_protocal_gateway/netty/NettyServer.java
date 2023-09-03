package org.example.custom_protocal_gateway.netty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class NettyServer  {
    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);
    ServerBootstrap bootstrap = new ServerBootstrap();
    private EventLoopGroup boss = new NioEventLoopGroup(1);// 通过nio方式来接收连接和处理连接
    private EventLoopGroup workerGroup = new NioEventLoopGroup(2);
    int tcp_port = 8082;
    int http_port = 9001;
    int mqtt_port = 8090;
    ChannelFuture future = null;



    @PostConstruct
    public void start(){
        System.out.println("nettyserver");

        bootstrap.group(boss, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .option(ChannelOption.SO_REUSEADDR,true)
                /*.option(EpollChannelOption.SO_REUSEPORT, true)*/
                /*.handler(new LoggingHandler(LogLevel.INFO))
                .option(ChannelOption.TCP_NODELAY,true)*/
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .childHandler(new NettyServerInitializer());
        try{
            ChannelFuture future1 = bootstrap.bind(tcp_port).sync();
            ChannelFuture future2 = bootstrap.bind(http_port).sync();
            ChannelFuture future3 = bootstrap.bind(mqtt_port).sync();
            future1.channel().closeFuture().addListener(new ChannelFutureListener()
            {
                @Override public void operationComplete(ChannelFuture future) throws Exception
                {       //通过回调只关闭自己监听的channel
                    future.channel().close();
                }
            });
            future2.channel().closeFuture().addListener(new ChannelFutureListener()
            {
                @Override public void operationComplete(ChannelFuture future2) throws Exception
                {
                    future2.channel().close();
                }
            });
            future3.channel().closeFuture().addListener(new ChannelFutureListener()
            {
                @Override public void operationComplete(ChannelFuture future3) throws Exception
                {
                    future3.channel().close();
                }
            });


            // 等待服务端监听端口关闭
            // future1.channel().closeFuture().sync();
        }catch (Exception e){
            logger.info("nettyServer 启动时发生异常---------------{}",e);
            logger.info(e.getMessage());
        }finally {
            //这里一定要注释，因为上面没有阻塞了，不注释的话，这里会直接关闭的
            //boss.shutdownGracefully();
            //worker.shutdownGracefully();

        }
    }

  /*  @Async//注意这里，组件启动时会执行run，这个注解是让线程异步执行，这样不影响主线程
    @Override
    public void run(String... args) {
        start();
    }*/

    @PreDestroy
    public void stop(){
        if(future!=null){
            future.channel().close().addListener(ChannelFutureListener.CLOSE);
            future.awaitUninterruptibly();
            boss.shutdownGracefully();
            workerGroup.shutdownGracefully();
            future=null;
            logger.info(" 服务关闭 ");
        }
    }
}
