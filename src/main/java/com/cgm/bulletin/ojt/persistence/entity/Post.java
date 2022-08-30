package com.cgm.bulletin.ojt.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cgm.bulletin.ojt.bl.dto.PostDto;
import com.cgm.bulletin.ojt.web.form.PostForm;

/**
 * <h2>Post Class</h2>
 * <p>
 * Process for Displaying Post
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Entity
@Table(name = "posts")
public class Post implements Serializable {
	/**
	 * <h2>serialVersionUID</h2>
	 * <p>
	 * serialVersionUID
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <h2>post_id</h2>
	 * <p>
	 * post_id
	 * </p>
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private int post_id;

	/**
	 * <h2>title</h2>
	 * <p>
	 * title
	 * </p>
	 */
	@Column(name = "title", nullable = false)
	private String title;

	/**
	 * <h2>description</h2>
	 * <p>
	 * description
	 * </p>
	 */
	@Column(name = "description", nullable = false)
	private String description;

	/**
	 * <h2>flag</h2>
	 * <p>
	 * flag
	 * </p>
	 */
	@Column(name = "flag")
	private boolean flag;

	/**
	 * <h2>created_user_id</h2>
	 * <p>
	 * created_user_id
	 * </p>
	 */
	@Column(name = "created_user_id")
	private int created_user_id;

	/**
	 * <h2>created_at</h2>
	 * <p>
	 * created_at
	 * </p>
	 */
	@Column(name = "created_at")
	private Date created_at;

	/**
	 * <h2>updated_user_id</h2>
	 * <p>
	 * updated_user_id
	 * </p>
	 */
	private int updated_user_id;

	/**
	 * <h2>updated_at</h2>
	 * <p>
	 * updated_at
	 * </p>
	 */
	@Column(name = "updated_at")
	private Date updated_at;

	/**
	 * <h2>deleted_user_id</h2>
	 * <p>
	 * deleted_user_id
	 * </p>
	 */
	@Column(name = "deleted_user_id")
	private Integer deleted_user_id;

	/**
	 * <h2>deleted_at</h2>
	 * <p>
	 * deleted_at
	 * </p>
	 */
	@Column(name = "deleted_at")
	private Date deleted_at;

	/**
	 * <h2>categories</h2>
	 * <p>
	 * categories
	 * </p>
	 */
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category categories;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable=false)
	private User user;

	/**
	 * <h2>getPost_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return int
	 */
	public int getPost_id() {
		return post_id;
	}

	/**
	 * <h2>setPost_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param post_id
	 * @return void
	 */
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	/**
	 * <h2>getTitle</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * <h2>setTitle</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param title
	 * @return void
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * <h2>getDescription</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <h2>setDescription</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param description
	 * @return void
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * <h2>isFlag</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return boolean
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * <h2>setFlag</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param flag
	 * @return void
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	/**
	 * <h2>getCreated_user_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return int
	 */
	public int getCreated_user_id() {
		return created_user_id;
	}

	/**
	 * <h2>setCreated_user_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param created_user_id
	 * @return void
	 */
	public void setCreated_user_id(int created_user_id) {
		this.created_user_id = created_user_id;
	}

	/**
	 * <h2>getCreated_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return Date
	 */
	public Date getCreated_at() {
		return created_at;
	}

	/**
	 * <h2>setCreated_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param created_at
	 * @return void
	 */
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	/**
	 * <h2>getUpdated_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return Date
	 */
	public Date getUpdated_at() {
		return updated_at;
	}

	/**
	 * <h2>getUpdated_user_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return int
	 */
	public int getUpdated_user_id() {
		return updated_user_id;
	}

	/**
	 * <h2>setUpdated_user_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param updated_user_id
	 * @return void
	 */
	public void setUpdated_user_id(int updated_user_id) {
		this.updated_user_id = updated_user_id;
	}

	/**
	 * <h2>setUpdated_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param updated_at
	 * @return void
	 */
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	/**
	 * <h2>getDeleted_user_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return int
	 */
	public int getDeleted_user_id() {
		return deleted_user_id;
	}

	/**
	 * <h2>setDeleted_user_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param deleted_user_id
	 * @return void
	 */
	public void setDeleted_user_id(int deleted_user_id) {
		this.deleted_user_id = deleted_user_id;
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
	 * <h2>getCategories</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return Category
	 */
	public Category getCategories() {
		return categories;
	}

	/**
	 * <h2>setCategories</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param categories
	 * @return void
	 */
	public void setCategories(Category categories) {
		this.categories = categories;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * <h2>Constructor for Post</h2>
	 * <p>
	 * Constructor for Post
	 * </p>
	 */
	public Post() {
		super();
	}

	/**
	 * <h2>Constructor for Post</h2>
	 * <p>
	 * Constructor for Post
	 * </p>
	 * 
	 * @param postForm
	 */
	public Post(PostForm postForm) {
		super();
		this.post_id = postForm.getPost_id();
		this.title = postForm.getTitle();
		this.description = postForm.getDescription();
		this.flag = postForm.isFlag();
		this.created_user_id = postForm.getCreated_user_id();
		this.created_at = postForm.getCreated_at();
		this.updated_user_id = postForm.getUpdated_user_id();
		this.updated_at = postForm.getUpdated_at();
		this.deleted_user_id = postForm.getDeletd_user_id();
		this.deleted_at = postForm.getDeleted_at();
		this.categories = postForm.getCategories();
		this.user = postForm.getUser();
	}

	/**
	 * <h2>Constructor for Post</h2>
	 * <p>
	 * Constructor for Post
	 * </p>
	 * 
	 * @param postDto
	 */
	public Post(PostDto postDto) {
		super();
		this.post_id = postDto.getPost_id();
		this.title = postDto.getTitle();
		this.description = postDto.getDescription();
		this.flag = postDto.isFlag();
		this.created_user_id = postDto.getCreated_user_id();
		this.created_at = postDto.getCreated_at();
		this.updated_user_id = postDto.getUpdated_user_id();
		this.updated_at = postDto.getUpdated_at();
		this.deleted_user_id = postDto.getDeleted_user_id();
		this.deleted_at = postDto.getDeleted_at();
		this.categories = postDto.getCategories();
	}
}