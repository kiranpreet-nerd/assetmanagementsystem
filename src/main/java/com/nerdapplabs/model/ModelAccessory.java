package com.nerdapplabs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "modelaccessory")
public class ModelAccessory {
	
	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "model")
	private String model;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
