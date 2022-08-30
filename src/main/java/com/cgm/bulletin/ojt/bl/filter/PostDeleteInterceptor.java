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

import com.cgm.bulletin.ojt.bl.dto.PostDto;
import com.cgm.bulletin.ojt.bl.dto.UserDto;
import com.cgm.bulletin.ojt.bl.service.AuthenticationService;
import com.cgm.bulletin.ojt.bl.service.PostService;
import com.cgm.bulletin.ojt.security.JwtAuthenticationEntryPoint;

/**
 * <h2>PostDeleteInterceptor Class</h2>
 * <p>
 * Process for Displaying PostDeleteInterceptor
 * </p>
 * 
 * @author KyawHtet
 *
 */
public class PostDeleteInterceptor implements HandlerInterceptor {
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
	 * <h2>postService</h2>
	 * <p>
	 * postService
	 * </p>
	 */
	@Autowired
	private PostService postService;

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
		PostDto postDto = this.postService.doGetPostById(id);
		UserDto userDto = this.authservice.doGetLoggedInUser();

		if (Integer.parseInt(userDto.getType()) != 1) {
			if (userDto.getId() != postDto.getCreated_user_id()) {
				if (request.getRequestURI().contains("/api")) {
					LOGGER.error("Responding with permission not access error. Message - {}", HttpStatus.FORBIDDEN);
					response.sendError(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION,
					        "Sorry, You're not premission to delete this resource Post.");
				} else {
					response.sendRedirect(request.getContextPath() + "/accessDenied");
				}
			}
		}
		return true;
	}
}