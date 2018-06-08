package com.db.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import com.db.model.SqlResult;

@Repository
public class SqlQueryDaoImp implements SqlQueryDao {

	public SqlResult callProcedure(Connection connection, String procName, Object... args) {
		SqlResult result = new SqlResult();
		try {
			// build the call upon the passed arguments
			String call = "{call " + procName + "(";
			if (args.length > 0) {
				call += "?";
				for (int i = 1; i < args.length; i++) {
					call += ",?";
				}
			}
			call += ")}";

			// Prepare a call to the stored procedure
			CallableStatement callStatment = connection.prepareCall(call);

			// pass the arguments after casting their types
			for (int i = 0; i < args.length; i++) {
				int argType = callStatment.getParameterMetaData().getParameterType(i + 1);

				if (argType == Types.VARCHAR)
					callStatment.setString(i + 1, args[i].toString());
				else if (argType == Types.INTEGER)
					callStatment.setInt(i + 1, (Integer) args[i]);
				else if (argType == Types.DATE)
					callStatment.setDate(i + 1, (Date) args[i]);
			}

			// the boolean returned , true if the query returns result set
			result.success = callStatment.execute();
			System.out.println("SQL PROC _________________" + result.success);
			// update result set and update count
			result.rs = callStatment.getResultSet();
		} catch (SQLException e) {
			result.msg = e.getMessage();
		}
		return result;
	}

}
