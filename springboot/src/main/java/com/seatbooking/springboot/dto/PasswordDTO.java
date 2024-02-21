package com.seatbooking.springboot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PasswordDTO {
    private Integer userId;
    @JsonIgnore
    private String oldPassword;
    @JsonIgnore
    private String newPassword;

}
