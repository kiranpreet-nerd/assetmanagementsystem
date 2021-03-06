package com.nerdapplabs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "request_asset")
public class AssetRequest {
	
	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "email", foreignKey = @ForeignKey(name = "FK_REQUEST_ASSET"))
    private User user;

	@NotEmpty(message = "required")
	@Column(name = "assetname")
	private String assetname;
	
	@NotEmpty(message = "required")
	@Column(name = "assettype")
	private String assettype;
	
	@NotEmpty(message = "required")
	@Column(name = "quantity")
	private String quantity;
	
	@NotEmpty(message = "required")
	@Column(name = "reason")
	private String reason;
	
	@NotEmpty(message = "required")
	@Column(name = "date")
	private String requestdate;
	
	@Column(name = "requestmode")
	private Boolean requestmode;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	@Column(name = "windows")
	private String windows;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "ram")
	private String ram;
	
	@Column(name = "harddisk")
	private String harddisk;
	
	public String getHarddisk() {
		return harddisk;
	}

	public void setHarddisk(String harddisk) {
		this.harddisk = harddisk;
	}

	public String getWindows() {
		return windows;
	}

	public void setWindows(String windows) {
		this.windows = windows;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getRequestmode() {
		return requestmode;
	}

	public void setRequestmode(Boolean requestmode) {
		this.requestmode = requestmode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAssetname() {
		return assetname;
	}

	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}

	public String getAssettype() {
		return assettype;
	}

	public void setAssettype(String assettype) {
		this.assettype = assettype;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRequestdate() {
		return requestdate;
	}

	public void setRequestdate(String requestdate) {
		this.requestdate = requestdate;
	}
}
