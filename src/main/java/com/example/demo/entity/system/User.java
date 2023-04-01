package com.example.demo.entity.system;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.common.Enums;
import com.example.demo.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cy.W
 * @date 2022-10-12 10:00:04
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class User extends Base {

	private Enums.USER_STATE state;
	private String password;
	private Boolean passwordRest; // 需要重置密码
	private LocalDateTime passwordRestTime;
	private String phone;
	private String mail;

	// Login
	private String loginToken;
	private Integer loginFailedCount;
	private String loginIp;
	private LocalDateTime loginTime;

	private String name;
	private Integer gender;
	private LocalDate birthday;

	private String avatar;
	private String frontCover;
	private String sign;
	private String profile;

}
