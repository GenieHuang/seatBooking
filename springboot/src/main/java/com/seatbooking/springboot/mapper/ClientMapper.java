package com.seatbooking.springboot.mapper;

import com.seatbooking.springboot.entity.Client;
import com.seatbooking.springboot.pojo.vo.ClientVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClientMapper {

//    查询用户个人信息
    @Select("select a.name, a.username, b.school, b.type, b.department from t_user a left join t_client b on a.user_id = b.user_id where a.user_id = #{userId}")
    ClientVo searchClientInfo(Integer userId);

//    Integer insert(@Param("userId") Integer userId,
//                   @Param("school") String school,
//                   @Param(""))

}
