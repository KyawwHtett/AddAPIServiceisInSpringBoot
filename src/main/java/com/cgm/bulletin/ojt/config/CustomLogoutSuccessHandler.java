package com.cgm.bulletin.ojt.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.cgm.bulletin.ojt.security.JwtTokenProvider;

/**
 * <h2>CustomLogoutSuccessHandler Class</h2>
 * <p>
 * Process for Displaying CustomLogoutSuccessHandler
 * </p>
 * 
 * @author KyawHtet
 *
 */
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {
	/**
	 * <h2>LOGGER</h2>
	 * <p>
	 * LOGGER
	 * </p>
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

	/**
	 * <h2>onLogoutSuccess</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param authentication
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	        throws IOException, ServletException {
		String refererUrl = request.getHeader("Header");
		LOGGER.info("Logout from: " + refererUrl);

		super.onLogoutSuccess(request, response, authentication);
	}
}