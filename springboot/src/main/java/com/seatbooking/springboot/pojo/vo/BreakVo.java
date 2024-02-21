package com.seatbooking.springboot.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class BreakVo {
    private Integer breakId;
    private String name;
    private String username;
    private String bookId;
    private Integer userId;
    private String reason;
    private Integer breakStatus;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp createBook;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp breakTime;
}
