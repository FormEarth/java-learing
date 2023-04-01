package com.example.demo.dto;

import com.example.demo.global.Common;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author raining_heavily
 * @date 2023/3/14 10:41
 */
@Data
public class DateRequestDTO {

    /**
     * 在全局已指定日期格式时，可以使用@JsonFormat重新指定格式
     */
    @JsonFormat(pattern = Common.DATE_TIME_PATTERN)
    private LocalDateTime dateTime;
    private LocalDate date;
    private LocalTime time;

}
