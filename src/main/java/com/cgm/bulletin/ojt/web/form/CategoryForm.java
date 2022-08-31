package com.cgm.bulletin.ojt.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.cgm.bulletin.ojt.persistence.entity.Category;
import com.cgm.bulletin.ojt.persistence.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2>CategoryForm Class</h2>
 * <p>
 * Process for Displaying CategoryForm
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryForm {
	/**
	 * <h2>category_id</h2>
	 * <p>
	 * category_id
	 * </p>
	 */
	private int category_id;

	/**
	 * <h2>category_name</h2>
	 * <p>
	 * category_name
	 * </p>
	 */
	@NotEmpty
	private String category_name;

	/**
	 * <h2>deleted_at</h2>
	 * <p>
	 * deleted_at
	 * </p>
	 */
	private Date deleted_at;

	/**
	 * <h2>posts</h2>
	 * <p>
	 * posts
	 * </p>
	 */
	private List<Post> posts = new ArrayList<Post>();

	/**
	 * <h2>Constructor for CategoryForm</h2>
	 * <p>
	 * Constructor for CategoryForm
	 * </p>
	 * 
	 * @param category
	 */
	public CategoryForm(Category category) {
		super();
		this.category_id = category.getCategory_id();
		this.category_name = category.getCategory_name();
		this.deleted_at = category.getDeleted_at();
		this.posts = category.getPosts();
	}
}