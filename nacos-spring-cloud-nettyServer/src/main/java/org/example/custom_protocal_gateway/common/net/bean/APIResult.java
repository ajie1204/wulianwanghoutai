package org.example.custom_protocal_gateway.common.net.bean;

import org.example.custom_protocal_gateway.util.CodeUtil;

public class APIResult<T> implements java.io.Serializable{
	private int code= CodeUtil.ERROR;
	private String msg="";
	private T data;
	
	public static APIResult create() {
		return new APIResult();
	}
	
	
	public APIResult(){
		data=null;
	}
	
	public APIResult(int code){
		this.code=code;
	}
	
	public APIResult(String msg){
		this.msg=msg;
	}
	
	public APIResult(int code,String msg){
		this.code=code;
		this.msg=msg;
	}
	
	
	public APIResult OK(String msg){
		this.code=CodeUtil.SUCCEED;
		this.msg=msg;
		return this;
	}
	public APIResult OK(){
		this.code=CodeUtil.SUCCEED;
		this.msg="";
		return this;
	}
	public APIResult NO(String msg){
		this.code=CodeUtil.ERROR;
		this.msg=msg;
		return this;
	}
	public APIResult NO(int errorCode,String msg) {
		this.code=errorCode;
		this.msg=msg;
		return this;
	}
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
		this.code=CodeUtil.SUCCEED;
		this.msg="";
	}
	
	public APIResult createOK(T data) {
		this.data = data;
		this.code=CodeUtil.SUCCEED;
		this.msg="";
		return this;
	}

}
