package com.cgm.bulletin.ojt.bl.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import com.cgm.bulletin.ojt.bl.dto.UserDto;
import com.cgm.bulletin.ojt.bl.service.AuthenticationService;
import com.cgm.bulletin.ojt.bl.service.UserService;
import com.cgm.bulletin.ojt.security.JwtAuthenticationEntryPoint;
import com.cgm.bulletin.ojt.web.form.UserForm;

/**
 * <h2>UserInterceptor Class</h2>
 * <p>
 * Process for Displaying UserInterceptor
 * </p>
 * 
 * @author KyawHtet
 *
 */
public class UserInterceptor implements HandlerInterceptor {
	/**
	 * <h2>LOGGER</h2>
	 * <p>
	 * LOGGER
	 * </p>
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

	/**
	 * <h2>authservice</h2>
	 * <p>
	 * authservice
	 * </p>
	 */
	@Autowired
	private AuthenticationService authservice;

	/**
	 * <h2>userService</h2>
	 * <p>
	 * userService
	 * </p>
	 */
	@Autowired
	private UserService userService;

	/**
	 * <h2>preHandle</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	        throws Exception {

		Map<String, String> path = (Map<String, String>) request
		        .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		int id = Integer.parseInt(path.get("id"));
		UserForm userDetail = this.userService.doGetUserById(id);
		UserDto userDto = this.authservice.doGetLoggedInUser();

		if (userDetail == null || Integer.parseInt(userDto.getType()) != 1) {
			if (userDetail == null || userDto.getId() != userDetail.getId()) {
				if (request.getRequestURI().contains("/api")) {
					LOGGER.error("Responding with permission not access error. Message - {}", HttpStatus.FORBIDDEN);
					response.sendError(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION,
					        "Sorry, You're not premission to access this resource User.");
				} else {
					response.sendRedirect(request.getContextPath() + "/accessDenied");
				}
			}
		}
		return true;
	}
}