/**
 * 
 */
package com.github.olivier.ccmanager.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Web configuration
 * @author odonn
 *
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("creditcardmanager");
		registry.addViewController("/manager").setViewName("creditcardmanager");
		registry.addViewController("/login").setViewName("login");
	}
}
