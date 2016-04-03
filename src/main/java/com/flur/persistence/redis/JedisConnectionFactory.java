package com.flur.persistence.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectionFactory {
	
	private String host;
	private int port;
	private int timeout;
	private String password;
	private JedisPoolConfig poolConfig;
	private JedisPool pool;
	
	/**
	 * 初始化方法
	 */
	public void init(){
		if(password == null || password.equals("")){
			//没有密码
			this.pool = new JedisPool(poolConfig, host, port, timeout);
		}else{
			this.pool = new JedisPool(poolConfig, host, port, timeout, password);
		}
	}
	
	/**
	 * 销毁方法
	 */
	public void destroy(){
		if(this.pool != null){
			this.pool.destroy();
		}
	}
	
	/**
	 * 获取jedis资源
	 * @return
	 */
	public Jedis getResource(){
		return this.pool.getResource();
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPoolConfig(JedisPoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
}
