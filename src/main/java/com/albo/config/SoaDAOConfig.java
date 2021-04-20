package com.albo.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "com.albo.dao.soa", entityManagerFactoryRef = "soaEntityManagerFactory", transactionManagerRef = "soaTransactionManager")
public class SoaDAOConfig {

	@Value("${spring.datasource.soa.hibernate-hbm2ddl-auto}")
	private String ddlMode;

	@Bean
	public PlatformTransactionManager soaTransactionManager() {
		return new JpaTransactionManager(soaEntityManagerFactory().getObject());
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean soaEntityManagerFactory() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", ddlMode);
		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");

		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

		factoryBean.setDataSource(soaDataSource());
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		factoryBean.setPackagesToScan(SoaDAOConfig.class.getPackage().getName());
		factoryBean.setJpaPropertyMap(properties);

		return factoryBean;
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.soa")
	public DataSource soaDataSource() {
		return DataSourceBuilder.create().build();
	}

}
