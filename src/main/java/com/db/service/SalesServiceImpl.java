package com.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.dao.BookDao;
import com.db.dao.SalesDao;
import com.db.model.BookInfo;
import com.db.model.SaleInfo;
import com.db.model.SqlResult;
import com.db.model.UserInfo;

@Service
public class SalesServiceImpl implements SalesService {

	SalesDao salesDao;

	@Autowired
	public void addSalesDao(SalesDao salesDao) {
		this.salesDao = salesDao;
	}

	public List<SaleInfo> totalSalesPreviousMonth() {
		return salesDao.totalSalesPreviousMonth();
	}

	public List<UserInfo> top5Customers() {
		// TODO Auto-generated method stub
		return salesDao.top5Customers();
	}

	public List<BookInfo> top10Book() {
		// TODO Auto-generated method stub
		return salesDao.top10Book();
	}

}
