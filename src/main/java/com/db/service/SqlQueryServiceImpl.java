package com.db.service;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.dao.SqlQueryDao;
import com.db.model.SqlResult;

@Service
public class SqlQueryServiceImpl implements SqlQueryService {

	SqlQueryDao sqlDao;

	@Autowired
	public void addSqlQueryDao(SqlQueryDao sqlDao) {
		this.sqlDao = sqlDao;
	}

	public SqlResult callProcedure(Connection connection, String procName, Object... args) {
		return sqlDao.callProcedure(connection, procName, args);
	}

}
