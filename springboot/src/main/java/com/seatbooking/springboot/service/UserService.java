package com.seatbooking.springboot.service;

import com.seatbooking.springboot.dto.ClientDTO;
import com.seatbooking.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public Integer insertOrUpdate(ClientDTO clientDTO){
        if(clientDTO.getUserInfo().getUserId() == null){
            // 新增
            userMapper.insert(clientDTO.getUserInfo());
            return userMapper.insertInfo(clientDTO.getUserInfo().getUserId(),clientDTO.getSchool(),clientDTO.getType(),clientDTO.getDepartment());
        }else{
            // 更新
            return userMapper.updateInfo(clientDTO.getUserInfo().getName(),
                    clientDTO.getUserInfo().getUsername(),
                    clientDTO.getSchool(),
                    clientDTO.getType(),
                    clientDTO.getUserInfo().getRole(),
                    clientDTO.getDepartment(),
                    clientDTO.getUserInfo().getUserId());
        }
    }

}
