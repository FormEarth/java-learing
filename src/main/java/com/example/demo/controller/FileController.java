package com.example.demo.controller;

import com.example.demo.dto.UploadRequestDTO;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * 文件的相关接口
 *
 * @author raining_heavily
 * @date 2023/3/7 16:56
 */
@RestController
@Controller
public class FileController {

    /**
     * 单文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("file")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            System.out.println("file is empty");
            return "";
        }

        return file.getOriginalFilename();
    }

    /**
     * 多文件上传
     *
     * @param files
     * @return
     */
    @PostMapping("files")
    public String uploadFiles(@RequestParam("files") MultipartFile[] files) {

        if (files == null || files.length == 0) {
            return "no file";
        }

        return files.length + " files upload";
    }

    private File dir = new File("E:\\test");

    /**
     * 文件包含在对象中
     *
     * @param dto
     * @return
     */
    @PostMapping("files-object")
    public String uploadWithObject(UploadRequestDTO dto) throws IOException {

        if (dto.getFiles() == null || dto.getFiles().length == 0) {
            return "no file";
        }
        int i = 0;
        for(MultipartFile file : dto.getFiles()) {
            File temp = new File(dir, Objects.requireNonNull(file.getOriginalFilename()));
            file.transferTo(temp);
            if(dto.getFiles().length == dto.getLastModifies().length) {
                temp.setLastModified(dto.getLastModifies()[i]);
            }
        }

        return dto.getFiles().length + " files upload";
    }

    /**
     * 文件下载
     *
     * @return
     */
//    @GetMapping("file")
    public ResponseEntity<FileSystemResource> downloadFile() {


        String fileName = "download.txt";
        URL url = this.getClass().getClassLoader().getResource(fileName);
        System.out.println("url:" + url);
        ClassPathResource classPathResource = new ClassPathResource(fileName);
        classPathResource.getPath();
        System.out.println("resource:" + classPathResource.getPath());

        String contentDisposition = ContentDisposition
                .builder("attachment")
                .filename(fileName)
                .build().toString();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
//                .header("File-Name", fileName)
//                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,"File-Name")//指定浏览器允许访问该属性
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new FileSystemResource(classPathResource.getPath()));
    }

}
