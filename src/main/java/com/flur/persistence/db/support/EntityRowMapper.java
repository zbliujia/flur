package com.flur.persistence.db.support;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EntityRowMapper<T> implements RowMapper<T> {
	
	private Class<T> cls;
	private Field[] fields;

	public EntityRowMapper(Class<T> cls) {
		super();
		this.cls = cls;
		this.fields = cls.getDeclaredFields();
	}

	@Override
	public T mapRow(ResultSet rs, int arg1) throws SQLException {
		//反射class，并设置值
		T instance = null;
		try {
			//创建实例
			instance = cls.newInstance();
			//反射属性
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				field.setAccessible(true);
				String name = field.getName();
				Object value = rs.getObject(name);
				if(value != null){
					field.set(instance, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}

}
