package com.github.olivier.ccmanager.configuration;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.validator.routines.CreditCardValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;

/**
 * Application beans and necessary configuration
 * @author odonn
 *
 */
@Configuration
public class CreditCardManagerConfig {

	@Bean
	public CreditCardValidator creditCardValidator() {
		return new CreditCardValidator();
	}
	
	@Bean
	public SimpleDateFormat simpleDateFormat(){
		return new SimpleDateFormat("MM/yy");
	}
	
	@Bean
	public Calendar calendar() {
		return Calendar.getInstance();
	}
	
	@Bean
	public Gson gson() {
	    return new Gson();
	}
	
}
