package com.olixstudios.expense.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "expense")
public class Expense {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Instant expensedate;

	private String descript;

	@ManyToOne
	private Category category;

//	@JsonIgnore
	@ManyToOne
	private User user;

	/**
	 * @param id
	 * @param expensedate
	 * @param descript
	 * @param category
	 * @param user
	 */
	public Expense(Long id, Instant expensedate, String descript, Category category, User user) {
		this.id = id;
		this.expensedate = expensedate;
		this.descript = descript;
		this.category = category;
		this.user = user;
	}

	/**
	 * 
	 */
	public Expense() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the expensedate
	 */
	public Instant getExpensedate() {
		return expensedate;
	}

	/**
	 * @param expensedate the expensedate to set
	 */
	public void setExpensedate(Instant expensedate) {
		this.expensedate = expensedate;
	}

	/**
	 * @return the descript
	 */
	public String getDescript() {
		return descript;
	}

	/**
	 * @param descript the descript to set
	 */
	public void setDescript(String descript) {
		this.descript = descript;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", expensedate=" + expensedate + ", descript=" + descript + ", category="
				+ category + ", user=" + user + "]";
	}

}
