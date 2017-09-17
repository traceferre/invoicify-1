package com.theironyard.invoicify.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(length=255, unique=true)
	private String name;
	
	@OneToMany(mappedBy="company")
	private List<Invoice> invoices;
	
	private int numberOfInvoices;
	
	public Company() {}
	
	public Company(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public int getNumberOfInvoices() {
		setNumberOfInvoices();
		return numberOfInvoices;
	}

	public void setNumberOfInvoices() {
		if (invoices == null || invoices.size() == 0) {
			this.numberOfInvoices = 0;
		}
		else {
			this.numberOfInvoices = invoices.size();
		}
		
	}
	
}
