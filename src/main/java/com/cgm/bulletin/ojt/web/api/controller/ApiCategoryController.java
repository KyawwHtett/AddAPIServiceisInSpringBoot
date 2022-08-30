package com.cgm.bulletin.ojt.web.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.cgm.bulletin.ojt.bl.dto.UserDto;
import com.cgm.bulletin.ojt.bl.service.ApiCategoryService;
import com.cgm.bulletin.ojt.bl.service.AuthenticationService;
import com.cgm.bulletin.ojt.payload.request.CategoryRequest;
import com.cgm.bulletin.ojt.payload.response.CategoryResponse;

@RestController
@RequestMapping("/api")
public class ApiCategoryController {

	@Autowired
	private ApiCategoryService apiCategoryService;
	
	@Autowired
	private AuthenticationService authService;

	@GetMapping(value = "/category/list")
	public List<CategoryResponse> listCategory() {
		UserDto userDto = authService.doGetLoggedInUser();
		System.out.println(userDto.getType()+"UserPrincipal");
		List<CategoryResponse> listCategory = this.apiCategoryService.doGetAllCategories();
		return listCategory;
	}

	@PostMapping(value = "/category/create")
	public HttpStatus saveCategory(@RequestBody @Valid CategoryRequest categoryRequest, BindingResult result) {
		if (result.hasErrors()) {
			return HttpStatus.BAD_REQUEST;
		}
		this.apiCategoryService.doSaveCategory(categoryRequest);
		return HttpStatus.OK;
	}

	@GetMapping(value = "/category/edit")
	public CategoryResponse editCategory(@RequestParam int category_id) {
		CategoryResponse categoryResponse = this.apiCategoryService.doGetCategoryById(category_id);
		System.out.println(HttpStatus.FOUND);
		return categoryResponse;
	}

	@PatchMapping(value = "/category/update")
	public HttpStatus updateCategory(@RequestBody @Valid CategoryRequest categoryRequest, BindingResult result) {
		if (result.hasErrors()) {
			return HttpStatus.BAD_REQUEST;
		}
		boolean isExist = this.apiCategoryService.doUpdateCategory(categoryRequest);
		return (isExist == true) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
	}

	@DeleteMapping(value = "/category/delete/{id}")
	public HttpStatus deleteCategory(@PathVariable("id") int category_id) {
		Boolean isExist = this.apiCategoryService.doDeleteCategory(category_id);
		return (isExist == true) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
	}
}