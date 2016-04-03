package com.flur.entity;

import com.flur.persistence.db.annotation.Table;

/**
 * Created by marvin on 15/9/11.
 */
@Table(name = "user_secure")
public class UserSecure {

	private int id;						// 主键
	private String password;			// 加密之后
	private String token;				//登录之后的token
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
