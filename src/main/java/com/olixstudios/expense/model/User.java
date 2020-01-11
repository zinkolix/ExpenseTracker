/**
 * 
 */
package com.olixstudios.expense.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zinki
 *
 */
@Entity
@Table(name = "user")
public class User {

	@Id
	private Long id;

	private String name;

	private String email;

	/**
	 * @param id
	 * @param name
	 * @param email
	 */
	public User(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	/**
	 * 
	 */
	public User() {
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
	}

}
