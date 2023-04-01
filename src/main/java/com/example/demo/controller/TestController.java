package com.example.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.dto.DateRequestDTO;
import com.example.demo.test.base.tree.TreeData;
import com.example.demo.test.base.tree.TreeItem;
import org.springframework.web.bind.annotation.*;

import com.example.demo.global.Result;

@RestController
public class TestController {

    //当未指明请求方法时，则所有的方法都可以请求
    @RequestMapping(value = "/nomethod")
    public Result nomethod() {
        return Result.success();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Result index(String type) {
        return Result.success();
    }

    @RequestMapping(value = "/test/list", method = RequestMethod.GET)
    public List<String> getList() {
        return Arrays.asList("aa", "bb", "cc", "dd");
    }

    @RequestMapping(value = {"/test/str", "/test/string"}, method = RequestMethod.GET)
    public String getString() {
        return "aa,bb,cc,dd";
    }

    @RequestMapping(value = {"/test/strings"}, method = RequestMethod.GET)
    public String[] getStringArray() {
        return new String[]{"A", "B", "C"};
    }

    @RequestMapping(value = "/test/map", method = RequestMethod.GET)
    public Map<String, String> getMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "aaa");
        map.put("2", "bbb");
        map.put("3", "ccc");
        return map;
    }

    @RequestMapping(value = "/test/except", method = RequestMethod.GET)
    public long except(@RequestParam String name, @RequestParam long number) {
        System.out.println("name:" + name + " number:" + number);
        return 100 / number;
    }

    @PostMapping(value = "/save/content")
    public void post(@RequestBody HashMap<String, String> params) {
        System.out.println(params);

    }

    @GetMapping("ipJson.jsp")
    public Map<String, String> get() {
        HashMap<String, String> map = new HashMap<>();
        map.put("addr", "局域网");
        return map;
    }

    @PostMapping("test/date")
    public DateRequestDTO getSomeDate(@RequestBody DateRequestDTO dto) {

        DateRequestDTO res = new DateRequestDTO();
        res.setDateTime(LocalDateTime.now());
        res.setDate(LocalDate.now());
        res.setTime(LocalTime.now());
        return res;
    }

    @GetMapping("test/tree")
    public List<TreeItem> getTree() {
        TreeData treeData = new TreeData();
        return treeData.getTree();
    }

}
