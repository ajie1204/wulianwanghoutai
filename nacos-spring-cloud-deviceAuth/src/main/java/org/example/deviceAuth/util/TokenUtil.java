package org.example.deviceAuth.util;

import java.util.Random;
import java.util.UUID;

public class TokenUtil {
	

	
	/**
	 * 生成UUID
	 * @return
	 */
	public static String createUUID(){
		String uuid="";
		try {
		    uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		}catch(Exception e) {
			uuid=System.currentTimeMillis()+""+new Random().nextInt(10000);
			LogUtil.error(e);
		}
		return uuid;
	}
	


}
