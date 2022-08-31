package com.cgm.bulletin.ojt.web.api.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cgm.bulletin.ojt.bl.dto.UserDto;
import com.cgm.bulletin.ojt.bl.service.ApiCategoryService;
import com.cgm.bulletin.ojt.bl.service.AuthenticationService;
import com.cgm.bulletin.ojt.payload.request.CategoryRequest;
import com.cgm.bulletin.ojt.payload.response.ApiResponse;
import com.cgm.bulletin.ojt.payload.response.CategoryResponse;

/**
 * <h2>ApiCategoryController Class</h2>
 * <p>
 * Process for Displaying ApiCategoryController
 * </p>
 * 
 * @author KyawHtet
 *
 */
@RestController
@RequestMapping("/api/category")
public class ApiCategoryController {

	/**
	 * <h2>apiCategoryService</h2>
	 * <p>
	 * apiCategoryService
	 * </p>
	 */
	@Autowired
	private ApiCategoryService apiCategoryService;

	/**
	 * <h2>authService</h2>
	 * <p>
	 * authService
	 * </p>
	 */
	@Autowired
	private AuthenticationService authService;

	/**
	 * <h2>listCategory</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return List<CategoryResponse>
	 */
	@GetMapping(value = "/list")
	@PreAuthorize("hasRole('ADMIN')")
	public List<CategoryResponse> listCategory() {
		UserDto userDto = authService.doGetLoggedInUser();
		System.out.println(userDto.getType() + "UserPrincipal");
		List<CategoryResponse> listCategory = this.apiCategoryService.doGetAllCategories();
		return listCategory;
	}

	/**
	 * <h2>saveCategory</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param categoryRequest
	 * @param result
	 * @return
	 * @return HttpStatus
	 */
	@PostMapping(value = "/create")
	@PreAuthorize("hasRole('ADMIN')")
	public HttpStatus saveCategory(@RequestBody @Valid CategoryRequest categoryRequest, BindingResult result) {
		if (result.hasErrors()) {
			return HttpStatus.BAD_REQUEST;
		}
		this.apiCategoryService.doSaveCategory(categoryRequest);
		return HttpStatus.OK;
	}

	/**
	 * <h2>editCategory</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param category_id
	 * @return
	 * @return CategoryResponse
	 */
	@GetMapping(value = "/edit")
	@PreAuthorize("hasRole('ADMIN')")
	public CategoryResponse editCategory(@RequestParam int category_id) {
		CategoryResponse categoryResponse = this.apiCategoryService.doGetCategoryById(category_id);
		System.out.println(HttpStatus.FOUND);
		return categoryResponse;
	}

	/**
	 * <h2>updateCategory</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param categoryRequest
	 * @param result
	 * @return
	 * @return HttpStatus
	 */
	@PatchMapping(value = "/update")
	@PreAuthorize("hasRole('ADMIN')")
	public HttpStatus updateCategory(@RequestBody @Valid CategoryRequest categoryRequest, BindingResult result) {
		if (result.hasErrors()) {
			return HttpStatus.BAD_REQUEST;
		}
		boolean isExist = this.apiCategoryService.doUpdateCategory(categoryRequest);
		return (isExist == true) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
	}

	/**
	 * <h2>deleteCategory</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param category_id
	 * @return
	 * @return HttpStatus
	 */
	@DeleteMapping(value = "/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public HttpStatus deleteCategory(@PathVariable("id") int category_id) {
		Boolean isExist = this.apiCategoryService.doDeleteCategory(category_id);
		return (isExist == true) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
	}

	/**
	 * <h2>fileImport</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 * @return ResponseEntity<?>
	 */
	@PostMapping(value = "/import")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> fileImport(@RequestParam("file") MultipartFile file) throws IOException {
		String fileImportMessage = this.apiCategoryService.doImportCategory(file);
		return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, fileImportMessage), HttpStatus.OK);
	}

}