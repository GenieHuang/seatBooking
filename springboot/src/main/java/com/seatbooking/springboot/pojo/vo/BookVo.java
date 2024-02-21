package com.seatbooking.springboot.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class BookVo {
    private String bookId;
    private Integer userId;
    private String name;
    private String username;
    private Integer bookType;
    private Integer roomId;
    private Integer seatId;
    private String rCode;
    private String sCode;
    private String roomName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createBook;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date bookDate;

    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private Timestamp startTime;

    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private Timestamp endTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp reviewTime;

    private Integer bookStatus;
    private String reason;
    private String topic;
    private String advisor;
}
