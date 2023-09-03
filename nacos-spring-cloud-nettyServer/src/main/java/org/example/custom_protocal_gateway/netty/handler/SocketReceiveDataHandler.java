package org.example.custom_protocal_gateway.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.example.custom_protocal_gateway.config.BaseConfigurable;
import org.example.custom_protocal_gateway.util.ReqDataUtils;
import org.example.custom_protocal_gateway.util.StringHelper;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 类名 SocketReceiveDataHandler
 * 描述 Socket接受数据请求处理
 *
 * @author hedonglin
 * @version 1.0
 * @date 2019/9/29 11:07
 */
@Slf4j
public class SocketReceiveDataHandler extends SimpleChannelInboundHandler<String> implements Serializable, BaseConfigurable {
    private static final long serialVersionUID = -1045570500846888098L;
    /**
     * 用于标识报文是否完全接收，如果完全接收，则调用下一个handler的channelReadComplete方法
     */
    private AtomicBoolean isCompleted = new AtomicBoolean(false);
    /**
     * 接受到的所有消息
     */
    private ThreadLocal<String> allMsg = new ThreadLocal<>();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 换行符替换
        log.info("收到的数据为"+msg);
        msg = msg.replaceAll("(\\\\r\\\\n)", "");
        // 判断是否是结尾，是则证明数据已经读取完毕
        if (StringHelper.isBlank(msg)) {
            msg = "";
        }
        String oldAllMsg = allMsg.get() == null ? "" : allMsg.get();
        // 获取Bean

        // 如果是JSON数据，则放行
        if (ReqDataUtils.isJson(oldAllMsg + msg)) {
            log.info("read data progress is 100% \n The data is:{}" ,(oldAllMsg+msg));
            ctx.fireChannelRead(oldAllMsg + msg);
            isCompleted.set(true);
            allMsg.remove();
        } else {
            // 不是JSON或或XML，一是数据本身非法，二是XML/JSON数据尚未接受完毕，这里进行数据全部接受，判断格式放在下一个handler
            oldAllMsg = allMsg.get() == null ? "" : allMsg.get();
            allMsg.set(oldAllMsg + msg);
            // 读取进度
            double process = oldAllMsg.length() / (double) allMsg.get().length();
            log.info("read data progress is {}%" , String.format("%.2f", process * 100));
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 数据读取标志位true,完成读取，流入下一个ChannelInboundHandler
        if (isCompleted.get()) {
            super.channelReadComplete(ctx);
        }
    }
}
