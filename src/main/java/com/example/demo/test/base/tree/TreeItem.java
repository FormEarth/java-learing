package com.example.demo.test.base.tree;

import lombok.Data;

import java.util.List;

/**
 * @author raining_heavily
 * @date 2023/3/15 17:39
 */
@Data
public class TreeItem {

    private String id;
    private String pid;
    private String name;
    private List<TreeItem> children;

    TreeItem() {

    }

    TreeItem(String id, String name, String pid) {
        this.id = id;
        this.name = name;
        this.pid = pid;
    }

}
