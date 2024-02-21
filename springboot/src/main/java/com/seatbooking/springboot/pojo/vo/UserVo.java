package com.seatbooking.springboot.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import java.sql.Timestamp;

@Data
public class UserVo {

    private Integer userId;
    private String username;
    private String name;
    private Integer role;
    private Integer status;
    private String school;
    private Integer type;
    private String department;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp createTime;
    private Integer isBookMeet;
    private Integer isBookSeat;
}
