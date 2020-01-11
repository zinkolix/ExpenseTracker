/**
 * 
 */
package com.olixstudios.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olixstudios.expense.model.Category;

/**
 * @author zinki
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
	Category findByName(String name);
}
