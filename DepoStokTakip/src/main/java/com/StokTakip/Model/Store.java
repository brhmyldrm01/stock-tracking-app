package com.StokTakip.Model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	 @Column(nullable=false)
	private int user_id;
	private String name;
	private String address;
	private String description;
	private boolean deletable;
	private Date created_at;

	  	@ManyToOne(optional=false)
	    @JoinColumn(name="user_id",referencedColumnName = "id", insertable=false, updatable=false)
	    private User user;
	
	public Store(int user_id, String name, String address, String description,Date created_at,boolean deletable,User user) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.created_at = created_at;
		this.deletable = deletable;
		this.user=user;
	}
	
	public Store( int user_id, String name, String address, String description, boolean deletable) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.deletable = deletable;
	}
	
	public Store( int user_id, String name, String address, String description) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.address = address;
		this.description = description;
	}
	
	
	
	
	
	public Store() {
	
	}
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	


	public boolean isDeletable() {
		return deletable;
	}


	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
	}




	@Override
	public String toString() {
		return "Store [id=" + id + ", user_id=" + user_id + ", name=" + name + ", address=" + address + ", description="
				+ description + ", deletable=" + deletable + ", created_at=" + created_at + "]";
	}



	public String getText() {
		return "Store [id=" + id +  ", name=" + name + ", address=" + address + ", description="
				+ description + ", deletable=" + deletable + ", created_at=" + created_at + "]";
	}

	



	
	
}
