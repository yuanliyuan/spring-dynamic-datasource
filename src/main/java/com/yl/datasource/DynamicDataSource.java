package com.yl.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
* @author yuanli   
* @date 2017年5月22日 上午11:39:42
 */
public class DynamicDataSource extends AbstractRoutingDataSource  {

	@Override
	protected Object determineCurrentLookupKey() {
		 return DatabaseContextHolder.getDatabaseType();
	}

}
