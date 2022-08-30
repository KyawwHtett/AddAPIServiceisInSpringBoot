package com.cgm.bulletin.ojt.persistence.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cgm.bulletin.ojt.persistence.dao.CategoryDao;
import com.cgm.bulletin.ojt.persistence.entity.Category;

/**
 * <h2>CategoryDaoImpl Class</h2>
 * <p>
 * Process for Displaying CategoryDaoImpl
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {
	/**
	 * <h2>SELECT_CATEGORY_LIST_HQL</h2>
	 * <p>
	 * SELECT_CATEGORY_LIST_HQL
	 * </p>
	 */
	private static final String SELECT_CATEGORY_LIST_HQL = "SELECT c FROM Category c WHERE deleted_at = null";

	/**
	 * <h2>sessionFactory</h2>
	 * <p>
	 * sessionFactory
	 * </p>
	 */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * <h2>dbSaveCategory</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param category
	 */
	@Override
	public void dbSaveCategory(Category category) {
		this.sessionFactory.getCurrentSession().save(category);
	}

	/**
	 * <h2>dbGetAllCategories</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Category> dbGetAllCategories() {
		StringBuffer query = new StringBuffer(SELECT_CATEGORY_LIST_HQL);
		Query queryUserList = this.sessionFactory.getCurrentSession().createQuery(query.toString());
		List<Category> userList = (List<Category>) queryUserList.getResultList();
		return userList;
	}

	/**
	 * <h2>dbGetCategoryById</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param category_id
	 * @return
	 */
	@Override
	public Category dbGetCategoryById(int category_id) {
		return this.sessionFactory.getCurrentSession().get(Category.class, category_id);
	}

	/**
	 * <h2>dbDeleteCategory</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param category
	 */
	@Override
	public void dbDeleteCategory(Category category) {
		this.sessionFactory.getCurrentSession().update(category);
	}

	/**
	 * <h2>dbUpdateCategory</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param category
	 */
	@Override
	public void dbUpdateCategory(Category category) {
		this.sessionFactory.getCurrentSession().update(category);
	}
}