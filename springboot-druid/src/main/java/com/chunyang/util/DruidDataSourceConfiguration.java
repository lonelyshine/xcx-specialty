package com.chunyang.util;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 配置数据源，将application中的前缀为"spring.datasource"的数据源信息添加到datasource
 * @author qinxuegang
 */
@Configuration
public class DruidDataSourceConfiguration {
	 @Bean  
	    @ConfigurationProperties(prefix = "spring.datasource")  
	    public DataSource druidDataSource() {  
	        DruidDataSource druidDataSource = new DruidDataSource();  
	        return druidDataSource;  
	    }  
}
