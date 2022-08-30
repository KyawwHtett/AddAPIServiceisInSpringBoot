package com.cgm.bulletin.ojt.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.cgm.bulletin.ojt.persistence.entity.Category;
import com.cgm.bulletin.ojt.persistence.entity.Post;

/**
 * <h2>CategoryForm Class</h2>
 * <p>
 * Process for Displaying CategoryForm
 * </p>
 * 
 * @author KyawHtet
 *
 */
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
	 * <h2>getCategory_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return int
	 */
	public int getCategory_id() {
		return category_id;
	}

	/**
	 * <h2>setCategory_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param category_id
	 * @return void
	 */
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	/**
	 * <h2>getCategory_name</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	public String getCategory_name() {
		return category_name;
	}

	/**
	 * <h2>setCategory_name</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param category_name
	 * @return void
	 */
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	/**
	 * <h2>getDeleted_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return Date
	 */
	public Date getDeleted_at() {
		return deleted_at;
	}

	/**
	 * <h2>setDeleted_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param deleted_at
	 * @return void
	 */
	public void setDeleted_at(Date deleted_at) {
		this.deleted_at = deleted_at;
	}

	/**
	 * <h2>getPosts</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return List<Post>
	 */
	public List<Post> getPosts() {
		return posts;
	}

	/**
	 * <h2>setPosts</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param posts
	 * @return void
	 */
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	/**
	 * <h2>Constructor for CategoryForm</h2>
	 * <p>
	 * Constructor for CategoryForm
	 * </p>
	 */
	public CategoryForm() {
		super();
	}

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