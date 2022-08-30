package com.cgm.bulletin.ojt.persistence.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.cgm.bulletin.ojt.payload.request.CategoryRequest;
import com.cgm.bulletin.ojt.web.form.CategoryForm;

/**
 * <h2>Category Class</h2>
 * <p>
 * Process for Displaying Category
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Entity
@Table(name = "categories")
public class Category implements Serializable {
	/**
	 * <h2>serialVersionUID</h2>
	 * <p>
	 * serialVersionUID
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <h2>category_id</h2>
	 * <p>
	 * category_id
	 * </p>
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private int category_id;

	/**
	 * <h2>category_name</h2>
	 * <p>
	 * category_name
	 * </p>
	 */
	@NotEmpty
	@Column(name = "category_name")
	private String category_name;

	/**
	 * <h2>deleted_at</h2>
	 * <p>
	 * deleted_at
	 * </p>
	 */
	@Column(name = "deleted_at")
	private Date deleted_at;

	/**
	 * <h2>posts</h2>
	 * <p>
	 * posts
	 * </p>
	 */
	@OneToMany(mappedBy = "categories", fetch = FetchType.EAGER)
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
	 * <h2>Constructor for Category</h2>
	 * <p>
	 * Constructor for Category
	 * </p>
	 */
	public Category() {
		super();
	}

	/**
	 * <h2>Constructor for Category</h2>
	 * <p>
	 * Constructor for Category
	 * </p>
	 * 
	 * @param categoryForm
	 */
	public Category(CategoryForm categoryForm) {
		super();
		this.category_id = categoryForm.getCategory_id();
		this.category_name = categoryForm.getCategory_name();
		this.deleted_at = categoryForm.getDeleted_at();
		this.posts = categoryForm.getPosts();
	}

	public Category(CategoryRequest categoryRequest) {
		this.category_id = categoryRequest.getCategory_id();
		this.category_name = categoryRequest.getCategory_name();
		this.deleted_at = categoryRequest.getDeleted_at();
	}
}