package com.example.demo;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class CouponsApplication extends SpringBootServletInitializer{

	private @Autowired AutowireCapableBeanFactory beanFactory;
	
	public static void main(String[] args) {
		SpringApplication.run(CouponsApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean filterRegistrationBean(){
		
		FilterRegistrationBean bean = new FilterRegistrationBean();
		Filter filter = new LoginFilter();
		beanFactory.autowireBean(filter);
		
		bean.setFilter(filter);
		bean.addUrlPatterns("/Admin/index.html");
		bean.addUrlPatterns("/Company/index.html");
		bean.addUrlPatterns("/Customer/index.html");
		
		return bean;
	}
}
