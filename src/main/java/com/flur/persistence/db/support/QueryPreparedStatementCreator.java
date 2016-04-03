package com.flur.persistence.db.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;

/**
 * 查询管道构造对象
 * @author WX
 *
 */
public class QueryPreparedStatementCreator implements PreparedStatementCreator {

	private SqlQuery query;
	private String sql;
	
	public QueryPreparedStatementCreator(SqlQuery query, String sql) {
		super();
		this.query = query;
		this.sql = sql;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql);
		if(query.getCriteria() != null){
			List<Object> params = query.getCriteria().getParams();
			for (int i = 0; i < params.size(); i++) {
				Object param = params.get(i);
				int psInd = i + 1;
				ps.setObject(psInd, param);
			}
		}
		return ps;
	}

}
