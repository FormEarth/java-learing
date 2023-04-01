package com.example.demo.controller;/**
 * @author raining_heavily
 * @date 2023/3/6 11:17
 */

import com.example.demo.dto.LoginResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author raining_heavily
 * @date 2023/3/6 11:17
 */
@RestController
public class LoginController {

    @PostMapping("/user/login")
    public LoginResponseDTO login() {
        return new LoginResponseDTO();
    }

    @PostMapping("/user/login/auto")
    public LoginResponseDTO autoLogin(@RequestHeader String authorization) {

        // 检查token是否

        return new LoginResponseDTO();

    }

    @GetMapping("/user/info")
    public void queryUserInfo() {

    }
}
