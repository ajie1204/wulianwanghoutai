package org.example.deviceAuth.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

	private static final Logger logger= LoggerFactory.getLogger(LogUtil.class);
	
    public static void debug(String message){
    		try{
    			logger.debug(message);
    		}catch(Exception e){
    			
    		}
    }
    
    
    public static void debug(String message,Throwable exception){
		try{
			logger.debug(message,exception);
		}catch(Exception e){
			
		}
    }

    public static void info(String message){
		try{
			logger.info(message);
		}catch(Exception e){
			e.printStackTrace();
		}
    }
    
    public static void warn(String message){
		try{
			logger.warn(message);
		}catch(Exception e){
			
		}
    }
    
    
    public static void error(String message){
		try{
			logger.error(message);
		}catch(Exception e){
			
		}
    }
    
    public static void error(String message,Throwable exception){
		try{
			logger.error(message, exception);
		}catch(Exception e){
			
		}
    }
    
    public static void error(Throwable exception){
		try{
			logger.error(exception.getMessage(), exception);
		}catch(Exception e){
			
		}
    }
    
 
}
