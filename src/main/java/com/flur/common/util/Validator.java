package com.flur.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class Validator {

	/**
	 * 判断内容不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean notEmpty(Object str) {
		if (str != null && str.toString().trim().length() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断内容是空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(Object str) {
		if (str == null || str.toString().trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	
	/**
	 * 判断是否是合法邮箱地址
	 * 
	 * @param email
	 * @return
	 */
	public static boolean emailAddress(String email) {
		Pattern p = Pattern.compile("^\\w+([\\-+.]\\w+)*@\\w+([-.]\\w+)*\\.[a-z]{2,3}");
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	/**
	 * 判断是否是合法的手机号码
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone){
		if(phone == null){
			return false;
		}
		Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^[1][0-9][0-9]{9}$"); // 验证手机号  
        m = p.matcher(phone);  
        b = m.matches(); 
        return b;
	}
	
	/**
	 * 检查请求的参数
	 * @param request
	 * @param names
	 * @return
	 */
	public static boolean checkParameter(HttpServletRequest request, String... names){
		for(String name : names){
			if(Validator.isEmpty(request.getParameter(name))){
				return false;
			}
		}
		return true;
	}

	/**
	 * 只包含英文字母和数字、下划线
	 * 
	 * @param str
	 * @return
	 */
	public static boolean onlyNumAndChar(String str) {
		String regex = "^[a-zA-Z0-9_]+$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(str).matches();
	}
	/**
	 * 必须包含字母
	 *
	 * @param str
	 * @return
	 */
	public static boolean hasLetterAndNum(String str) {
		Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z].*).{6,}$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 是否长度符合
	 * 
	 * @param str
	 * @param min
	 *            最小
	 * @param max
	 *            最大
	 * @return
	 */
	public static boolean lengthBetween(String str, int min, int max) {
		return str.length() >= min && str.length() <= max;
	}

}
