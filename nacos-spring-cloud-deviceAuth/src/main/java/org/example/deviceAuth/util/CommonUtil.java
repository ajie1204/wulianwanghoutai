package org.example.deviceAuth.util;

import io.netty.buffer.ByteBuf;

public class CommonUtil {
	
    public  static byte[]  copyByteBuf(ByteBuf byteBuf){
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        return bytes;
    }

}
