package org.example.deviceAuth.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class NumberUtil {
	
	/**
	 * int整数转换为1字节的byte数组
	 * @param
	 * @return
	 */
	public static byte[] intToByte1(String iStr){
		int i=0;
		try{
			i=Integer.parseInt(iStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		byte[] targets = null;
		try{
			targets = new byte[1];
			targets[0] = (byte) i;
		}catch(Exception e){
			targets=null;
			e.printStackTrace();
		}
		return targets;
	}
	
	/**
	 * int整数转换为4字节的byte数组
	 * 
	 * @param i
	 *            整数
	 * @return byte数组
	 */
	public static byte[] intToByte4(String iStr) {
		int i=0;
		try{
			i=Integer.parseInt(iStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		byte[] targets = new byte[4];
		try{
			targets[3] = (byte) (i & 0xFF);
			targets[2] = (byte) (i >> 8 & 0xFF);
			targets[1] = (byte) (i >> 16 & 0xFF);
			targets[0] = (byte) (i >> 24 & 0xFF);
		}catch(Exception e){
			targets=null;
			e.printStackTrace();
		}
		return targets;
	}

	/**
	 * long整数转换为8字节的byte数组
	 * 
	 * @param lo
	 *            long整数
	 * @return byte数组
	 */
	public static byte[] longToByte8(long lo) {
		byte[] targets = new byte[8];
		for (int i = 0; i < 8; i++) {
			int offset = (targets.length - 1 - i) * 8;
			targets[i] = (byte) ((lo >>> offset) & 0xFF);
		}
		return targets;
	}

	/**
	 * short整数转换为2字节的byte数组
	 * 
	 * @param s
	 *            short整数
	 * @return byte数组
	 */
	public static byte[] intToByte2(String iStr) {
		int s=0;
		try{
			s=Integer.parseInt(iStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		byte[] targets = new byte[2];
		try{
			targets[0] = (byte) (s >> 8 & 0xFF);
			targets[1] = (byte) (s & 0xFF);
		}catch(Exception e){
			targets=null;
			e.printStackTrace();
		}
		return targets;
	}

	/**
	 * byte数组转换为无符号short整数
	 * 
	 * @param bytes
	 *            byte数组
	 * @return short整数
	 */
	public static int byte2ToUnsignedShort(byte[] bytes) {
		try{
			return byte2ToUnsignedShort(bytes, 0);
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * byte数组转换为无符号short整数
	 * 
	 * @param bytes
	 *            byte数组
	 * @param off
	 *            开始位置
	 * @return short整数
	 */
	public static int byte2ToUnsignedShort(byte[] bytes, int off) {
		int high = bytes[off];
		int low = bytes[off + 1];
		return (high << 8 & 0xFF00) | (low & 0xFF);
	}

	/**
	 * byte数组转换为int整数
	 * 
	 * @param bytes
	 *            byte数组
	 * @param off
	 *            开始位置
	 * @return int整数
	 */
	public static int byte4ToInt(byte[] bytes, int off) {
		int b0 = bytes[off] & 0xFF;
		int b1 = bytes[off + 1] & 0xFF;
		int b2 = bytes[off + 2] & 0xFF;
		int b3 = bytes[off + 3] & 0xFF;
		return (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
	}	

	
	
    /** 
     * 将byte[]转为各种进制的字符串 
     * @param bytes byte[] 
     * @param radix 基数可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制 
     * @return 转换后的字符串 
     */  
    public static String binary(byte[] bytes, int radix){
    		String binaryStr="0";
    		try{
    			binaryStr=(new BigInteger(1, bytes)).toString(radix);// 这里的1代表正数  
    		}catch(Exception e){
    			binaryStr="0";
    			e.printStackTrace();
    		}
    		return binaryStr;
    }
    
    
	
    /**
     * 从一个byte[]数组中截取一部分
     * @param src
     * @param begin
     * @param count
     * @return
     */
    public static byte[] subBytes(byte[] src, int begin, int count) {
    		try{
    	        byte[] bs = new byte[count];
    	        for (int i=begin;i<begin+count; i++) {
                    bs[i-begin] = src[i];
                }
    	        return bs;
    		}catch(Exception e){
    			e.printStackTrace();
    			return null;
    		}
    }
    
    
    /**
     * byte[]数组转换为十六进制
     * byte[] to hex string
     * 
     * @param bytes
     * @return
     */
    public static String bytesToHexFun3(byte[] bytes) {
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        for(byte b : bytes) { // 使用String的format方法进行转换
            buf.append(String.format("%02x", new Integer(b & 0xff)));
        }

        return buf.toString().toUpperCase();
    }
	
    /**
     * 十六进制0xFF格式转换为含有一个字节的字节数组
     * @param hexStr
     * @return
     */
    public static byte[] hexString2bytes(String hexStr){
    		byte[] bb=null;
    		try{
    			bb=new byte[1];
    			byte b = Integer.decode(hexStr).byteValue();
    			bb[0]=b;
    		}catch(Exception e){
    			bb=null;
    			e.printStackTrace();
    		}
    		return bb;

    }
    

    /**
     * 字节数组转换为0xFF格式表示
     * @param b byte[]
     * @return String
     */
    public static String bytes2HexString(byte[] b) {
        String ret = "";
        try{
	        for (int i = 0; i < b.length; i++) {
	            String hex = Integer.toHexString(b[i] & 0xFF);
	            if (hex.length() == 1) {
	                hex = '0' + hex;
	            }
	            ret += " 0x" + hex.toUpperCase();
	        }
        }catch(Exception e){
        		e.printStackTrace();
        }
        return ret;
    }

	
    /**
     * 从ByteBuf中获取信息 使用UTF-8编码返回
     */
    public static String getMessage(ByteBuf buf) {
        byte[] con = new byte[buf.readableBytes()];
        buf.readBytes(con);
        try {
            return new String(con, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
    
    
    
    /**
     * 将Sting转化为UTF-8编码的字节
     */
    public static ByteBuf getSendByteBuf(String message){
    		try{
    	        byte[] req = message.getBytes("UTF-8");
    	        ByteBuf pingMessage = Unpooled.buffer();
    	        pingMessage.writeBytes(req);
    	        return pingMessage;
    		}catch(Exception e){
    			e.printStackTrace();
    			return null;
    		}
    }
    
    /**
     * 获取字节信息数组
     * @param msg
     * @return
     */
    public static byte[] getMsgByte(ByteBuf msg){
    		byte[] msgByteArray=null;
    		try{
    			ByteBuf msgBuf = (ByteBuf)msg;
    			msgByteArray= new byte[msgBuf.readableBytes()];
    			msgBuf.readBytes(msgByteArray);
    		}catch(Exception e){
    			msgByteArray=null;
    			e.printStackTrace();
    		}
    		return msgByteArray;

    }
    
    /**
     * 获取字节信息数组
     * @param msg
     * @return
     */
    public static byte[] getMsgByteBuf(ByteBuf msgBuf){
    		byte[] msgByteArray=null;
    		try{
    			msgByteArray= new byte[msgBuf.readableBytes()];
    			msgBuf.readBytes(msgByteArray);
    		}catch(Exception e){
    			msgByteArray=null;
    			e.printStackTrace();
    		}
    		return msgByteArray;

    }

	/**
	 * 获取字节信息数组
	 * @param msg
	 * @return
	 */
	public static byte[] getMsgByte(Object msg){
		byte[] msgByteArray=null;
		try{
			ByteBuf msgBuf = (ByteBuf)msg;
			msgByteArray= new byte[msgBuf.readableBytes()];
			//msgBuf.readBytes(msgByteArray);
			msgBuf.getBytes(0,msgByteArray);
		}catch(Exception e){
			msgByteArray=null;
			e.printStackTrace();
		}
		return msgByteArray;

	}


}
