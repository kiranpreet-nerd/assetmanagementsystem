package com.nerdapplabs.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "asset")
public class Asset {
	
	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "company")
	private String company;
	
	@Column(name = "tag")
	private String tag;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "serialnumber")
	private String serialnumber;
	
	@Column(name = "purchasedate")
	private String purchasedate;
	
	@Column(name = "supplier")
	private String supplier;
	
	@Column(name = "ordernumber")
	private String ordernumber;
	
	@Column(name = "purchasecost")
	private String purchasecost;
	
	@Column(name = "warranty")
	private String warranty;
	
	@Column(name = "quantity")
	private String quantity;
	
	@Column(name = "totalcost")
	private String totalcost;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getPurchasedate() {
		return purchasedate;
	}

	public void setPurchasedate(String purchasedate) {
		this.purchasedate = purchasedate;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}

	public String getPurchasecost() {
		return purchasecost;
	}

	public void setPurchasecost(String purchasecost) {
		this.purchasecost = purchasecost;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
}