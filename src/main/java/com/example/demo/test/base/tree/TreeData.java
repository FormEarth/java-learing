package com.example.demo.test.base.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 树形结构的归纳
 *
 * @author cy.W
 * @date 2022-6-2 11:18:38
 */
public class TreeData {

    static List<TreeItem> list;

    static {
        list = new ArrayList<>();
        list.add(new TreeItem("1", "河北省", "0"));
        list.add(new TreeItem("2", "沧州市", "1"));
        list.add(new TreeItem("3", "华新区", "2"));
        list.add(new TreeItem("4", "运河区", "2"));
        list.add(new TreeItem("5", "河南省", "0"));
        list.add(new TreeItem("6", "开封市", "5"));
        list.add(new TreeItem("7", "鼓楼区", "6"));
        list.add(new TreeItem("8", "龙亭区", "6"));
    }

    public List<TreeItem> getTree() {
        return this.listToTree(list, "0");
    }

    private List<TreeItem> listToTree(List<TreeItem> list, String pid) {
        return list.stream()
                //过滤一级节点
                .filter(parent -> parent.getPid().equals(pid))
                //对二级节点和三级递归进行归纳，归纳到对应的父节点上
                .peek(child -> child.setChildren(listToTree(list, child.getId()))).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        String data = "";
    }

}
