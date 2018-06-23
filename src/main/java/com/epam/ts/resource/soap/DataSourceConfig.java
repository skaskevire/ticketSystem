package com.epam.ts.resource.soap;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
    @Bean
    public DataSource dataSource()
    {
        BasicDataSource ds = new org.apache.commons.dbcp.BasicDataSource();
        ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        ds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        ds.setUsername("system");
        ds.setPassword("admin");

        return ds;
    }

    @Bean
    public AbstractPlatformTransactionManager txManager(DataSource dataSource)
    {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);

    return dataSourceTransactionManager;
    }
}
