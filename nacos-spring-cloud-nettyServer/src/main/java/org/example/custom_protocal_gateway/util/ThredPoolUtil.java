package org.example.custom_protocal_gateway.util;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

public class ThredPoolUtil {
	
    private ExecutorService messageExecutor;//消息事件业务处理线程池
    private ExecutorService channelExecutor;//通道事件业务处理线程池
    private ExecutorService exceptionExecutor;//异常事件业务处理线程池
    private int corePoolSize = 10;//消息事件业务处理线程池最小保持的线程数
    private int maximumPoolSize = 150;//消息事件业务处理线程池最大线程数
    private int queueCapacity = 1000000;//消息事件业务处理线程池队列最大值
    
    private static ThredPoolUtil thredPool;

    public static ThredPoolUtil create() {
    	if(thredPool==null) {
    		thredPool=new ThredPoolUtil();
    		thredPool.createThredPool();
    	}
    	return thredPool;
    }
    
    //创建线程池
    private void createThredPool() {
    	try {
    		if(messageExecutor==null) {
                messageExecutor = new ThreadPoolExecutor(
                        this.corePoolSize,
                        this.maximumPoolSize,
                        60L,
                        TimeUnit.SECONDS,
                        new LinkedBlockingQueue<Runnable>(this.queueCapacity),
                        new BasicThreadFactory.Builder().namingPattern("MessageEventProcessor-%d").daemon(true).build(),
                        new ThreadPoolExecutor.AbortPolicy());
    		}

            if(channelExecutor==null) {
                channelExecutor = Executors.newCachedThreadPool(
                        new BasicThreadFactory.Builder().namingPattern("ChannelEventProcessor-%d").daemon(true).build());
            	
            }
            
            if(exceptionExecutor==null) {
                exceptionExecutor = Executors.newCachedThreadPool(
                        new BasicThreadFactory.Builder().namingPattern("ExceptionEventProcessor-%d").daemon(true).build());
            	
            }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

}
