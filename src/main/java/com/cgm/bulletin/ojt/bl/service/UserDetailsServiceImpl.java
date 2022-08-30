package com.cgm.bulletin.ojt.bl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgm.bulletin.ojt.bl.dto.UserDto;
import com.cgm.bulletin.ojt.web.form.UserForm;

/**
 * <h2>UserDetailsServiceImpl Class</h2>
 * <p>
 * Process for Displaying UserDetailsServiceImpl
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
	/**
	 * <h2>userService</h2>
	 * <p>
	 * userService
	 * </p>
	 */
	@Autowired
	private UserService userService;

	/**
	 * <h2>loadUserByUsername</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param email
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserDto userDto = userService.doFindUserByEmail(email);
		if (userDto == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new User(userDto.getEmail(), userDto.getPassword(), authorities(userDto.getType()));
	}
	
	@Transactional(readOnly = true)
	public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
		UserForm userForm = userService.doGetUserById(id.intValue());
		if (userForm == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new User(userForm.getEmail(), userForm.getPassword(), authorities(userForm.getType()));
	}

	/**
	 * <h2>authorities</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param type
	 * @return
	 * @return List<GrantedAuthority>
	 */
	private List<GrantedAuthority> authorities(String type) {
		List<GrantedAuthority> setAuths = new ArrayList<GrantedAuthority>();
		if (Integer.parseInt(type) == 0) {
			setAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
		} else if (Integer.parseInt(type) == 1) {
			setAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		return setAuths;
	}
}