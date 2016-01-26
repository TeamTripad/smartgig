package com.smartgig.config;

import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.smartgig.*" })
@EnableTransactionManagement
@Import({ SecurityConfig.class })
public class AppConfig extends WebMvcConfigurerAdapter{

//	@Bean
//	public SessionFactory sessionFactory() {
//		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
//		builder.scanPackages("com.smartgig.users.model").addProperties(getHibernateProperties());
//
//		return builder.buildSessionFactory();
//	}

	private Properties getHibernateProperties() {
		Properties prop = new Properties();
		prop.put("hibernate.format_sql", "true");
		prop.put("hibernate.show_sql", "true");
		prop.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return prop;
	}

//	@Bean(name = "dataSource")
//	public BasicDataSource dataSource() {
//		DbConnect db = new DbConnect();
//		db.createTables();
//
//		BasicDataSource ds = new BasicDataSource();
//		ds.setDriverClassName("com.mysql.jdbc.Driver");
//		ds.setUrl("jdbc:mysql://localhost:3306/smartgig");
//		ds.setUsername("root");
//		return ds;
//	}

	// Create a transaction manager
//	@Bean
//	public HibernateTransactionManager txManager() {
//		return new HibernateTransactionManager(sessionFactory());
//	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/");
		//viewResolver.setPrefix("/WEB-INF/views/");
		//viewResolver.setPrefix("/WEB-INF/views/Admin/");
		//viewResolver.setPrefix("/WEB-INF/views/E-commerce/");
		//viewResolver.setPrefix("/WEB-INF/views/SmartGIG/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/js/**").addResourceLocations("/resources/js/*");
        registry.addResourceHandler("/resources/css/**").addResourceLocations("/resources/css/*");
        registry.addResourceHandler("/resources/img/**").addResourceLocations("/resources/img/*");
        registry.addResourceHandler("/resources/fonts/**").addResourceLocations("/resources/fonts/*");
        registry.addResourceHandler("/resources/font-awesome/**").addResourceLocations("/resources/font-awesome/*");
        registry.addResourceHandler("/resources/less/**").addResourceLocations("/resources/less/*");
    }

}