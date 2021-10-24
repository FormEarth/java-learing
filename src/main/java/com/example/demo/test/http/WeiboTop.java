package com.example.demo.test.http;

/**
 * 微博热搜
 *
 * @author raining_heavily
 * @date 2020/9/8 22:00
 **/
public class WeiboTop {
    private Integer sort;
    private String topTitle;
    private String topSum;
    private String topTag;
    private String uri;

    public WeiboTop(Integer sort,String topTitle,String uri, String topSum, String topTag) {
        this.sort = sort;
        this.topTitle = topTitle;
        this.uri = uri;
        this.topSum = topSum;
        this.topTag = topTag;
    }

    @Override
    public String toString() {
        return "WeiboTop{" +
                "sort=" + sort +
                ", topTitle='" + topTitle + '\'' +
                ", topSum='" + topSum + '\'' +
                ", topTag='" + topTag + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTopTitle() {
        return topTitle;
    }

    public void setTopTitle(String topTitle) {
        this.topTitle = topTitle;
    }

    public String getTopSum() {
        return topSum;
    }

    public void setTopSum(String topSum) {
        this.topSum = topSum;
    }

    public String getTopTag() {
        return topTag;
    }

    public void setTopTag(String topTag) {
        this.topTag = topTag;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
