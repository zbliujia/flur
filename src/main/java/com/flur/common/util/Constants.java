package com.flur.common.util;

public class Constants {
	
	/**
	 * salt_to_compress
	 */
	public static final String PASSWORD_SALT = "62729956539adc67ba4d281f832b8498";
	
	public static final String REDIS_KEY_VERIFY_CODE = "user:verifycode:";
	
	public static final String REDIS_KEY_TOKEN = "user:token:";
	
	public static final int REDIS_TOKEN_EXPIRE = 60 * 60 * 2;	//两小时
	
}
