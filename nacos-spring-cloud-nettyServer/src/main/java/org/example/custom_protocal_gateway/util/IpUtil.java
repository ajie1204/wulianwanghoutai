package org.example.custom_protocal_gateway.util;

import java.net.Inet4Address;
import java.net.InetAddress;

public class IpUtil {
	
	
	public static String getLocalIp(){
		String ipaddress="";
		try {
			InetAddress ip4 = Inet4Address.getLocalHost();
			ipaddress=ip4.getHostAddress();
		} catch (Exception e) {
			ipaddress="127.0.0.1";
			e.printStackTrace();
		}
		return ipaddress;		
	}

}
