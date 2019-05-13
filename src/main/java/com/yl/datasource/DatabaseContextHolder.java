package com.yl.datasource;


/**
 * 
 * @author yuanli   
 * @date 2017年5月22日 上午11:26:12
 * 作用:保存一个线程安全带的DatebaseType容器
 */
public class DatabaseContextHolder {
	private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

	public static void setDatabaseType(DatabaseType type) {
		contextHolder.set(type);
	}

	public static DatabaseType getDatabaseType() {
		return  contextHolder.get();
	}
	
	public static void clearnDatabaseType() {
		contextHolder.remove();
	}
}
