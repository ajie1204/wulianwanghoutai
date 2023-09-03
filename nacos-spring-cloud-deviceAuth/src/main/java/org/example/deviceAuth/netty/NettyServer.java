package org.example.deviceAuth.netty;



import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import org.example.deviceAuth.netty.handler.IotTcpServerHandler;
//import org.example.deviceAuth.netty.handler.MyDecoder;
import org.example.deviceAuth.netty.handler.TcpHandler;
import org.example.deviceAuth.util.LogUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * Netty 服务端
 * @author xxu
 *
 */
@Component
@Order(value = 1)
public class NettyServer implements CommandLineRunner {

	// 通过nio方式来接收连接和处理连接
    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup workerGroup = new NioEventLoopGroup();
    private ServerBootstrap bootstrap = new ServerBootstrap();
    
//    @Value("${socket.port}")
    private int port=8089;//端口号
    
    
    public void start(){
	     try {
			 bootstrap.group(boss, workerGroup)
					 .channel(NioServerSocketChannel.class)
					 .option(ChannelOption.SO_BACKLOG,1024)
					 .option(ChannelOption.SO_REUSEADDR,true)
					 /*.option(EpollChannelOption.SO_REUSEPORT, true)*/
					 /*.handler(new LoggingHandler(LogLevel.INFO))
                     .option(ChannelOption.TCP_NODELAY,true)*/
					 .childOption(ChannelOption.SO_KEEPALIVE,true);
    		bootstrap.childHandler(new ChannelInitializer<SocketChannel>() { //设置过滤器
    		    @Override
    		    protected void initChannel(SocketChannel ch) throws Exception {
    		        ChannelPipeline pipeline = ch.pipeline();
//					pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
//					pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));

					pipeline.addLast(new TcpHandler());//自定义解码器
					pipeline.addLast(new IotTcpServerHandler());// 请求TCP处理器
    		    }
    		});
    		
	        //服务器绑定端口监听
	        ChannelFuture f = bootstrap.bind(port).sync();
	        LogUtil.info("Server Run succeed:"+"port"+":"+port);
//	        System.out.println("Server Run succeed:"+"port"+":"+port);
	        // 监听服务器关闭监听
	        f.channel().closeFuture().sync();
	    }catch(InterruptedException ex) {
	    	LogUtil.info("Main Server Stoping...");
	    }catch(Exception e){
	    	e.printStackTrace();
	    }finally {
            stop();
        }
    }
	//注意这里，组件启动时会执行run，这个注解是让线程异步执行，这样不影响主线程
    @Async
    @Override
    public void run(String... args) {
		System.out.println("方法运行");
        start();
    }
    
    @PreDestroy
    public void stop() {
    	try {
    		if(boss!=null) {
    			boss.shutdownGracefully(); //关闭EventLoopGroup，释放掉所有资源包括创建的线程
    		}
        	if(workerGroup!=null) {
        		workerGroup.shutdownGracefully();
        	}
        	LogUtil.info("Server Stop!!!");
        	System.out.println("Server Stop!!!");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

}
