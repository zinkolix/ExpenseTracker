/**
 * 
 */
package com.olixstudios.expense.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olixstudios.expense.model.Category;
import com.olixstudios.expense.repository.CategoryRepository;

/**
 * @author zinki
 *
 */

@RestController
@RequestMapping("/api")
public class CategoryController {

	private CategoryRepository categoryRepository;

	/**
	 * @param categoryRepository
	 */
	public CategoryController(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	@GetMapping("/categories")
	Collection<Category> categories() {
		return categoryRepository.findAll();
	}

	@GetMapping("/categories/{id}")
	ResponseEntity<?> getCategory(@PathVariable Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		return category.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<Category>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/category")
	ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) throws URISyntaxException {
		Category result = categoryRepository.save(category);
		return ResponseEntity.created(new URI("api/category" + result.getId())).body(result);
	}

	@PutMapping("/category/{id}")
	ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category) {
		Category result = categoryRepository.save(category);
		return ResponseEntity.ok().body(result);

	}

	@DeleteMapping("/category/{id}")
	ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
		categoryRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
