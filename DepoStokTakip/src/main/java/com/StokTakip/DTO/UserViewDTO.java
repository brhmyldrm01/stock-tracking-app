package com.StokTakip.DTO;

import java.util.Date;

import lombok.Data;

@Data
public final class UserViewDTO {

// DATA TRANSFER OBJECT USER VİEW
	
    private String name;
    private String password;
    private String email;
	private Date created_at;
	private Date updated_at;
	
}
