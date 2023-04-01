package com.example.demo.entity.system;

import com.example.demo.test.util.Util;
import com.example.demo.tool.RequestUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class Tag extends Base {

    private String text;
    private Boolean open; //公开的
    private String pid; //父节点id
    private List<Tag> children;

    Tag(String id, String text, String pid) {
        super.setId(id);
        this.text = text;
        this.pid = pid;
    }

    Tag() {

    }

    public static List<Tag> streamToTree(List<Tag> nodeList, String pid) {

        return nodeList.stream()
                //过滤指定节点
                .filter(parent -> parent.getPid().equals(pid))
                //归纳子节点到对应的父节点上
                .peek(tag -> tag.setChildren(streamToTree(nodeList, tag.getId()))).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Tag> allTags = new ArrayList<>();
        allTags.add(new Tag("1", "河北省", "0"));
        allTags.add(new Tag("2", "沧州市", "1"));
        allTags.add(new Tag("3", "华新区", "2"));
        allTags.add(new Tag("4", "运河区", "2"));
        allTags.add(new Tag("5", "河南省", "0"));
        allTags.add(new Tag("6", "开封市", "5"));
        allTags.add(new Tag("7", "鼓楼区", "6"));
        allTags.add(new Tag("8", "龙亭区", "6"));
        allTags.add(new Tag("9", "XX市", "1"));
        List<Tag> list = streamToTree(allTags, "0");
        System.out.println(Util.toJsonStr(list));
    }

}
