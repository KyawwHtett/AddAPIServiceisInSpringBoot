package com.cgm.bulletin.ojt.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <h2>HibernateConf Class</h2>
 * <p>
 * Process for Displaying HibernateConf
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class) // required for mapping
@PropertySources(value = { @PropertySource("classpath:application.properties") })
public class HibernateConf {
	/**
	 * <h2> environment</h2>
	 * <p>
	 * environment
	 * </p>
	 */
	@Autowired
	private Environment environment;

	/**
	 * <h2>sessionFactory</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return LocalSessionFactoryBean
	 */
	@Bean
	LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("com.cgm.bulletin.ojt");
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	/**
	 * <h2>dataSource</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return DataSource
	 */
	@Bean
	DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(environment.getProperty("spring.datasource.url"));
		dataSource.setUsername(environment.getProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource.password"));

		return dataSource;
	}

	/**
	 * <h2>hibernateTransactionManager</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return PlatformTransactionManager
	 */
	@Bean
	PlatformTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	/**
	 * <h2>hibernateProperties</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return Properties
	 */
	private final Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto",
		        environment.getProperty("spring.jpa.hibernate.ddl-auto"));
		hibernateProperties.setProperty("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"));
		hibernateProperties.setProperty("hibernate.dialect",
		        environment.getProperty("spring.jpa.properties.hibernate.dialect"));
		hibernateProperties.setProperty("hibernate.current_session_context_class",
		        environment.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));

		return hibernateProperties;
	}
}