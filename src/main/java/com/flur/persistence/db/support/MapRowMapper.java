package com.flur.persistence.db.support;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public class MapRowMapper implements RowMapper<Map<String, Object>> {
	

	public MapRowMapper() {
		super();
	}

	@Override
	public Map<String, Object> mapRow(ResultSet rs, int arg1) throws SQLException {
		//反射class，并设置值
		Map<String, Object> map = new HashMap<String, Object>();
		ResultSetMetaData metaData = rs.getMetaData();
		int count = metaData.getColumnCount();
		for(int i = 0; i < count; i++){
			String column = metaData.getColumnLabel(i+1);
			map.put(column, rs.getObject(column));
		}
		return map;
	}

}
