package com.example.demo.test.json;

import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * 
 * @author raining_heavily
 * @date  2023/3/6 10:37
 */
@Data
public class Bookmark {

    private List<Bookmark> children;
    private String date_added;
    private String date_last_used;
    private String date_modified;
    private String guid;
    private String id;
    private String name;
    private String source;
    private String type;
    private Boolean show_icon;
    private String url;

    //name和url都相同的视为一个
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Bookmark b = (Bookmark) obj;
        return Objects.equals(b.name, this.name) && Objects.equals(b.url, this.url);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name + this.url);
    }

}
