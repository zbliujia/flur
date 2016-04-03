package com.flur.common.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie工具类
 * @author wx
 *
 */
public class CookieUtil {
	
	private HttpServletResponse response ;
	private HttpServletRequest request ;
	/**
	 * 用户cookie默认生存期，30天
	 */
	public static final int USER_COOKIE_DEFAULT_AGE = 60*60*24*30; 
	/**
	 * 用户cookie默认路径
	 */
	public static final String USER_COOKIE_DEFAULT_PATH = "/"; 
	
	private CookieUtil(HttpServletResponse response){
		this.response = response;
	}
	
	/**
	 * 获得写实例
	 * @param response
	 * @return
	 */
	public static CookieUtil getWriteInstance(HttpServletResponse response){
		return new CookieUtil(response);
	}
	
	private CookieUtil(HttpServletRequest request){
		this.request = request;
	}
	
	/**
	 * 获得读实例
	 * @param request
	 * @return
	 */
	public static CookieUtil getReadInstance(HttpServletRequest request){
		return new CookieUtil(request);
	}
	
	/**
	 * 创建默认cookie
	 * 默认一个月生命期
	 * @param value
	 */
	public void create(String key, String value){
		create(key, value, USER_COOKIE_DEFAULT_AGE , USER_COOKIE_DEFAULT_PATH);
	}

	/**
	 * 创建cookie
	 * @param key
	 * @param value
	 * @param age
	 * @param path
	 */
	public void create(String key, String value, int age, String path){
		if(this.response == null){
			throw new IllegalArgumentException("response value is null");
		}
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(age);
		cookie.setPath(path);
		
		this.response.addCookie(cookie);
		
	}
	
	/**
	 * 获得指定cookie值
	 * @param key
	 * @return
	 */
	public String getCookieValue(String key){
		return getCookies(true).get(key);
	}
	
	/**
	 * 获得所有key不为null的cookie
	 * @return
	 */
	public Map<String, String> getCookies(){
		return getCookies(true);
	}
	
	/**
	 * 获得所有cookie
	 * @param filterKeyNull
	 * @return
	 */
	public Map<String, String> getCookies(boolean filterKeyNull){
		if(this.request == null){
			throw new IllegalArgumentException("request value is null");
		}
		Map<String, String> returnMap = new HashMap<String, String>(); 
		Cookie[] ces = request.getCookies();
		if(ces == null) return returnMap;
		for ( int i = 0 ; i < ces.length ; i ++) {
			Cookie ce = ces[i];
			if(filterKeyNull){
				if(ce.getName() == null || "".equals(ce.getName())){
					continue;
				}
			}
			returnMap.put(ce.getName(), ce.getValue());
		}
		return returnMap;
	}
	
	/**
	 * 删除默认cookie
	 */
	public void remove(String key){
		remove(key, USER_COOKIE_DEFAULT_PATH);
	}
	
	/**
	 * 删除指定路径下的指定键的cookie
	 * @param key
	 * @param path
	 */
	public void remove(String key, String path){
		create(key, null, 0, path);
	}
	

}
