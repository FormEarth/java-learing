package com.example.demo.entity.system;

import com.example.demo.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Role extends BaseEntity{

	private String id;
	private String type;
	private Boolean editable;
	
}
