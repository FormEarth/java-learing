package com.example.demo.test.db;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductAttrProperty {

    private Long id;
    /**
     * 对应的mib值
     */
    private String attrProperty;
    /**
     * web值
     */
    private String attrPropertyWeb;
    /**
     * 产品id
     */
    private String productId;
    /**
     * 目录id
     */
    private Long directoryId;
    /**
     * input,password,select,radio,number,datetime
     */
    private String propertyType;
    /**
     * 排序
     */
    private Integer propertyOrder;
    /**
     * 最大长度
     */
    private Integer propertyLength;
    /**
     * 对应的数据字典
     */
    private String actionDict;
    /**
     * 1.只读，0.不是
     */
    private Integer isReadOnly;
    /**
     * 1.动态参数,0.静态
     */
    private Integer hasActive;
    private Integer hasStatic;
    private Integer hasSms;
    private Integer hasHidden;
    private Integer hasFiled;
    private Boolean hasBatch;
    private Boolean hasCount;
    private String filedName;
    private String groupName;
    /**
     * 动态参数初始下标
     */
    private Integer activeIndexStart;
    /**
     * 动态参数结束下标
     */
    private Integer activeIndexEnd;
    /**
     * 占位符用于设备解析
     */
    private String activePlaceholder;
    private String propertyUnit;
    private String timeFormat;
    private Integer accuracy;
    private Integer scopeMin;
    private Integer scopeMax;

    private String remark;
    private String remarkDetail;
    private String category;
    private String directory;
    private String valueRange;

}
