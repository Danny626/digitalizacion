package com.albo.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "com.albo.compusoft.*", entityManagerFactoryRef = "compusoftEntityManagerFactory", transactionManagerRef = "compusoftTransactionManager")
@ComponentScan(basePackages = { "com.albo.compusoft.*" })
@EntityScan("com.albo.compusoft.model")
public class CompusoftDAOConfig {

	@Value("${spring.datasource.compusoft.hibernate-hbm2ddl-auto}")
	private String ddlMode;

	@Bean
	public PlatformTransactionManager compusoftTransactionManager() {
		return new JpaTransactionManager(compusoftEntityManagerFactory().getObject());
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean compusoftEntityManagerFactory() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", ddlMode);
		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");

		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

		factoryBean.setDataSource(compusoftDataSource());
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
//		factoryBean.setPackagesToScan(CompusoftDAOConfig.class.getPackage().getName());
		factoryBean.setPackagesToScan("com.albo.compusoft.*");
		factoryBean.setJpaPropertyMap(properties);

		return factoryBean;
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.compusoft")
	public DataSource compusoftDataSource() {
		return DataSourceBuilder.create().build();
	}

}
