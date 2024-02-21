package com.seatbooking.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_client")
@Data
public class Client {

    @TableId(value = "client_id",type = IdType.AUTO)
    private Integer clientId;
    private Integer userId;
    private String school;
    private Integer type;
    private String department;
}
