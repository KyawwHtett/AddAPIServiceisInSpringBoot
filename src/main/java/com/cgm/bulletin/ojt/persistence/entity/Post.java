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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2>Post Class</h2>
 * <p>
 * Process for Displaying Post
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
	@Column(name = "updated_user_id")
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