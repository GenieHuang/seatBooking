package com.seatbooking.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.seatbooking.springboot.config.Constants;
import com.seatbooking.springboot.config.Result;
import com.seatbooking.springboot.dto.ClientDTO;
import com.seatbooking.springboot.dto.PasswordDTO;
import com.seatbooking.springboot.dto.UserDTO;
import com.seatbooking.springboot.entity.User;
import com.seatbooking.springboot.exception.ServiceException;
import com.seatbooking.springboot.mapper.UserMapper;
import com.seatbooking.springboot.pojo.vo.page.UserPageVo;
import com.seatbooking.springboot.pojo.vo.UserVo;
import com.seatbooking.springboot.service.UserService;
import com.seatbooking.springboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.lang.Console.error;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

//    登录接口
    @PostMapping("/login")
    public Result<?> login(@RequestBody UserDTO userDTO){
        System.out.println(userDTO);
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error("-1","用户名或密码不能为空");
        }

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        wrapper.eq(User::getPassword,password);

        try{
            User res = userMapper.selectOne(wrapper);

            if(res == null){
                return Result.error("-1","用户名或密码错误");
            }else{

                if (res.getStatus() == 1){
                    return Result.error("-1","当前用户已被禁用");
                }else {

                    String token = TokenUtils.genToken(res.getUserId().toString(), res.getPassword());
                    userDTO.setToken(token);
                    userDTO.setName(res.getName());
                    userDTO.setRole(res.getRole());
                    userDTO.setPassword("***");
                    return Result.success(userDTO);
                }
            }

        }catch (Exception e){
            error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }

    }


//     分页查询接口
    @GetMapping("/page")
    public Result<?> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        UserPageVo pageVo = new UserPageVo();
        pageVo.setPageNum(pageNum);
        pageVo.setPageSize(pageSize);
        pageNum = (pageNum - 1) * pageSize;
        List<UserVo> data = userMapper.selectPage(pageNum,pageSize);
        Integer total = userMapper.selectTotal();
        pageVo.setData(data);
        pageVo.setTotal(total);
        return Result.success(pageVo);
    }

//    按条件搜索
    @GetMapping("/search")
    public Result<?> searchDetailInfo(@RequestParam(required=false) String username,
                                                @RequestParam(required=false) String name,
                                                @RequestParam(required=false) Integer status,
                                                @RequestParam Integer pageNum,
                                                @RequestParam Integer pageSize){
        UserPageVo pageVo = new UserPageVo();
        pageVo.setPageNum(pageNum);
        pageVo.setPageSize(pageSize);
        pageNum = (pageNum - 1) * pageSize;
        List<UserVo> data = userMapper.searchList(username, name, status, pageNum,pageSize);
        Integer total = userMapper.searchTotal(username, name, status);
        pageVo.setData(data);
        pageVo.setTotal(total);
        return Result.success(pageVo);
    }

//    编辑用户信息
    @GetMapping("/audit")
    public Result<?> auditInfo(@RequestParam(required=false) String name,
                             @RequestParam(required=false) String username,
                             @RequestParam(required=false) String school,
                             @RequestParam(required=false) Integer type,
                             @RequestParam(required=false) Integer role,
                             @RequestParam(required = false) String department,
                             @RequestParam Integer userId){
        return Result.success(userMapper.updateInfo(name, username, school, type, role, department,userId));
    }

//    更改用户状态t_user.status
    @GetMapping("/status")
    public Result<?> switchStatus(@RequestParam Integer userId){
        return Result.success(userMapper.updateStatus(userId));
    }

//    获取当前用户信息
    @PostMapping
    public Result<?> currentUserInfo() {
        ClientDTO currentUser = new ClientDTO();
//        User currentUser = TokenUtils.getCurrentUser();
        System.out.println(TokenUtils.getCurrentUser());
        try {
            if(TokenUtils.getCurrentUser() == null){
                return Result.error("-1","用户未登录");
            }
            currentUser.setUserInfo(TokenUtils.getCurrentUser());
            currentUser.setType(userMapper.searchType(currentUser.getUserInfo().getUserId()));
            currentUser.setDepartment(userMapper.searchDepartment(currentUser.getUserInfo().getUserId()));
            currentUser.setIsBookMeet(userMapper.searchBookMeet(currentUser.getUserInfo().getUserId()));
            currentUser.setIsBookSeat(userMapper.searchBookSeat(currentUser.getUserInfo().getUserId()));
            currentUser.setIsBookClass(userMapper.searchBookClass(currentUser.getUserInfo().getUserId()));
            currentUser.setSchool(userMapper.searchSchool(currentUser.getUserInfo().getUserId()));

            System.out.println("获取当前用户信息 ================>" + currentUser.getUserInfo().getName());

            return Result.success(currentUser);
        } catch (Exception e) {
            error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
    }
//    修改密码
    @PostMapping("/changePassword")
    @ResponseBody
    public Result<?> changePassword(@RequestBody PasswordDTO passwordDTO){

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserId,passwordDTO.getUserId());

        try{
            User res = userMapper.selectOne(wrapper);

            if(res == null){
                return Result.error("-1","用户不存在");
            }

            if(!passwordDTO.getOldPassword().equals(res.getPassword())){
                return Result.error("-1","旧密码错误");
            }

            return Result.success(userMapper.changePassword(passwordDTO.getUserId(),passwordDTO.getNewPassword()));
        }catch (Exception e){
            error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }

    }

//    新增或更新用户
    @PostMapping("/insertOrUpdateUser")
    @ResponseBody
    public Result<?> insertOrUpdateUser(@RequestBody ClientDTO clientDTO){
        Integer res = userService.insertOrUpdate(clientDTO);
        return Result.success(res);
    }
}

