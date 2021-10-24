package com.example.demo.test.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class FileSource {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static int BIT_NUMBER = 1024;

	private String id;
	private String originalName;
	private String type;
	private Long size;

	private Date createTime;
	private Date lastModifiedTime;

	@Override
	public String toString() {
		return "FileSource [id=" + id + ", originalName=" + originalName + ", type=" + type + ", size="
				+ sizeFormat(size) + ", createTime=" + (createTime == null ? "null" : sdf.format(createTime))
				+ ", lastModifiedTime=" + (lastModifiedTime == null ? "null" : sdf.format(lastModifiedTime)) + "]";
	}

	String sizeFormat(long size) {
		BigDecimal result = new BigDecimal(size);
		String flag = "";
		if (size < BIT_NUMBER) {
			flag = "B";
		} else if (size < BIT_NUMBER * BIT_NUMBER) {
			result = BigDecimal.valueOf(size).divide(BigDecimal.valueOf(BIT_NUMBER));
			flag = "K";
		} else if (size < BIT_NUMBER * BIT_NUMBER * BIT_NUMBER) {
			result = BigDecimal.valueOf(size).divide(BigDecimal.valueOf(BIT_NUMBER * BIT_NUMBER));
			flag = "M";
		} else if (size < BIT_NUMBER * BIT_NUMBER * BIT_NUMBER * BIT_NUMBER) {
			result = BigDecimal.valueOf(size).divide(BigDecimal.valueOf(BIT_NUMBER * BIT_NUMBER * BIT_NUMBER));
			flag = "G";
		}
		return result.setScale(2, BigDecimal.ROUND_DOWN) + flag;//舍弃多余位数
	}

}
