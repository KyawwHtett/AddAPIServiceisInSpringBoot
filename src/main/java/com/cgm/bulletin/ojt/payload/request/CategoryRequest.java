package com.cgm.bulletin.ojt.payload.request;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.cgm.bulletin.ojt.persistence.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h2>CategoryRequest Class</h2>
 * <p>
 * Process for Displaying CategoryRequest
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest implements Serializable {
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
	 * <h2>Constructor for CategoryRequest</h2>
	 * <p>
	 * Constructor for CategoryRequest
	 * </p>
	 * 
	 * @param category
	 */
	public CategoryRequest(Category category) {
		super();
		this.category_id = category.getCategory_id();
		this.category_name = category.getCategory_name();
		this.deleted_at = category.getDeleted_at();
	}
}