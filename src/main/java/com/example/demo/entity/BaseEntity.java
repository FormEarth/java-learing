package com.example.demo.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BaseEntity {

	private String creator;
	private String updater;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
	private Integer delFlag;
	private String id;
}
