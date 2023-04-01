package com.example.demo.entity.system;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Base {

    private String id;
    private LocalDateTime createTime;
    private String createdBy;
    private LocalDateTime updateTime;
    private String updatedBy;
}
