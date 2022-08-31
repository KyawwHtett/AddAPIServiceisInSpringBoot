package com.cgm.bulletin.ojt;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * <h2>BulletinBoardSpringBoot1Application Class</h2>
 * <p>
 * Process for Displaying BulletinBoardSpringBoot1Application
 * </p>
 * 
 * @author KyawHtet
 *
 */
@SpringBootApplication
public class BulletinBoardSpringBoot1Application {
	/**
	 * <h2>main</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
		SpringApplication.run(BulletinBoardSpringBoot1Application.class, args);
	}

	/**
	 * <h2>modelMapper</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return ModelMapper
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}