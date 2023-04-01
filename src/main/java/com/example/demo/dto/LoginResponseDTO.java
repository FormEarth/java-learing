package com.example.demo.dto;

import com.example.demo.entity.system.User;
import lombok.Data;

/**
 * @author raining_heavily
 * @date 2023/3/6 11:45
 */
@Data
public class LoginResponseDTO {

    private User user;
    private String authorization;

}
