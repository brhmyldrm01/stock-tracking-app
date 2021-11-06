package com.StokTakip.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public final class CategoryCreateDTO {
	// DATA TRANSFER OBJECT USER
	
    @NotNull(message="{StokTakip.constraints.Notnull.message}")
    @NotEmpty
    private int user_id;
    
    @NotNull(message="{StokTakip.constraints.Notnull.message}")
    @NotEmpty
    private String name;
    
    @NotNull(message="{StokTakip.constraints.Notnull.message}")
    @NotEmpty
    private String description;

    
}
