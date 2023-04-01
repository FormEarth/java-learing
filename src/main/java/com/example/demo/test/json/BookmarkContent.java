package com.example.demo.test.json;

import lombok.Data;

@Data
public class BookmarkContent {

    private String checksum;
    private BookmarkRoots roots;
    private String version;

}