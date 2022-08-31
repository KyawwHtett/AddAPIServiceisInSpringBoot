package com.cgm.bulletin.ojt.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * <h2>JwtAuthenticationEntryPoint Class</h2>
 * <p>
 * Process for Displaying JwtAuthenticationEntryPoint
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	/**
	 * <h2>LOGGER</h2>
	 * <p>
	 * LOGGER
	 * </p>
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

	/**
	 * <h2>commence</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param e
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
	        AuthenticationException e) throws IOException, ServletException {
		LOGGER.error("Responding with unauthorized error. Message - {}", e.getMessage());
		httpServletResponse.sendError(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION,
		        "Sorry, You're not authorized to access this resource.");
	}
}