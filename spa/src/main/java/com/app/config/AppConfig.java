package com.app.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
 
@Configuration
@ComponentScan({ "com.app" })
@PropertySource({"classpath:application.properties"})
public class AppConfig {
	
	@Autowired
    private static Environment env;
 
	@Bean
	public static PropertySourcesPlaceholderConfigurer xxxpropertyConfig() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	public static String getValue(String key)
	{
		
		return env.getProperty(key);
	}
	
	@Bean
	public MessageSource messageSource() {
		Locale.setDefault(Locale.ENGLISH);
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.addBasenames("classpath:messages/messages");
	    return messageSource;
	}

}