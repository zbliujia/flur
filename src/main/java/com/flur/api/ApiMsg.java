package com.flur.api;

public class ApiMsg {
	
	final static public int FAILED = 0;
	final static public int SUCCESS = 1;	//成功
	final static public int EXPIRED = 2;	//登录过期
	final static public int EXCEPTION = 3;	//异常
	final static public int UPGRADE = 4;	//客户端太旧
	
	private int code;
	private Object message;
	
	public ApiMsg(int code, Object message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public ApiMsg(int code) {
		super();
		this.code = code;
	}
	
	public ApiMsg() {
		super();
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	
}
