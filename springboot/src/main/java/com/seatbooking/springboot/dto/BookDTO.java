package com.seatbooking.springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class BookDTO {
    private String bookId;
    private Integer userId;
    private Integer bookType;
    private Integer roomId;
    private Integer seatId;
    private Integer bookStatus;
    private String reason;
    private String topic;
    private String advisor;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date bookDate;

//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private Timestamp startTime;

//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private Timestamp endTime;

//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp reviewTime;
}
