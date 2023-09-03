package org.example.deviceAuth.entity;

import lombok.Data;

@Data
public class RegistReply implements java.io.Serializable{

	private String deviceID;
	private String token;
	private String url;
//	private String port;
	private String protocol;



}
