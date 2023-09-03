package org.example.custom_protocal_gateway.util;


public class Tools {
	
	/**
	 * 组合N个byte数组
	 * @param values
	 * @return
	 */
	 public static byte[] byteMergerAll(byte[]... values) {
		 try{
			    int length_byte = 0;
		        for (int i = 0; i < values.length; i++) {
		        		if(values[i]==null){
		        			continue;
		        		}
		            length_byte += values[i].length;
		        }
		        byte[] all_byte = new byte[length_byte];
		        int countLength = 0;
		        for (int i = 0; i < values.length; i++) {
		            byte[] b = values[i];
		            if(b==null){
		            		continue;
		            }
		            System.arraycopy(b, 0, all_byte, countLength, b.length);
		            countLength += b.length;
		        }
		        return all_byte;		 
		 }catch(Exception e){
			 e.printStackTrace();
			 return null;
		 }

	 }
	
	
	/**
	 * 组合最多两个byte数组
	 * @param data1
	 * @param data2
	 * @return
	 */
	public static byte[] combineBytes(byte[] data1,byte[] data2){
		if(data1==null && data2==null){
			return null;
		}
		if(data1==null && data2!=null){
			return data2;
		}
		if(data1!=null && data2==null){
			return data1;
		}
		  try{
			  byte[] data3 = new byte[data1.length+data2.length];
			  System.arraycopy(data1,0,data3,0,data1.length);
			  System.arraycopy(data2,0,data3,data1.length,data2.length);
			  return data3;
		  }catch(Exception e){
			  e.printStackTrace();
			  return data1;
		  }
	}


}
