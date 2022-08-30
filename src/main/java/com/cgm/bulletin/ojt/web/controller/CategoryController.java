package com.cgm.bulletin.ojt.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.bulletin.ojt.bl.dto.UserDto;
import com.cgm.bulletin.ojt.bl.service.AuthenticationService;
import com.cgm.bulletin.ojt.bl.service.CategoryService;
import com.cgm.bulletin.ojt.persistence.entity.Category;
import com.cgm.bulletin.ojt.web.form.CategoryForm;

/**
 * <h2>CategoryController Class</h2>
 * <p>
 * Process for Displaying CategoryController
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Controller
public class CategoryController {
	/**
	 * <h2>session</h2>
	 * <p>
	 * session
	 * </p>
	 */
	@Autowired
	private HttpSession session;

	/**
	 * <h2>categoryService</h2>
	 * <p>
	 * categoryService
	 * </p>
	 */
	@Autowired
	private CategoryService categoryService;

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
	 * @return ModelAndView
	 */
	@GetMapping(value = "/category/list")
	public ModelAndView listCategory() {
		session.removeAttribute("CATEGORY");
		ModelAndView listCategoryView = new ModelAndView("/category/list/listCategory");
		List<Category> listCategory = this.categoryService.doGetAllCategories();
		UserDto userDto = this.authService.doGetLoggedInUser();
		listCategoryView.addObject("listCategory", listCategory);
		listCategoryView.addObject("loginUser", userDto);
		return listCategoryView;
	}

	/**
	 * <h2>createCategory</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return ModelAndView
	 */
	@GetMapping(value = "/category/create")
	public ModelAndView createCategory() {
		ModelAndView createCategoryView = new ModelAndView("/category/create/createCategory");
		CategoryForm categoryForm = (CategoryForm) session.getAttribute("CATEGORY");
		if (categoryForm != null) {
			createCategoryView.addObject("categoryForm", categoryForm);
		} else {
			createCategoryView.addObject("categoryForm", new CategoryForm());
		}
		return createCategoryView;
	}

	/**
	 * <h2>createCategoryConfirm</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param categoryForm
	 * @param result
	 * @return
	 * @return ModelAndView
	 */
	@PostMapping(value = "/category/confirmCategory")
	public ModelAndView createCategoryConfirm(@Valid CategoryForm categoryForm, BindingResult result) {
		ModelAndView modelView = new ModelAndView("/category/create/createCategory");
		if (result.hasErrors()) {
			return modelView;
		}
		session.setAttribute("CATEGORY", categoryForm);
		modelView.addObject("categoryForm", categoryForm);
		modelView.setViewName("/category/create/createCategoryConfirm");
		return modelView;
	}

	/**
	 * <h2>saveCategory</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return ModelAndView
	 */
	@PostMapping(value = "/category/create")
	public ModelAndView saveCategory() {
		CategoryForm category = (CategoryForm) session.getAttribute("CATEGORY");
		this.categoryService.doSaveCategory(category);
		return new ModelAndView("redirect:/category/list");
	}

	/**
	 * <h2>editCategory</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param category_id
	 * @return
	 * @return ModelAndView
	 */
	@GetMapping(value = "/category/edit/{id}")
	public ModelAndView editCategory(@PathVariable("id") int category_id) {
		ModelAndView editCategoryView = new ModelAndView("/category/edit/editCategory");
		CategoryForm sessionCategory = (CategoryForm) session.getAttribute("CATEGORY");
		if (sessionCategory != null) {
			editCategoryView.addObject("categoryForm", sessionCategory);
		} else {
			CategoryForm categoryForm = this.categoryService.doGetCategoryById(category_id);
			editCategoryView.addObject("categoryForm", categoryForm);
		}
		return editCategoryView;
	}

	/**
	 * <h2>editCategoryConfirm</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param categoryForm
	 * @param result
	 * @return
	 * @return ModelAndView
	 */
	@PostMapping(value = "/category/editCategoryConfirm")
	public ModelAndView editCategoryConfirm(@Valid CategoryForm categoryForm, BindingResult result) {
		ModelAndView editConfirmView = new ModelAndView("category/edit/editCategoryConfirm");
		if (result.hasErrors()) {
			editConfirmView.setViewName("/category/edit/editCategory");
			return editConfirmView;
		}
		session.setAttribute("CATEGORY", categoryForm);
		return editConfirmView;
	}

	/**
	 * <h2>updateCategory</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return ModelAndView
	 */
	@PostMapping(value = "/category/update")
	public ModelAndView updateCategory() {
		CategoryForm category = (CategoryForm) session.getAttribute("CATEGORY");
		if (category != null) {
			this.categoryService.doUpdateCategory(category);
		}
		return new ModelAndView("redirect:/category/list");
	}

	/**
	 * <h2>deleteCategory</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param category_id
	 * @return
	 * @return ModelAndView
	 */
	@GetMapping(value = "/category/delete/{id}")
	public ModelAndView deleteCategory(@PathVariable("id") int category_id) {
		this.categoryService.doDeleteCategory(category_id);
		return new ModelAndView("redirect:/category/list");
	}
}