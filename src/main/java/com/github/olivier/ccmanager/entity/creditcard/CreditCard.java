package com.github.olivier.ccmanager.entity.creditcard;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CreditCard {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long userId;
	private String number;
	private String name;
	private Date expiryDate;
	
	public CreditCard() {}
	
	public CreditCard(Long userId, String number, String name, Date expiryDate) {
		this.userId = userId;
		this.number = number;
		this.name = name;
		this.expiryDate = expiryDate;
	}
	
	public Long getUserId() {
		return userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

}
