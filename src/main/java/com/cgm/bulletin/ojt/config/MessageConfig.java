package com.cgm.bulletin.ojt.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * <h2>MessageConfig Class</h2>
 * <p>
 * Process for Displaying MessageConfig
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Configuration
public class MessageConfig {
	/**
	 * <h2>MESSAGE_SOURCE_VALID_PATH</h2>
	 * <p>
	 * MESSAGE_SOURCE_VALID_PATH
	 * </p>
	 */
	private static final String MESSAGE_SOURCE_VALID_PATH = "classpath:/validationMessage";
	/**
	 * <h2>MESSAGE_SOURCE_PATH</h2>
	 * <p>
	 * MESSAGE_SOURCE_PATH
	 * </p>
	 */
	private static final String MESSAGE_SOURCE_PATH = "classpath:/messages";

	/**
	 * <h2>messageSource</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return MessageSource
	 */
	@Bean
	MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames(MESSAGE_SOURCE_VALID_PATH, MESSAGE_SOURCE_PATH);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
}