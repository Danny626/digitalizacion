package com.albo.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "com.albo.dao.digitalizacion", entityManagerFactoryRef = "digitalizacionEntityManagerFactory", transactionManagerRef = "digitalizacionTransactionManager")
public class DigitalizacionDAOConfig {

	@Value("${spring.datasource.digitalizacion.hibernate-hbm2ddl-auto}")
	private String ddlMode;

	@Primary
	@Bean
	public PlatformTransactionManager digitalizacionTransactionManager() {
		return new JpaTransactionManager(digitalizacionEntityManagerFactory().getObject());
	}

	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean digitalizacionEntityManagerFactory() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", ddlMode);
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");

		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

		factoryBean.setDataSource(digitalizacionDataSource());
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		factoryBean.setPackagesToScan(DigitalizacionDAOConfig.class.getPackage().getName());
		factoryBean.setJpaPropertyMap(properties);

		return factoryBean;
	}

	@Primary
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.digitalizacion")
	public DataSource digitalizacionDataSource() {
		return DataSourceBuilder.create().build();
	}

}
