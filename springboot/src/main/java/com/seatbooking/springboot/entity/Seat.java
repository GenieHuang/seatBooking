package com.seatbooking.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_seat")
@Data
public class Seat {

    @TableId(value = "seat_id",type = IdType.AUTO)
    private Integer seatId;

    @TableField(value = "s_code")
    private String sCode;

    @TableField(value = "r_code")
    private String rCode;
    private Integer roomId;

}
