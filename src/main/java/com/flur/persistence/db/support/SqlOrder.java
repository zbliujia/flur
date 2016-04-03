package com.flur.persistence.db.support;

/**
 * 数据库排序类型对象
 * @author WX
 *
 */
public class SqlOrder {
	
	private String orderType;
	
	/**
	 * 构造方法，设为私有，禁止外部new
	 * @param sortType
	 */
	private SqlOrder(String orderType){
		this.orderType = orderType;
	}
	
	public String getOrderType() {
		return orderType;
	}
	
	public static final SqlOrder ASCENDING = new SqlOrder("asc");
	
	public static final SqlOrder DESCENDING = new SqlOrder("desc");

}
