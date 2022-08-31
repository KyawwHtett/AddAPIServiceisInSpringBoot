package com.cgm.bulletin.ojt.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cgm.bulletin.ojt.bl.service.UserDetailsServiceImpl;

/**
 * <h2>JwtAuthenticationFilter Class</h2>
 * <p>
 * Process for Displaying JwtAuthenticationFilter
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	/**
	 * <h2>LOGGER</h2>
	 * <p>
	 * LOGGER
	 * </p>
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	/**
	 * <h2>tokenProvider</h2>
	 * <p>
	 * tokenProvider
	 * </p>
	 */
	@Autowired
	private JwtTokenProvider tokenProvider;
	/**
	 * <h2>userDetailsServiceImpl</h2>
	 * <p>
	 * userDetailsServiceImpl
	 * </p>
	 */
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	/**
	 * <h2>doFilterInternal</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {
		try {
			String jwt = getJwtFromRequest(request);

			if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
				Long userId = tokenProvider.getUserIdFromJWT(jwt);

				UserDetails userDetails = userDetailsServiceImpl.loadUserById(userId);
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				        userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		} catch (Exception ex) {
			LOGGER.error("Could not set user authentication in security context", ex);
		}
		filterChain.doFilter(request, response);
	}

	/**
	 * <h2>getJwtFromRequest</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param request
	 * @return
	 * @return String
	 */
	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
}