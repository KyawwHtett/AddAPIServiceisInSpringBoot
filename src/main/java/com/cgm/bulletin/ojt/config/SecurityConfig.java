package com.cgm.bulletin.ojt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.cgm.bulletin.ojt.bl.service.UserDetailsServiceImpl;
import com.cgm.bulletin.ojt.security.JwtAuthenticationEntryPoint;
import com.cgm.bulletin.ojt.security.JwtAuthenticationFilter;

/**
 * <h2>SecurityConfig Class</h2>
 * <p>
 * Process for Displaying SecurityConfig
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig {
	private final JwtAuthenticationEntryPoint unauthorizedHandler;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	/**
	 * <h2>userDetailsService</h2>
	 * <p>
	 * userDetailsService
	 * </p>
	 */
	@Qualifier("userDetailsServiceImpl")
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public SecurityConfig(UserDetailsServiceImpl customUserDetailsService,
	        JwtAuthenticationEntryPoint unauthorizedHandler, JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.unauthorizedHandler = unauthorizedHandler;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

	/**
	 * <h2>passwordEncoder</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return PasswordEncoder
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * <h2>accessDeniedHandler</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return AccessDeniedHandler
	 */
	@Bean
	AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	/**
	 * <h2>logoutSuccessHandler</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return LogoutSuccessHandler
	 */
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new CustomLogoutSuccessHandler();
	}

	/**
	 * <h2>apiFilterChain</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param http
	 * @return
	 * @throws Exception
	 * @return SecurityFilterChain
	 */
	@Bean
	SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
			.accessDeniedHandler(accessDeniedHandler())
			.and()
			.antMatcher("/api/**") // <= Security only available
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER")
			.antMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.headers().frameOptions().sameOrigin();
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	/**
	 * <h2>filterChain</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param http
	 * @return
	 * @throws Exception
	 * @return SecurityFilterChain
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			.cors().and().csrf()
			.and()
			.exceptionHandling().accessDeniedHandler(accessDeniedHandler())
			.and().authorizeRequests()
			.antMatchers(
					"/", "/login", "/user/register", "/user/registerConfirm", 
					"/password/reset", "/password/reset/sendMail", 
					"/password/reset/sendMail/*", "/password/changeReset").permitAll()
			.antMatchers("/post/list").hasAnyRole("ADMIN", "USER")
			.antMatchers("/user/list").hasRole("ADMIN")
			.antMatchers("/user/create").hasRole("ADMIN")
			.antMatchers("/user/download")
			.hasRole("ADMIN").antMatchers("/category/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/j_login")
			.defaultSuccessUrl("/user/list", true)
			.failureUrl("/login?error=true")
			.usernameParameter("email")
			.passwordParameter("password")
			.and()
			.logout()
			.logoutUrl("/logout").logoutSuccessUrl("/login");                          ;

		http.headers().frameOptions().sameOrigin();

		return http.build();
	}

	/**
	 * <h2>webSecurityCustomizer</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return WebSecurityCustomizer
	 */
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/css/**", "/js/**");
	}

	/**
	 * <h2>configureGlobal</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param auth
	 * @throws Exception
	 * @return void
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	// Used by spring security if CORS is enabled.
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

//   @Bean(value = BeanIds.AUTHENTICATION_MANAGER)
//	 public AuthenticationManager authenticationManagerBean() throws Exception {
//		return authenticationManagerBean();
//	}
}