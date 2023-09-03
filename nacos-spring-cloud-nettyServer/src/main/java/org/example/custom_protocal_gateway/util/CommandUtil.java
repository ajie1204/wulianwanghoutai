package org.example.custom_protocal_gateway.util;


import javax.activation.CommandInfo;

public class CommandUtil {
	
	/**
	 * 获得ICCID号
	 * @param msgData
	 * @return
	 */
	public static String getICCID(byte[] msgData){
		try{
			byte[] iccidByte=new byte[10];
			int j=0;
			for(int i=35;i<45;i++){
				iccidByte[j]=msgData[i];
				j++;
			}
			return NumberUtil.bytesToHexFun3(iccidByte);
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}

		
	}
	
	/**
	 * 获得设备ID
	 * @param msgData
	 * @return
	 */
	public static String getDeviceID(byte[] msgData){
		try{
	        byte[] deviceData=NumberUtil.subBytes(msgData, 0, 4);
	        return NumberUtil.binary(deviceData, 10);
		}catch(Exception e){
			e.printStackTrace();
			return "0";
		}
	}
	
	/**
	 * 获得命令
	 * @param msgData
	 * @return
	 */
	public static String getCommandTag(byte[] msgData){
		try{
			return NumberUtil.bytes2HexString(NumberUtil.subBytes(msgData, 5, 1));
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		
	}
	
	/**
	 * 获得当前指令对象
	 * @param msgData
	 * @return
	 */
	/*public static CommandInfo getCommandInfo(String deviceId, String commandid, byte[] msgData, int type){
		CommandInfo commandInfo=new CommandInfo();
		try{
			commandInfo.setDeviceId(deviceId);
			commandInfo.setJfModel(NumberUtil.binary(NumberUtil.subBytes(msgData, 4, 1), 10));
			commandInfo.setCommand(commandid);
			commandInfo.setDeviceStatus(NumberUtil.binary(NumberUtil.subBytes(msgData, 6, 1), 10));
			commandInfo.setXfFlow(NumberUtil.binary(NumberUtil.subBytes(msgData, 7, 2), 10));
			commandInfo.setCzFlow(NumberUtil.binary(NumberUtil.subBytes(msgData, 9, 2), 10));
			commandInfo.setCzDay(NumberUtil.binary(NumberUtil.subBytes(msgData, 11, 2), 10));
			commandInfo.setSyFlow(NumberUtil.binary(NumberUtil.subBytes(msgData, 13, 2), 10));
			commandInfo.setSyDay(NumberUtil.binary(NumberUtil.subBytes(msgData, 15, 2), 10));
			commandInfo.setAlreadyUseFlow(NumberUtil.binary(NumberUtil.subBytes(msgData, 17, 2), 10));
			commandInfo.setAlreadyDay(NumberUtil.binary(NumberUtil.subBytes(msgData, 19, 2), 10));
			commandInfo.setCunWater(NumberUtil.binary(NumberUtil.subBytes(msgData, 21, 2), 10));
			commandInfo.setYuanWater(NumberUtil.binary(NumberUtil.subBytes(msgData, 23, 2), 10));
			commandInfo.setFilter1(NumberUtil.binary(NumberUtil.subBytes(msgData, 25, 2), 10));
			commandInfo.setFilter2(NumberUtil.binary(NumberUtil.subBytes(msgData, 27, 2), 10));
			commandInfo.setFilter3(NumberUtil.binary(NumberUtil.subBytes(msgData, 29, 2), 10));
			commandInfo.setFilter4(NumberUtil.binary(NumberUtil.subBytes(msgData, 31, 2), 10));
			commandInfo.setFilter5(NumberUtil.binary(NumberUtil.subBytes(msgData, 33, 2), 10));
			if(Constants.COMMAND_8.equals(commandInfo.getCommand()) || Constants.COMMAND_9.equals(commandInfo.getCommand()) || Constants.COMMAND_27.equals(commandInfo.getCommand())){
				commandInfo.setIccid(getICCID(msgData));
			}else{
				commandInfo.setFilterMax1(NumberUtil.binary(NumberUtil.subBytes(msgData, 35, 2), 10));
				commandInfo.setFilterMax2(NumberUtil.binary(NumberUtil.subBytes(msgData, 37, 2), 10));
				commandInfo.setFilterMax3(NumberUtil.binary(NumberUtil.subBytes(msgData, 39, 2), 10));
				commandInfo.setFilterMax4(NumberUtil.binary(NumberUtil.subBytes(msgData, 41, 2), 10));
				commandInfo.setFilterMax5(NumberUtil.binary(NumberUtil.subBytes(msgData, 43, 2), 10));				
			}
			
			if(type==1) {
				commandInfo.setBjTime(NumberUtil.binary(NumberUtil.subBytes(msgData, 45, 4), 10));
				commandInfo.setDeviceType(NumberUtil.binary(NumberUtil.subBytes(msgData, 49, 1), 10));
				commandInfo.setCheckNo(NumberUtil.binary(NumberUtil.subBytes(msgData, 50, 2), 10));
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return commandInfo;
	}*/
	
	
	
	
	/**
	 * 封装指令对象
	 * @param commandInfo
	 */
	/*public static byte[] putCommandInfo(CommandInfo commandInfo){
		byte[] commandBytes=null;
		try{
			byte[] c_1=NumberUtil.intToByte4(commandInfo.getDeviceId());
			byte[] c_2=NumberUtil.intToByte1(commandInfo.getJfModel());
			byte[] c_3=NumberUtil.hexString2bytes(commandInfo.getCommand());
			byte[] c_4=NumberUtil.intToByte1(commandInfo.getDeviceStatus());
			byte[] c_5=NumberUtil.intToByte2(commandInfo.getXfFlow());
			byte[] c_6=NumberUtil.intToByte2(commandInfo.getCzFlow());
			byte[] c_7=NumberUtil.intToByte2(commandInfo.getCzDay());
			byte[] c_8=NumberUtil.intToByte2(commandInfo.getSyFlow());
			byte[] c_9=NumberUtil.intToByte2(commandInfo.getSyDay());
			byte[] c_10=NumberUtil.intToByte2(commandInfo.getAlreadyUseFlow());
			byte[] c_11=NumberUtil.intToByte2(commandInfo.getAlreadyDay());
			byte[] c_12=NumberUtil.intToByte2(commandInfo.getCunWater());
			byte[] c_13=NumberUtil.intToByte2(commandInfo.getYuanWater());
			byte[] c_14=NumberUtil.intToByte2(commandInfo.getFilter1());
			byte[] c_15=NumberUtil.intToByte2(commandInfo.getFilter2());
			byte[] c_16=NumberUtil.intToByte2(commandInfo.getFilter3());
			byte[] c_17=NumberUtil.intToByte2(commandInfo.getFilter4());
			byte[] c_18=NumberUtil.intToByte2(commandInfo.getFilter5());
			byte[] c_19=NumberUtil.intToByte2(commandInfo.getFilterMax1());
			byte[] c_20=NumberUtil.intToByte2(commandInfo.getFilterMax2());
			byte[] c_21=NumberUtil.intToByte2(commandInfo.getFilterMax3());
			byte[] c_22=NumberUtil.intToByte2(commandInfo.getFilterMax4());
			byte[] c_23=NumberUtil.intToByte2(commandInfo.getFilterMax5());
			
			if(!StringUtils.isEmpty(commandInfo.getBjTime()) && !StringUtils.isEmpty(commandInfo.getDeviceType()) && !StringUtils.isEmpty(commandInfo.getCheckNo())) {
				byte[] c_24=NumberUtil.intToByte4(commandInfo.getBjTime());
				byte[] c_25=NumberUtil.intToByte1(commandInfo.getDeviceType());
				byte[] c_26=NumberUtil.intToByte2(commandInfo.getCheckNo());
				commandBytes=Tools.byteMergerAll(c_1,c_2,c_3,c_4,c_5,c_6,c_7,c_8,c_9,c_10,c_11,c_12,c_13,c_14,c_15,c_16,c_17,c_18,c_19,c_20,c_21,c_22,c_23,c_24,c_25,c_26);
			}else {
				commandBytes=Tools.byteMergerAll(c_1,c_2,c_3,c_4,c_5,c_6,c_7,c_8,c_9,c_10,c_11,c_12,c_13,c_14,c_15,c_16,c_17,c_18,c_19,c_20,c_21,c_22,c_23);
			}
			
			byte bcll=0;
			for(int i=0;i<commandBytes.length;i++){
				bcll=(byte)(bcll & commandBytes[i]);
			}
			byte[] weiToken=NumberUtil.intToByte2(String.valueOf(bcll));
			commandBytes[7]=weiToken[0];
			commandBytes[8]=weiToken[1];
			
		}catch(Exception e){
			commandBytes=null;
			e.printStackTrace();
		}
		return commandBytes;
	}*/
	


}
