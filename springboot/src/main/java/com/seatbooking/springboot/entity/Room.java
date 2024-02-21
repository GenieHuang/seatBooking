package com.seatbooking.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@TableName("t_room")
@Data
public class Room {

    @TableId(value = "room_id",type = IdType.AUTO)
    private Integer roomId;

    @TableField(value = "r_code")
    private String rCode;

    @TableField(value = "r_type")
    private Integer rType;

    private Integer num;

    @TableField(value = "r_status")
    private Integer rStatus;

    @TableField(value = "r_url")
    private String rUrl;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp createRoom;
    private String floor;
    private String district;
    private String roomName;
    private Boolean isResearch;
    private String roomDepart;
}
