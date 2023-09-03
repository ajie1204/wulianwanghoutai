package org.example.deviceAuth.common.net.util;

/**
 * 错误码表
 * @author xuxiangxun
 *
 */
public class CodeUtil {
	
	public static final int SUCCEED=0;//成功
	public static final int ERROR=100;//失败
	public static final int ERROR_500=500;//服务器内部错误
	public static final int TOKEN_ERROR=101;//Token无效
	public static final int NOT_CONN=102;//拒绝连接
	public static final int ERROR_103=103;//注册服务器连接错误
	public static final int ERROR_104=104;//WEB服务设备注册平台错误
	public static final int ERROR_105=105;//WEB平台参数错误
	public static final int ERROR_106=106;//WEB平台未注册的设备，拒绝连接(未加入到白名单)
	public static final int ERROR_107=107;//WEB平台过期的设备，拒绝连接
	public static final int ERROR_108=108;//WEB平台禁用的设备，拒绝连接(已加入到黑名单)
	
}
