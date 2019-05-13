package com.yl.config;

import com.yl.datasource.DatabaseType;
import com.yl.datasource.DynamicDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * mybatis 配置类
 * @author yuanli 
 * @date 2017年8月25日 上午8:54:56
 */
@Configuration
@MapperScan(basePackages = "com.wgmf.jz.mapper")
public class MyBatisConfig {
	@Autowired
	private Environment environment;

	//微信库
	@Bean(name = "wxDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.wx")
	public DataSource wxDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "mysqlDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.mysql")
	public DataSource mysqlDataSource() {
		return DataSourceBuilder.create().build();
	}
	

	@Primary
	@Bean(name="dynamicDataSource")
	@DependsOn({"wxDataSource"})
	public DynamicDataSource dataSource() {
		Map<Object, Object> targetDataSources = new HashMap<>();
		//targetDataSources.put(DatabaseType.MAINDATASOURCE, mainDataSource());
		targetDataSources.put(DatabaseType.WXDATASOURCE, wxDataSource());
		targetDataSources.put(DatabaseType.MYSQLDATASOURCE, mysqlDataSource());
		
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		dynamicDataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
		dynamicDataSource.setDefaultTargetDataSource(wxDataSource());// 默认的datasource设置为mainDataSource
		return dynamicDataSource;
	}

	/**
	 * 根据数据源创建SqlSessionFactory
	 * @throws IOException 
	 * @throws Exception 
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DynamicDataSource ds) throws Exception {
		SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
		fb.setDataSource(ds);// 指定数据源(这个必须有，否则报错)
		// 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
		fb.setTypeAliasesPackage("com.wgmf.jz.domain");// 指定基包
		/**
		 * 在多数据源的模式下，使用pagehelper-spring-boot-starter的方式启动分页插件有问题，必须手动引入
		 * 在分页插件4.X版本使用的是PageHelper对象
		 * 在分页插件5.X版本使用的是com.github.pagehelper.PageInterceptor对象
		 */
		com.github.pagehelper.PageInterceptor pageHelper = new com.github.pagehelper.PageInterceptor();
		Properties properties = new Properties();
		properties.setProperty("reasonable", "true");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("returnPageInfo", "check");
		properties.setProperty("params", "count=countSql");
		properties.setProperty("autoRuntimeDialect", "true");
		properties.setProperty("closeConn", "true");
		pageHelper.setProperties(properties);
		fb.setPlugins(new Interceptor[] { pageHelper });
		// 添加XML目录
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		// 指定Mapper文件的位置
		fb.setMapperLocations(resolver.getResources("classpath:mapper/*Mapper.xml"));
		return fb.getObject();
	}

	/**
	 * 配置事务管理器
	 */
	@Bean
	public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
}
