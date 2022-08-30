package com.cgm.bulletin.ojt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cgm.bulletin.ojt.bl.filter.PostDeleteInterceptor;
import com.cgm.bulletin.ojt.bl.filter.PostInterceptor;
import com.cgm.bulletin.ojt.bl.filter.UserInterceptor;

/**
 * <h2>WebConfiguration Class</h2>
 * <p>
 * Process for Displaying WebConfiguration
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	@Value("cors.allowedOrings")
	private String allowedOrigins;

	/**
	 * <h2>postInterceptor</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return PostInterceptor
	 */
	@Bean
	PostInterceptor postInterceptor() {
		return new PostInterceptor();
	}

	/**
	 * <h2>postDeleteInterceptor</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return PostDeleteInterceptor
	 */
	@Bean
	PostDeleteInterceptor postDeleteInterceptor() {
		return new PostDeleteInterceptor();
	}

	/**
	 * <h2>userInterceptor</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return UserInterceptor
	 */
	@Bean
	UserInterceptor userInterceptor() {
		return new UserInterceptor();
	}
	
	public void addCorsMappings(CorsRegistry registry) {
		final long MAX_AGE_SECS = 3600;

		registry.addMapping("/**")
				.allowedOrigins(allowedOrigins)
				.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
				.allowedHeaders("*")
				.maxAge(MAX_AGE_SECS);
	}

	/**
	 * <h2>addInterceptors</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(postInterceptor()).addPathPatterns("/post/edit/*", "/post/edit/profile/*", "/api/post/edit/*");
		registry.addInterceptor(postDeleteInterceptor()).addPathPatterns("/post/delete/*", "/profile/post/delete/*", "/api/post/delete/*");
		registry.addInterceptor(userInterceptor()).addPathPatterns("/user/edit/*", "/api/user/edit/*");
	}
}