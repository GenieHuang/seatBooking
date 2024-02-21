package com.seatbooking.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@TableName("t_break")
@Data
public class Break {

    @TableId(value = "break_id",type = IdType.AUTO)
    private Integer breakId;
    private String bookId;
    private Integer userId;
    private String reason;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp breakTime;
    private Integer breakStatus;

}
