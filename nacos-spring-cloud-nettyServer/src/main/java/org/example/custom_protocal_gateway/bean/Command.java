package org.example.custom_protocal_gateway.bean;

import lombok.Data;

import java.util.HashMap;

@Data
public class Command implements java.io.Serializable{


	private String Id;
	private int s;
	private String url;


//	private String deviceId;
//	private int token_Expired;
//	private int deviceId_Invalid;
//	private String authIP;
//	private String authPort;

//	private String msg;
//	private int cmd;
//	private String id;
//	private HashMap data;



}
