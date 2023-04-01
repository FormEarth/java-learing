package com.example.demo.entity.system;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true)
public class File extends Base {

    private String name;
    private String path;
    private Long size;
    private String remark;
    private Boolean open; //公开的
    private String objectId; // 关联对象的id
    private List<String> tagIds;
    private List<Tag> tags;

    public void getAllTag() {
        this.tagIds.forEach(String::getClass);
    }

}
