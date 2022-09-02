package com.cgm.bulletin.ojt.web.api.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgm.bulletin.ojt.bl.dto.UserDto;
import com.cgm.bulletin.ojt.bl.service.ApiPostService;
import com.cgm.bulletin.ojt.bl.service.ApiUserService;
import com.cgm.bulletin.ojt.bl.service.AuthenticationService;
import com.cgm.bulletin.ojt.payload.request.UserRequest;
import com.cgm.bulletin.ojt.payload.response.ApiResponse;
import com.cgm.bulletin.ojt.payload.response.JwtAuthenticationResponse;
import com.cgm.bulletin.ojt.payload.response.PostResponse;
import com.cgm.bulletin.ojt.payload.response.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * <h2>ApiUserController Class</h2>
 * <p>
 * Process for Displaying ApiUserController
 * </p>
 * 
 * @author KyawHtet
 *
 */
@RestController
@RequestMapping(value = "/api/user")
public class ApiUserController {
	/**
	 * <h2>authService</h2>
	 * <p>
	 * authService
	 * </p>
	 */
	@Autowired
	private AuthenticationService authService;

	/**
	 * <h2>apiUserService</h2>
	 * <p>
	 * apiUserService
	 * </p>
	 */
	@Autowired
	private ApiUserService apiUserService;

	/**
	 * <h2>apiPostService</h2>
	 * <p>
	 * apiPostService
	 * </p>
	 */
	@Autowired
	private ApiPostService apiPostService;

	/**
	 * <h2>showUserList</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return ResponseEntity<?>
	 */
	@GetMapping(value = "/list")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> showUserList() {
		List<UserResponse> users = this.apiUserService.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	/**
	 * <h2>createUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userRequest
	 * @return
	 * @throws JsonProcessingException
	 * @return ResponseEntity<?>
	 */
	@PostMapping({ "/create", "/register" })
	public ResponseEntity<?> createUser(@Valid @ModelAttribute UserRequest userRequest) throws JsonProcessingException {
		if (this.apiUserService.doApiIsEmailExist(userRequest.getEmail())) {
			ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Email has already Taken.");
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		}
		UserResponse userResponse = this.apiUserService.doApiSaveUser(userRequest);
		if (!(this.authService.doIsLoggedIn())) {
			this.authService.doLoadAuth(userRequest.getEmail());
		}
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}

	/**
	 * <h2>editUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @return
	 * @return ResponseEntity<?>
	 */
	@GetMapping(value = "/edit/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> editUser(@PathVariable("id") int userId) {
		UserResponse userResponse = this.apiUserService.doApiGetUserById(userId);
		if (userResponse == null) {
			return new ResponseEntity<>(new ApiResponse(Boolean.FALSE, "You don't have permission to this route."),
			        HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}

	/**
	 * <h2>updateUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userRequest
	 * @return
	 * @return ResponseEntity<?>
	 */
	@PatchMapping(value = "/update")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> updateUser(@Valid @ModelAttribute UserRequest userRequest) {
		UserResponse oldUserForm = this.apiUserService.doApiGetUserById(userRequest.getId());
		if (oldUserForm == null) {
			return new ResponseEntity<>(new ApiResponse(Boolean.FALSE, "Invalid User"), HttpStatus.OK);
		}
		if (!oldUserForm.getEmail().equals(userRequest.getEmail())
		        && this.apiUserService.doApiIsEmailExist(userRequest.getEmail())) {
			ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Email Already Used.");
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		}
		UserResponse userResponse = this.apiUserService.doApiUpdateUser(userRequest);

		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}

	/**
	 * <h2>deletePost</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @return
	 * @return ResponseEntity<?>
	 */
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deletePost(@PathVariable("id") int userId) {
		ApiResponse apiResponse = this.apiUserService.doDeleteUser(userId);
		return new ResponseEntity<>(apiResponse, apiResponse.getSuccess() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
	}

	/**
	 * <h2>logout</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param request
	 * @param response
	 * @return
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
		String jwt = this.authService.doApiLogout(request, response);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	/**
	 * <h2>downloadExcel</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @return ResponseEntity<?>
	 */
	@GetMapping(value = "/download")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> downloadExcel(HttpServletRequest request, HttpServletResponse response)
	        throws IOException {
		String downloadLink = getBaseUrl(request) + request.getServletPath();
		String url = downloadLink.replace("/api", "");
		ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, url);
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	/**
	 * <h2>userProfile</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return ResponseEntity<?>
	 */
	@GetMapping(value = "/profile")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> userProfile() {
		UserDto loginUser = this.authService.doGetLoggedInUser();
		UserResponse responseUser = new UserResponse(loginUser);
		List<PostResponse> setList = this.apiPostService.doGetAllPostsByUser(loginUser.getId());
		responseUser.setListPost(setList);
		return new ResponseEntity<>(responseUser, HttpStatus.OK);
	}

	/**
	 * <h2>denied</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/accessDenied")
	public ResponseEntity<?> denied() {
		ApiResponse response = new ApiResponse(Boolean.FALSE, "Sorry, You're not premission to access this resource.");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * <h2>getBaseUrl</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param request
	 * @return
	 * @return String
	 */
	private String getBaseUrl(HttpServletRequest request) {
		String url = request.getScheme() + "://" + request.getServerName();
		if (request.getServerPort() != 0) {
			url = url + ":" + request.getServerPort();
		}
		url = url + request.getContextPath();
		return url;
	}
}