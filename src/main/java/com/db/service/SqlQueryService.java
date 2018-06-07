package com.db.service;

import java.sql.Connection;

import com.db.model.SqlResult;

public interface SqlQueryService {
	public SqlResult callProcedure(Connection connection, String procName, Object... args);

}
