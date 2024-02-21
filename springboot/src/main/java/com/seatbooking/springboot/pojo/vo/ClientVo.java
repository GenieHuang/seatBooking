package com.seatbooking.springboot.pojo.vo;

import lombok.Data;

@Data
public class ClientVo {
    private String username;
    private String name;
    private String school;
    private Integer type;
    private String department;
}
