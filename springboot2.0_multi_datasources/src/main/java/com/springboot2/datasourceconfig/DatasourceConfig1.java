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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * 数据源配置
 * @author admin
 *
 */
@Configuration
@MapperScan(basePackages= "com.springboot2.test1.dao",sqlSessionFactoryRef="test1SqlSessionFactory") //注意：com.springboot2.test1.dao是dao类的包名！！
public class DatasourceConfig1 {

	/**
	 * 配置test1数据库
	 * @return
	 */
	@Bean(name="test1DataSource")
	@ConfigurationProperties(prefix="spring.datasource.test1")
	@Primary
	public DataSource test1DataSource() {
		return DataSourceBuilder.create().build();
	}
	
	/**
	 * 创建sqlsessinfactory会话工厂
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	@Bean(name="test1SqlSessionFactory")
	@Primary
	public SqlSessionFactory test1SqlSessionFactory(@Qualifier("test1DataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		//加载mapping文件 
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
			.getResources("classpath:com/springboot2/test1/mapping/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	/**
	 * 事务管理
	 */
	@Bean(name="test1TransactionManager")
	@Primary
	public DataSourceTransactionManager test1TransactionManager(@Qualifier("test1DataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	/**
	 * 创建SqlSessionTemplate
	 * @param sqlSessionFactory
	 * @return
	 */
	@Bean(name="test1SqlSessionTemplate")
	@Primary
	public SqlSessionTemplate test1SqlSessionTemplate(@Qualifier("test1SqlSessionFactory") SqlSessionFactory sqlSessionFactory){
		return new SqlSessionTemplate(sqlSessionFactory);
		
	}
}