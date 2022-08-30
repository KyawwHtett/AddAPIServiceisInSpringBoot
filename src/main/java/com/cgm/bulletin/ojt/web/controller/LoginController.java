package com.cgm.bulletin.ojt.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.bulletin.ojt.bl.dto.UserDto;
import com.cgm.bulletin.ojt.bl.service.AuthenticationService;
import com.cgm.bulletin.ojt.bl.service.UserService;
import com.cgm.bulletin.ojt.web.form.LoginForm;

/**
 * <h2>LoginController Class</h2>
 * <p>
 * Process for Displaying LoginController
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Controller
public class LoginController {
	/**
	 * <h2>authService</h2>
	 * <p>
	 * authService
	 * </p>
	 */
	@Autowired
	private AuthenticationService authService;

	/**
	 * <h2>passwordEncoder</h2>
	 * <p>
	 * passwordEncoder
	 * </p>
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * <h2>userService</h2>
	 * <p>
	 * userService
	 * </p>
	 */
	@Autowired
	private UserService userService;

	/**
	 * <h2>session</h2>
	 * <p>
	 * session
	 * </p>
	 */
	@Autowired
	private HttpSession session;

	/**
	 * <h2>init</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return ModelAndView
	 */
	@GetMapping({ "/", "/login" })
	public ModelAndView init() {
		if (this.authService.doIsLoggedIn()) {
			return new ModelAndView("redirect:/post/list");
		}
		ModelAndView model = new ModelAndView("/user/login/login");
		model.addObject("loginForm", new LoginForm());
		return model;
	}

	/**
	 * <h2>login</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param loginForm
	 * @param result
	 * @return
	 * @return ModelAndView
	 */
	@PostMapping(value = "/login")
	public ModelAndView login(@Valid LoginForm loginForm, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView loginFormView = new ModelAndView("/user/login/login");
		if (result.hasErrors()) {
			return loginFormView;
		}
		if (!this.userService.doIsEmailExist(loginForm.getEmail())) {
			loginFormView.addObject("loginError", "Email doesn't exist");
			return loginFormView;
		}
		UserDto userDetail = this.userService.doFindUserByEmail(loginForm.getEmail());

		boolean passwordMatch = passwordEncoder.matches(loginForm.getPassword(), userDetail.getPassword());
		if (!passwordMatch) {
			loginFormView.addObject("loginError", "Invalid Login! Email and Password doesn't match!");
			return loginFormView;
		}
		this.authService.doLoadAuth(loginForm.getEmail());
		if (this.authService.doIsLoggedIn()) {
			loginFormView.setViewName("redirect:/post/list");
			session.setAttribute("LOGIN_USER", this.authService.doGetLoggedInUser());
		}
		return loginFormView;
	}

	/**
	 * <h2>logout</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param request
	 * @return
	 * @return String
	 */
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		this.session.removeAttribute("LOGIN_USER");
		this.authService.doLogout(request);
		return "redirect:/login";
	}

	/**
	 * <h2>denied</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	@GetMapping(value = "/accessDenied")
	public String denied() {
		return "/error/accessDenied";
	}
}