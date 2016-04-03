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
public class QueryUpdatePreparedStatementCreator implements PreparedStatementCreator {

	private SqlQuery query;
	private SqlUpdate update;
	private String sql;
	
	public QueryUpdatePreparedStatementCreator(SqlQuery query, SqlUpdate update, String sql) {
		super();
		this.query = query;
		this.update = update;
		this.sql = sql;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql);
		int psInd = 1;
		if(update.getParams().size() > 0){
			List<Object> params = update.getParams();
			for (int i = 0; i < params.size(); i++) {
				Object param = params.get(i);
				ps.setObject(psInd, param);
				psInd++;
			}
		}
		if(query.getCriteria() != null){
			List<Object> params = query.getCriteria().getParams();
			for (int i = 0; i < params.size(); i++) {
				Object param = params.get(i);
				ps.setObject(psInd, param);
				psInd++;
			}
		}
		return ps;
	}

}
