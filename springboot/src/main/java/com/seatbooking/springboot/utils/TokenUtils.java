package com.seatbooking.springboot.utils;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.seatbooking.springboot.entity.User;
import com.seatbooking.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class TokenUtils {

    private static UserMapper staticUserMapper;

    @Resource
    private UserMapper userMapper;

    @PostConstruct
    public void setUserService(){
        staticUserMapper = userMapper;
    }

//    生成token
    public static String genToken(String userId, String sign){
        return JWT.create().withAudience(userId)  // 将userId保存到token里面作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(),2))  //  2小时后token过期
                .sign(Algorithm.HMAC256(sign));  // 已password作为token的密钥
    }

    public static User getCurrentUser(){
        try{
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                String token = request.getHeader("token");
                if(StrUtil.isNotBlank(token)) {
                    String userId = JWT.decode(token).getAudience().get(0);
                    User user = staticUserMapper.selectById(Integer.valueOf(userId));
                    user.setPassword("***");
                    return user;
                }
            } catch (Exception e){
                return null;
            }
        return null;
    }
}
