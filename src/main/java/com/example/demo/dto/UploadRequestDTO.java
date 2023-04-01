package com.example.demo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author raining_heavily
 * @date 2023/3/8 14:52
 */
@Data
public class UploadRequestDTO {

    private MultipartFile[] files;
    private String txt;
    private Long[] lastModifies;

}
