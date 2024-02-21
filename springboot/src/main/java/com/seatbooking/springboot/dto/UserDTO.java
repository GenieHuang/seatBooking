package com.seatbooking.springboot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String name;

    private String token;

    private Integer role;

    @JsonIgnore
    public String getPassword(){ return password;}

    @JsonProperty
    public void setPassword(String password){this.password = password;}
}
