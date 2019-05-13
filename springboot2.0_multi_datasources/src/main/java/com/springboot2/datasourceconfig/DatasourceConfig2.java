package com.springboot2.datasourceconfig;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * 数据源配置
 * @author admin
 *
 */
@Configuration
@MapperScan(basePackages= {"com.springboot2.test2.dao"},sqlSessionFactoryRef="test2SqlSessionFactory")
public class DatasourceConfig2 {

	/**
	 * 配置test2数据库
	 * @return
	 */
	@Bean(name="test2DataSource")
	@ConfigurationProperties(prefix="spring.datasource.test2")
	public DataSource test2DataSource() {
		return DataSourceBuilder.create().build();
	}
	
	/**
	 * 创建sqlsessinfactory会话工厂
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	@Bean(name="test2SqlSessionFactory")
	public SqlSessionFactory test2SqlSessionFactory(@Qualifier("test2DataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		//加载mapping文件 
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
			.getResources("classpath:com/springboot2/test2/mapping/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	/**
	 * 事务管理
	 */
	@Bean(name="test2TransactionManager")
	public DataSourceTransactionManager test2TransactionManager(@Qualifier("test2DataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	/**
	 * 创建SqlSessionTemplate
	 * @param sqlSessionFactory
	 * @return
	 */
	@Bean(name="test2SqlSessionTemplate")
	public SqlSessionTemplate test2SqlSessionTemplate(@Qualifier("test2SqlSessionFactory") SqlSessionFactory sqlSessionFactory){
		return new SqlSessionTemplate(sqlSessionFactory);
		
	}
}
