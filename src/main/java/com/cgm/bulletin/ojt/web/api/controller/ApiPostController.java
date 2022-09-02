package com.cgm.bulletin.ojt.web.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgm.bulletin.ojt.bl.service.ApiPostService;
import com.cgm.bulletin.ojt.bl.service.CategoryService;
import com.cgm.bulletin.ojt.payload.request.PostRequest;
import com.cgm.bulletin.ojt.payload.response.ApiResponse;
import com.cgm.bulletin.ojt.payload.response.PostResponse;
import com.cgm.bulletin.ojt.persistence.entity.Category;
import com.cgm.bulletin.ojt.web.form.PostForm;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * <h2>ApiPostController Class</h2>
 * <p>
 * Process for Displaying ApiPostController
 * </p>
 * 
 * @author KyawHtet
 *
 */
@RestController
@RequestMapping(value = "/api/post")
public class ApiPostController {
	/**
	 * <h2>apiPostService</h2>
	 * <p>
	 * apiPostService
	 * </p>
	 */
	@Autowired
	private ApiPostService apiPostService;

	/**
	 * <h2>categoryService</h2>
	 * <p>
	 * categoryService
	 * </p>
	 */
	@Autowired
	private CategoryService categoryService;

	/**
	 * <h2>listPosts</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param page
	 * @param size
	 * @param request
	 * @return
	 * @return ResponseEntity<Page<PostResponse>>
	 */
	@GetMapping(value = "/list")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Page<PostResponse>> listPosts(@RequestParam("page") Optional<Integer> page,
	        @RequestParam("size") Optional<Integer> size, HttpServletRequest request) {
		System.out.print(request.getHeader("Authorization"));
		Page<PostResponse> listPosts = this.postListPaginatedModel(page, size, null, 0);
		return new ResponseEntity<>(listPosts, HttpStatus.OK);
	}

	/**
	 * <h2>savePost</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param postForm
	 * @param result
	 * @return
	 * @return ResponseEntity<PostResponse>
	 */
	@PostMapping("/create")
	public ResponseEntity<?> savePost(@Valid @ModelAttribute PostForm postForm, BindingResult result) {
		System.out.println("heloworld");
		if (result.hasErrors()) {
			return new ResponseEntity<>("Must Not Be Empty!", HttpStatus.BAD_REQUEST);
		}
		List<Category> categoryList = this.categoryService.doGetAllCategories();
		PostForm postConfirm = this.getPostForm(postForm, categoryList);
		PostResponse postResponse = this.apiPostService.doSavePost(postConfirm);
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}

	/**
	 * <h2>editPost</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param postId
	 * @return
	 * @throws JsonProcessingException
	 * @return ResponseEntity<String>
	 */
	@GetMapping("/edit/{id}")
	public ResponseEntity<?> editPost(@PathVariable("id") int postId) throws JsonProcessingException {
		PostResponse postResponse = this.apiPostService.doGetPostById(postId);
		return new ResponseEntity<>(postResponse, postResponse == null ? HttpStatus.NOT_FOUND : HttpStatus.FOUND);
	}

	/**
	 * <h2>updatePost</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param id
	 * @param postRequest
	 * @param result
	 * @return
	 * @return ResponseEntity<PostResponse>
	 */
	@PostMapping("/update")
	public ResponseEntity<PostResponse> updatePost(@RequestParam("id") int id,
	        @Valid @ModelAttribute PostRequest postRequest, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		postRequest.setPost_id(id);
		PostResponse postResponse = this.apiPostService.doUpdatePost(postRequest);
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
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
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/download")
	public ResponseEntity<?> downloadPost(HttpServletResponse response) throws IOException {
		this.apiPostService.doDownloadPost(response);
		return null;
	}

	/**
	 * <h2>searchPost</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param postRequest
	 * @param page
	 * @param size
	 * @return
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/search")
	public ResponseEntity<?> searchPost(@ModelAttribute PostRequest postRequest,
	        @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		Page<PostResponse> listPosts = this.postListPaginatedModel(page, size, postRequest.getSearch(), 0);
		return new ResponseEntity<>(listPosts, HttpStatus.OK);
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
	 * @return ResponseEntity<?>
	 */
	@GetMapping(value = "/search/{id}")
	public ResponseEntity<?> searchByCategory(@PathVariable("id") int categoryId, Model model,
	        @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		Page<PostResponse> listPosts = this.postListPaginatedModel(page, size, null, categoryId);
		return new ResponseEntity<>(listPosts, HttpStatus.OK);
	}

	/**
	 * <h2>deletePost</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param id
	 * @return
	 * @return ResponseEntity<?>
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePost(@PathVariable("id") int id) {
		ApiResponse apiResponse = this.apiPostService.doDeletePost(id);
		return new ResponseEntity<>(apiResponse, apiResponse.getSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
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
	 * @param page
	 * @param size
	 * @param search
	 * @param categoryId
	 * @return
	 * @return Page<PostResponse>
	 */
	private Page<PostResponse> postListPaginatedModel(Optional<Integer> page, Optional<Integer> size, String search,
	        int categoryId) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(6);
		Page<PostResponse> postPage = this.apiPostService.findPaginated(PageRequest.of(currentPage - 1, pageSize),
		        search, categoryId);
		return postPage;
	}
}