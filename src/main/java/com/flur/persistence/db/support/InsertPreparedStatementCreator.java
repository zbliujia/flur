package com.flur.persistence.db.support;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.core.PreparedStatementCreator;

/**
 * 插入管道构造对象
 * @author WX
 *
 */
public class InsertPreparedStatementCreator implements PreparedStatementCreator {

	private Object entity;
	private String sql;
	
	public InsertPreparedStatementCreator(Object entity, String sql) {
		super();
		this.entity = entity;
		this.sql = sql;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		Field[] fields = entity.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			int psInd = i + 1;
			String typeName = field.getType().getName();
			if(typeName.equals(String.class.getName())){
				//字符串
				Object value = null;
				try {
					value = field.get(entity);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(value == null){
					ps.setString(psInd, null);
				}else{
					ps.setString(psInd, value.toString());
				}
			}else if(typeName.equals(Integer.class.getName()) || typeName.equals("int")){
				//Integer
				try {
					ps.setInt(psInd, Integer.parseInt(field.get(entity).toString()));
				} catch (Exception e) {
					e.printStackTrace();
					ps.setInt(psInd, 0);
				}
			}else if(typeName.equals(Long.class.getName()) || typeName.equals("long")){
				//Long
				try {
					ps.setLong(psInd, field.getLong(entity));
				} catch (Exception e) {
					ps.setLong(psInd, 0);
				}
			}else if(typeName.equals(Double.class.getName()) || typeName.equals("double")){
				//Long
				try {
					ps.setDouble(psInd, field.getDouble(entity));
				} catch (Exception e) {
					ps.setLong(psInd, 0);
				}
			}
		}
		return ps;
	}

}
