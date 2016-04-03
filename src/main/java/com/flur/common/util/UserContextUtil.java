package com.flur.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户上下文操作工具类
 * 
 * @author wx
 *
 */
public class UserContextUtil {

	/**
	 * 对密码执行加密 经过两次加密，第一次：明文MD5，第二次：加盐再次MD5
	 * 
	 * @param password
	 *            密码明文
	 * @return
	 */
	public static String encryptPassword(String password) {
		String firstEncrypt = StringUtil.md5Encrypt(password);
		String result = StringUtil.md5Encrypt(Constants.PASSWORD_SALT + firstEncrypt);
		return result;
	}
	
	/**
	 * 获取当前登录用户的ID
	 * @param request
	 * @return
	 */
	public static int getUserId(HttpServletRequest request){
		return Integer.parseInt(request.getParameter("loginUserId"));
	}
	
	/**
	 * 获取头像地址
	 * @param photoId
	 * @return
	 */
	public static String getPhotoUrl(Object photoId){
		if(Validator.isEmpty(photoId)){
			return "http://cdn-img.easyicon.net/png/11814/1181403.gif";
		}else{
			return "http://cdn-img.easyicon.net/png/11814/1181403.gif";
		}
	}
	
}
