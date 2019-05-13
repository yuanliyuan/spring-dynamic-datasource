package com.yl.config;

import com.yl.datasource.DatabaseContextHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class DataSourceAspect implements Ordered {
	private static final Logger LOGGER = LogManager.getLogger(DataSourceAspect.class);
	
	@Autowired
	private DataSourceTransactionManager dataSourceTransactionManager;

	@Around("@annotation(dataSource)")
	public Object proceed(ProceedingJoinPoint proceedingJoinPoint, DataSource dataSource) {
		try {
			DatabaseContextHolder.setDatabaseType(dataSource.value());
			return proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			LOGGER.error("DataSourceAspect异常：{}",e);
		} finally {
			DatabaseContextHolder.clearnDatabaseType();
		}
		return null;
	}

	@Override
	public int getOrder() {
		return 0;
	}
}