package com.seatbooking.springboot.dto;

import com.seatbooking.springboot.entity.User;
import lombok.Data;

@Data
public class ClientDTO {
    private User userInfo;
    private String department;
    private Integer type;
    private Integer isBookMeet;
    private Integer isBookSeat;
    private Integer isBookClass;
    private String school;
}
