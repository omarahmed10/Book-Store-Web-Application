package com.db.dao;

import java.sql.Connection;
import com.db.model.SqlResult;

public interface SqlQueryDao {
	public SqlResult callProcedure(Connection connection, String procName, Object... args);
}
