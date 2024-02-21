package com.seatbooking.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@TableName("t_user")
@Data
public class User {
    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer userId;
    private String username;
    private String name;
//    @JsonIgnore
    private String password;
    private Integer role;
    private Integer status;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp createTime;

    @JsonIgnore
    public String getPassword(){ return password;}

    @JsonProperty
    public void setPassword(String password){this.password = password;}


}
