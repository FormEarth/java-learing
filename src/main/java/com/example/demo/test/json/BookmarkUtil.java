package com.example.demo.test.json;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookmarkUtil {

    public static final ObjectMapper MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

    String googleBookmarkPath = "C:/Users/Administrator/AppData/Local/Google/Chrome/User Data/Default/Bookmarks";
    String edgeBookmarkPath = "C:/Users/Administrator/AppData/Local/Microsoft/Edge/User Data/Default/Bookmarks";

    private void readBookmark() {

        File file = new File("E:/test/books.json");
        if (file.exists()) {
            System.out.println("------> " + file.length());
        } else {
            return;
        }
        BookmarkContent content = null;
        try {
            content = MAPPER.readValue(file, BookmarkContent.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(content);
        Bookmark bookmark = content.getRoots().getBookmark_bar();
        List<Bookmark> result = new ArrayList<>();
        getItems(bookmark.getChildren(), result);
        System.out.println("total:" + result.size());
        result.forEach(System.out::println);
    }

    // 递归获取所有
    private void getItems(List<Bookmark> children, List<Bookmark> result) {

        for (Bookmark item : children) {
            if ("folder".equals(item.getType())) {
                if (item.getChildren() != null && item.getChildren().size() > 0) {
                    getItems(item.getChildren(), result);
                }
            } else if ("url".equals(item.getType())) {
                result.add(item);
            }
        }
    }


    public static void main(String[] args) {
        BookmarkUtil util = new BookmarkUtil();
        util.readBookmark();
//        readBookmark();
    }

}

