package com.cgm.bulletin.ojt.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * <h2>CustomAccessDeniedHandler Class</h2>
 * <p>
 * Process for Displaying CustomAccessDeniedHandler
 * </p>
 * 
 * @author KyawHtet
 *
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
//	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

	/**
	 * <h2>handle</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param accessDeniedException
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
	        AccessDeniedException accessDeniedException) throws IOException, ServletException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Access Denined Access DeninedAccess DeninedAccess DeninedAccess DeninedAccess Denined");
		if (auth != null) {
			System.out.print(
			        "User: " + auth.getName() + " attempted to access the protected URL: " + request.getRequestURI());
		}
		if (request.getRequestURI().contains("/api")) {
			response.sendRedirect(request.getContextPath() + "/api/user/accessDenied");
//			LOGGER.error("Responding with permission not access error. Message - {}", HttpStatus.FORBIDDEN);
//			response.sendError(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION,
//			        "Sorry, You're not premission to access this resource.");
		} else {
			response.sendRedirect(request.getContextPath() + "/accessDenied");
		}
	}

}
