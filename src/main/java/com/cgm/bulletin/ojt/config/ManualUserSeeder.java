package com.cgm.bulletin.ojt.config;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cgm.bulletin.ojt.bl.service.UserService;
import com.cgm.bulletin.ojt.persistence.entity.User;

/**
 * <h2>ManualUserSeeder Class</h2>
 * <p>
 * Process for Displaying ManualUserSeeder
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Component
public class ManualUserSeeder {
	/**
	 * <h2>userService</h2>
	 * <p>
	 * userService
	 * </p>
	 */
	@Autowired
	private UserService userService;

	/**
	 * <h2>addInitialUsers</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return void
	 */
	@PostConstruct
	public void addInitialUsers() {
		if (userService.dbGetUserCount() <= 0) {
			// Admin
			User admin = new User();
			admin.setUsername("Kyaw Htet");
			admin.setEmail("cgm.kyawhtet@gmail.com");
			admin.setPassword("123456");
			admin.setGender("Male");
			admin.setType("1"); // ADMIN_ROLE
			admin.setCreated_at(new Date());
			admin.setUpdated_at(new Date());
			this.userService.doSaveUser(admin);

			// User
			User user = new User();
			user.setUsername("User");
			user.setEmail("kyawhtet@cgm-myanmar.com");
			user.setPassword("12345678");
			user.setGender("Female");
			user.setType("0"); // USER_ROLE
			user.setCreated_at(new Date());
			user.setUpdated_at(new Date());
			this.userService.doSaveUser(user);
		}
	}
}