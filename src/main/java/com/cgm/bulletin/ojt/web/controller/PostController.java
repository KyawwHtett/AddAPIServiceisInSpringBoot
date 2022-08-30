package com.cgm.bulletin.ojt.web.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.bulletin.ojt.bl.dto.PostDto;
import com.cgm.bulletin.ojt.bl.dto.UserDto;
import com.cgm.bulletin.ojt.bl.service.AuthenticationService;
import com.cgm.bulletin.ojt.bl.service.CategoryService;
import com.cgm.bulletin.ojt.bl.service.PostService;
import com.cgm.bulletin.ojt.persistence.entity.Category;
import com.cgm.bulletin.ojt.web.form.PostForm;

/**
 * <h2>PostController Class</h2>
 * <p>
 * Process for Displaying PostController
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Controller
public class PostController {
	/**
	 * <h2>categoryService</h2>
	 * <p>
	 * categoryService
	 * </p>
	 */
	@Autowired
	private CategoryService categoryService;

	/**
	 * <h2>postService</h2>
	 * <p>
	 * postService
	 * </p>
	 */
	@Autowired
	private PostService postService;

	/**
	 * <h2>session</h2>
	 * <p>
	 * session
	 * </p>
	 */
	@Autowired
	private HttpSession session;

	/**
	 * <h2>messageSource</h2>
	 * <p>
	 * messageSource
	 * </p>
	 */
	@Autowired
	private MessageSource messageSource;

	/**
	 * <h2>authService</h2>
	 * <p>
	 * authService
	 * </p>
	 */
	@Autowired
	private AuthenticationService authService;

	@Autowired
	private HttpServletRequest request;

	/**
	 * <h2>listPosts</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param model
	 * @param page
	 * @param size
	 * @return
	 * @return String
	 */
	@RequestMapping(value = "/post/list", method = RequestMethod.GET)
	public String listPosts(Model model, @RequestParam("page") Optional<Integer> page,
	        @RequestParam("size") Optional<Integer> size) {
		session.removeAttribute("POST_SESSION");
		session.removeAttribute("POST_EDIT_SESSION");
		model = this.postListPaginatedModel(model, page, size, null, 0);
		PostForm postForm = new PostForm();
		UserDto userDto = this.authService.doGetLoggedInUser();
		model.addAttribute("postForm", postForm);
		model.addAttribute("loginUser", userDto);
		return "/post/list/listPost";
	}

	/**
	 * <h2>createPost</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return ModelAndView
	 */
	@GetMapping("/post/create")
	public ModelAndView createPost() {
		ModelAndView createPostView = new ModelAndView("/post/create/createPost");
		List<Category> categoryList = this.categoryService.doGetAllCategories();
		PostForm postForm = (PostForm) session.getAttribute("POST_SESSION");
		if (postForm != null) {
			createPostView.addObject("postForm", postForm);
		} else {
			createPostView.addObject("postForm", new PostForm());
		}
		createPostView.addObject("listCategory", categoryList);
		return createPostView;
	}

	/**
	 * <h2>createPostConfirm</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param postForm
	 * @param result
	 * @return
	 * @return ModelAndView
	 */
	@PostMapping(value = "/post/confirmPost")
	public ModelAndView createPostConfirm(@Valid PostForm postForm, BindingResult result) {
		ModelAndView createPostView = new ModelAndView("/post/create/createPost");
		List<Category> categoryList = this.categoryService.doGetAllCategories();
		createPostView.addObject("listCategory", categoryList);
		if (result.hasErrors()) {
			if (postForm.getCategories().getCategory_id() == 0) {
				createPostView.addObject("NUllCAT", messageSource.getMessage("NULLCAT", null, null));
			}
			return createPostView;
		} else if (postForm.getCategories().getCategory_id() == 0) {
			createPostView.addObject("NUllCAT", messageSource.getMessage("NULLCAT", null, null));
			return createPostView;
		}
		PostForm postSession = this.getPostForm(postForm, categoryList);
		session.setAttribute("POST_SESSION", postSession);
		createPostView.setViewName("/post/create/createPostConfirm");
		return createPostView;
	}

	/**
	 * <h2>savePost</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return ModelAndView
	 */
	@PostMapping(value = "/post/save")
	public ModelAndView savePost() {
		this.postService.doSavePost();
		return new ModelAndView("redirect:/post/list");
	}

	/**
	 * <h2>editPost</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param post_id
	 * @return
	 * @return ModelAndView
	 */
	@GetMapping(value = { "/post/edit/{id}", "/profile/post/edit/{id}" })
	public ModelAndView editPost(@PathVariable("id") int post_id, HttpServletRequest request) {
		ModelAndView editPostFormView = new ModelAndView("/post/edit/editPost");
		List<Category> categoryList = this.categoryService.doGetAllCategories();
		PostForm sessionPostForm = (PostForm) this.session.getAttribute("POST_EDIT_SESSION");
		if (sessionPostForm != null) {
			editPostFormView.addObject("postForm", sessionPostForm);
		} else {
			PostDto postForm = this.postService.doGetPostById(post_id);
			editPostFormView.addObject("postForm", postForm);
		}
		if (isProfile()) {
			editPostFormView.addObject("profile", "profile");
			editPostFormView.addObject("url", "/profile/post/editConfirm");
		}
		editPostFormView.addObject("listCategory", categoryList);
		return editPostFormView;
	}

	/**
	 * <h2>editPostConfirm</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param postForm
	 * @param result
	 * @return
	 * @return ModelAndView
	 */
	@PostMapping(value = { "/post/editConfirm", "/profile/post/editConfirm" })
	public ModelAndView editPostConfirm(@Valid PostForm postForm, BindingResult result, HttpServletRequest request) {
		ModelAndView editPostConfirmView = new ModelAndView("/post/edit/editPost");
		List<Category> categoryList = this.categoryService.doGetAllCategories();
		editPostConfirmView.addObject("listCategory", categoryList);
		if (result.hasErrors()) {
			if (postForm.getCategories().getCategory_id() == 0) {
				editPostConfirmView.addObject("NUllCAT", messageSource.getMessage("NULLCAT", null, null));
			}
			return editPostConfirmView;
		} else if (postForm.getCategories().getCategory_id() == 0) {
			editPostConfirmView.addObject("NUllCAT", messageSource.getMessage("NULLCAT", null, null));
			return editPostConfirmView;
		}
		if (isProfile()) {
			editPostConfirmView.addObject("profile", "profile");
			editPostConfirmView.addObject("url", "/profile/post/update");
		}
		PostForm postSession = this.getPostForm(postForm, categoryList);
		session.setAttribute("POST_EDIT_SESSION", postSession);
		editPostConfirmView.setViewName("/post/edit/editPostConfirm");
		return editPostConfirmView;
	}

	/**
	 * <h2>updatePost</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return ModelAndView
	 */
	@PostMapping(value = { "/post/update", "/profile/post/update" })
	public ModelAndView updatePost() {
		this.postService.doUpdatePost();
		if (isProfile()) {
			return new ModelAndView("redirect:/user/profile");
		}
		return new ModelAndView("redirect:/post/list");
	}

	/**
	 * <h2>deletePost</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param post_id
	 * @return
	 * @return ModelAndView
	 */
	@GetMapping(value = { "/post/delete/{id}", "/profile/post/delete/{id}" })
	public ModelAndView deletePost(@PathVariable("id") int post_id) {
		UserDto userDto = this.authService.doGetLoggedInUser();
		PostDto postDto = this.postService.doGetPostById(post_id);
		if (userDto.getType().equals("1") || userDto.getId() == postDto.getCreated_user_id()) {
			System.out.println("delete post");
			this.postService.doDeletePost(post_id);
			if (this.isProfile()) {
				return new ModelAndView("redirect:/user/profile");
			}
			return new ModelAndView("redirect:/post/list");
		}
		return null;
	}

	/**
	 * <h2>searchPost</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param postForm
	 * @param model
	 * @param page
	 * @param size
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/post/search", method = RequestMethod.POST)
	public ModelAndView searchPost(PostForm postForm, Model model, @RequestParam("page") Optional<Integer> page,
	        @RequestParam("size") Optional<Integer> size) {
		session.removeAttribute("POST_SESSION");
		session.removeAttribute("POST_EDIT_SESSION");
		ModelAndView listPostView = new ModelAndView("/post/list/listPost");
		if (postForm.getSearch().isEmpty()) {
			return new ModelAndView("redirect:/post/list");
		} else {
			model = this.postListPaginatedModel(model, page, size, postForm.getSearch(), 0);
		}
		UserDto userDto = this.authService.doGetLoggedInUser();
		model.addAttribute("listType", "search");
		model.addAttribute("search", postForm.getSearch());
		model.addAttribute("loginUser", userDto);
		return listPostView;
	}

	/**
	 * <h2>searchPostGet</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param postForm
	 * @param model
	 * @param page
	 * @param size
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/post/search", method = RequestMethod.GET)
	public ModelAndView searchPostGet(PostForm postForm, Model model, @RequestParam("page") Optional<Integer> page,
	        @RequestParam("size") Optional<Integer> size) {
		session.removeAttribute("POST_SESSION");
		session.removeAttribute("POST_EDIT_SESSION");
		ModelAndView listPostView = new ModelAndView("/post/list/listPost");
		if (postForm.getSearch() == null) {
			return new ModelAndView("redirect:/post/list");
		} else {
			model = this.postListPaginatedModel(model, page, size, postForm.getSearch(), 0);
		}
		UserDto userDto = this.authService.doGetLoggedInUser();
		model.addAttribute("listType", "search");
		model.addAttribute("search", null);
		model.addAttribute("loginUser", userDto);
		return listPostView;
	}

	/**
	 * <h2>searchByCategory</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param categoryId
	 * @param model
	 * @param page
	 * @param size
	 * @return
	 * @return ModelAndView
	 */
	@GetMapping(value = "/post/search/{id}")
	public ModelAndView searchByCategory(@PathVariable("id") int categoryId, Model model,
	        @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		session.removeAttribute("POST_SESSION");
		session.removeAttribute("POST_EDIT_SESSION");
		ModelAndView listPostView = new ModelAndView("/post/list/listPost");
		model = this.postListPaginatedModel(model, page, size, null, categoryId);
		PostForm postForm = new PostForm();
		Category category = new Category();
		category.setCategory_id(categoryId);
		postForm.setCategories(category);
		UserDto userDto = this.authService.doGetLoggedInUser();
		model.addAttribute("postForm", postForm);
		model.addAttribute("listType", "category");
		model.addAttribute("loginUser", userDto);
		return listPostView;
	}

	/**
	 * <h2>downloadPost</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param response
	 * @return
	 * @throws IOException
	 * @return ModelAndView
	 */
	@GetMapping(value = "/post/download")
	public ModelAndView downloadPost(HttpServletResponse response) throws IOException {
		this.postService.doDownloadPost(response);
		return null;
	}

	/**
	 * <h2>getPostForm</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param postForm
	 * @param categoryList
	 * @return
	 * @return PostForm
	 */
	private PostForm getPostForm(PostForm postForm, List<Category> categoryList) {
		for (Category category : categoryList) {
			if (category.getCategory_id() == postForm.getCategories().getCategory_id()) {
				postForm.setCategories(category);
			}
		}
		return postForm;
	}

	/**
	 * <h2>postListPaginatedModel</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param model
	 * @param page
	 * @param size
	 * @param search
	 * @param categoryId
	 * @return
	 * @return Model
	 */
	private Model postListPaginatedModel(Model model, Optional<Integer> page, Optional<Integer> size, String search,
	        int categoryId) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(6);
		Page<PostDto> postPage = this.postService.findPaginated(PageRequest.of(currentPage - 1, pageSize), search,
		        categoryId);
		model.addAttribute("postPage", postPage);
		int totalPages = postPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return model;
	}

	private Boolean isProfile() {
		if (this.request.getServletPath().contains("/profile")) {
			return true;
		}
		return false;
	}
}