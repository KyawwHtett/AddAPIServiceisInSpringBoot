package com.cgm.bulletin.ojt.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.bulletin.ojt.bl.dto.PostDto;
import com.cgm.bulletin.ojt.bl.dto.UserDto;
import com.cgm.bulletin.ojt.bl.service.AuthenticationService;
import com.cgm.bulletin.ojt.bl.service.PostService;
import com.cgm.bulletin.ojt.bl.service.UserService;
import com.cgm.bulletin.ojt.persistence.entity.User;
import com.cgm.bulletin.ojt.web.form.PostForm;
import com.cgm.bulletin.ojt.web.form.UserForm;

/**
 * <h2>UserController Class</h2>
 * <p>
 * Process for Displaying UserController
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Controller
public class UserController {
	/**
	 * <h2>userService</h2>
	 * <p>
	 * userService
	 * </p>
	 */
	@Autowired
	UserService userService;

	@Autowired
	PostService postService;

	/**
	 * <h2>messageSource</h2>
	 * <p>
	 * messageSource
	 * </p>
	 */
	@Autowired
	MessageSource messageSource;

	/**
	 * <h2>authService</h2>
	 * <p>
	 * authService
	 * </p>
	 */
	@Autowired
	AuthenticationService authService;

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;

	/**
	 * <h2>showUserList</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param model
	 * @return
	 * @return String
	 */
	@GetMapping({ "/user/list" })
	public String showUserList(Model model) {
		List<User> users = this.userService.findAll();
		UserDto userDto = this.authService.doGetLoggedInUser();
		model.addAttribute("users", users);
		model.addAttribute("loginUser", userDto);
		return "/user/list/list";
	}

	/**
	 * <h2>createUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return ModelAndView
	 */
	@GetMapping({ "/user/create", "/user/register" })
	public ModelAndView createUser() {
		ModelAndView mv = new ModelAndView("/user/create/createUser");
		UserForm userForm = new UserForm();
		if (this.authService.doIsLoggedIn()) {
			mv.addObject("url", "/user/createConfirm");
		} else {
			mv.addObject("url", "/user/registerConfirm");
		}
		mv.addObject("userForm", userForm);
		return mv;
	}

	/**
	 * <h2>createUserConfirm</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userForm
	 * @param result
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = { "/user/createConfirm",
	        "/user/registerConfirm" }, params = "confirm", method = RequestMethod.POST)
	public ModelAndView createUserConfirm(@Valid UserForm userForm, BindingResult result) {
		ModelAndView mv = new ModelAndView("/user/create/createUser");
		if (result.hasErrors()) {
			return mv;
		} else if (this.userService.doIsEmailExist(userForm.getEmail())) {
			mv.addObject("exist_email", messageSource.getMessage("U001", null, null));
			return mv;
		}
		mv.setViewName("user/create/createUserConfirm");
		mv.addObject("userForm", userForm);
		if (this.authService.doIsLoggedIn()) {
			mv.addObject("url", "/user/save");
		} else {
			mv.addObject("url", "/user/register");
		}
		return mv;
	}

	/**
	 * <h2>backCreateUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = { "/user/createConfirm", "/user/editConfirm", "profile/user/editConfirm",
	        "/user/registerConfirm" }, params = "back", method = RequestMethod.POST)
	public ModelAndView backCreateUser() {
		ModelAndView mv = new ModelAndView("redirect:/user/list");
		if (!authService.doIsLoggedIn()) {
			mv.setViewName("redirect:/login");
			return mv;
		}
		UserDto userDto = this.authService.doGetLoggedInUser();
		if (userDto != null && userDto.getType().equals("0")) {
			mv.setViewName("redirect:/post/list");
		}
		if (this.isProfile()) {
			mv.setViewName("redirect:/user/profile");
		}
		return mv;
	}

	/**
	 * <h2>saveCreateUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userForm
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = { "/user/save", "/user/register" }, params = "create", method = RequestMethod.POST)
	public ModelAndView saveCreateUser(UserForm userForm) {
		User user = new User(userForm);
		user.setCreated_at(new Date());
		this.userService.doSaveUser(user);
		ModelAndView mv = new ModelAndView("redirect:/user/list");
		if (!(this.authService.doIsLoggedIn())) {
			this.authService.doLoadAuth(userForm.getEmail());
			mv.setViewName("redirect:/post/list");
		}
		return mv;
	}

	/**
	 * <h2>backCreateUserConfirm</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userForm
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = { "/user/save", "/user/register" }, params = "back", method = RequestMethod.POST)
	public ModelAndView backCreateUserConfirm(UserForm userForm) {
		ModelAndView mv = new ModelAndView("/user/create/createUser");
		mv.addObject("userForm", userForm);
		if (this.authService.doIsLoggedIn()) {
			mv.addObject("url", "/user/createConfirm");
		} else {
			mv.addObject("url", "/user/registerConfirm");
		}
		return mv;
	}

	/**
	 * <h2>editUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = { "/user/edit/{id}", "/profile/user/edit/{id}" }, method = RequestMethod.GET)
	public ModelAndView editUser(@PathVariable("id") int userId) {
		ModelAndView editUserForm = new ModelAndView("/user/edit/editUser");
		UserDto userDto = this.authService.doGetLoggedInUser();
		UserForm userForm = this.userService.doGetUserById(userId);
		if (userForm == null) {
			return null;
		} else {
			editUserForm.addObject("userForm", userForm);
			editUserForm.addObject("loginUser", userDto);
		}
		if (isProfile()) {
			editUserForm.addObject("url", "/profile/user/editConfirm");
		}
		return editUserForm;
	}

	/**
	 * <h2>editUserConfirm</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userForm
	 * @param result
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = { "/user/editConfirm",
	        "/profile/user/editConfirm" }, params = "confirm", method = RequestMethod.POST)
	public ModelAndView editUserConfirm(@Valid UserForm userForm, BindingResult result) {
		ModelAndView editUserForm = new ModelAndView("/user/edit/editUser");
		UserForm oldUserForm = this.userService.doGetUserById(userForm.getId());
		UserDto userDto = this.authService.doGetLoggedInUser();
		editUserForm.addObject("loginUser", userDto);
		if (result.hasErrors()) {
			return editUserForm;
		} else if (!oldUserForm.getEmail().equals(userForm.getEmail())
		        && this.userService.doIsEmailExist(userForm.getEmail())) {
			editUserForm.addObject("exist_email", messageSource.getMessage("U001", null, null));
			return editUserForm;
		}
		editUserForm.addObject("userForm", userForm);
		editUserForm.setViewName("/user/edit/editUserConfirm");
		if (this.isProfile()) {
			editUserForm.addObject("url", "/profile/user/update");
		}
		return editUserForm;
	}

	/**
	 * <h2>updateUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userForm
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = { "/user/update", "/profile/user/update" }, params = "update", method = RequestMethod.POST)
	public ModelAndView updateUser(UserForm userForm) {
		ModelAndView updateUser = new ModelAndView("redirect:/user/list");
		this.userService.doUpdateUser(userForm);
		UserDto userDto = this.authService.doGetLoggedInUser();
		if (userDto.getType().equals("0")) {
			updateUser.setViewName("redirect:/post/list");
		}
		if (this.isProfile()) {
			updateUser.setViewName("redirect:/user/profile");
		}
		return updateUser;
	}

	/**
	 * <h2>backUpdateUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userForm
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = { "/user/update", "/profile/user/update" }, params = "back", method = RequestMethod.POST)
	public ModelAndView backUpdateUser(UserForm userForm) {
		ModelAndView updateUser = new ModelAndView("/user/edit/editUser");
		UserDto userDto = this.authService.doGetLoggedInUser();
		updateUser.addObject("userForm", userForm);
		updateUser.addObject("loginUser", userDto);
		updateUser.addObject("url", "/profile/user/editConfirm");
		return updateUser;
	}

	@RequestMapping(value = "/user/profile", method = RequestMethod.GET)
	public ModelAndView userProfile(Model model, @RequestParam("page") Optional<Integer> page,
	        @RequestParam("size") Optional<Integer> size) {
		session.removeAttribute("POST_SESSION");
		session.removeAttribute("POST_EDIT_SESSION");
		ModelAndView userProfile = new ModelAndView("/user/profile/userProfile");
		UserDto loginUser = this.authService.doGetLoggedInUser();
		model = this.postListPaginatedModel(model, page, size, null, 0, loginUser.getId());
		PostForm postForm = new PostForm();
		User user = new User();
		user.setId(loginUser.getId());
		postForm.setUser(user);
		int postCount = this.postService.doGetCountByUserId(loginUser.getId());
		UserDto userDto = this.authService.doGetLoggedInUser();
		userProfile.addObject("loginUser", userDto);
		userProfile.addObject("postForm", postForm);
		userProfile.addObject("listType", "profile");
		userProfile.addObject("postCount", postCount);

		return userProfile;
	}

	/**
	 * <h2>softDelete</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/user/delete/{id}", method = RequestMethod.GET)
	public ModelAndView softDelete(@PathVariable("id") int userId) {
		this.userService.doDeleteUser(userId);
		return new ModelAndView("redirect:/user/list");
	}

	/**
	 * <h2>downloadExcel</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param response
	 * @return
	 * @throws IOException
	 * @return ModelAndView
	 */
	@GetMapping(value = "/user/download")
	public ModelAndView downloadExcel(HttpServletResponse response) throws IOException {
		this.userService.doDownloadUser(response);
		return null;
	}

	private Model postListPaginatedModel(Model model, Optional<Integer> page, Optional<Integer> size, String search,
	        int categoryId, int userId) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(6);
		Page<PostDto> postPage = this.postService.findUserProfilePaginated(PageRequest.of(currentPage - 1, pageSize),
		        search, categoryId, userId);
		model.addAttribute("postPage", postPage);
		int totalPages = postPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return model;
	}

	private Boolean isProfile() {
		if (request.getServletPath().contains("/profile")) {
			return true;
		}
		return false;
	}
}