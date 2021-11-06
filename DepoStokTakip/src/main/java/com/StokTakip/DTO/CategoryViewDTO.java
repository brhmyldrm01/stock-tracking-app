package com.StokTakip.DTO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public final class CategoryViewDTO {

// DATA TRANSFER OBJECT USER VİEW
	



	private int user_id;
	private String name;
	private String description;
	private boolean deletable;
	private Date created_at;
}
