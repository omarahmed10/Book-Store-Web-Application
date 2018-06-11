package com.db.service;

import java.util.List;

import com.db.model.BookInfo;
import com.db.model.SaleInfo;
import com.db.model.SqlResult;
import com.db.model.UserInfo;

public interface SalesService {
	public List<SaleInfo> totalSalesPreviousMonth();

	public List<UserInfo> top5Customers();

	public List<BookInfo> top10Book();
}
