package com.example.demo.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;

/**
 * @author raining_heavily
 * @date 2023/3/8 17:47
 */
@Controller
public class DownloadController {

    String fileName = "download.txt";

    @GetMapping("file")
    public ResponseEntity<InputStreamResource> downloadFile() throws IOException {

//        System.out.println("url:" + url);
        ClassPathResource resource = new ClassPathResource(fileName);
        System.out.println("resource:" + resource.getPath());
        byte[] output = FileUtils.readFileToByteArray(resource.getFile());

        String contentDisposition = ContentDisposition
                .builder("attachment")
                .filename(fileName)
                .build().toString();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
//                .header("File-Name", fileName)
//                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,"File-Name")//指定浏览器允许访问该属性
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(this.getClass().getClassLoader().getResourceAsStream(fileName)));
    }

    @GetMapping("file1")
    public ResponseEntity<Resource> getFile() throws IOException {

        ClassPathResource source = new ClassPathResource(fileName);
        InputStreamResource resource = new InputStreamResource(source.getInputStream());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(source.getFile().length()));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(source.getFile().length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping("file-void")
    public void downloadFile1(HttpServletResponse response) throws IOException {

        String fileName = "download.txt";
        URL url = this.getClass().getClassLoader().getResource(fileName);
        System.out.println("url:" + url);
        ClassPathResource resource = new ClassPathResource(fileName);
        System.out.println("resource:" + resource.getPath());

        InputStream in = resource.getInputStream();
        int len;
        byte[] buffer = new byte[1024];
        response.setHeader("content-disposition", String.format("attachment; filename=%s", fileName));
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);

        OutputStream out = response.getOutputStream();
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        out.flush();
        in.close();
    }

}
