package com.seatbooking.springboot.controller;

import com.seatbooking.springboot.config.Result;
import com.seatbooking.springboot.entity.User;
import com.seatbooking.springboot.mapper.ClientMapper;
import com.seatbooking.springboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientMapper clientMapper;

//    用户个人信息
    @PostMapping("/info")
    public Result<?> clientInfo(@RequestBody User user){
        Integer userId = user.getUserId();
        return Result.success(clientMapper.searchClientInfo(userId));
    }
}
