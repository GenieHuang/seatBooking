package com.seatbooking.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seatbooking.springboot.entity.User;
import com.seatbooking.springboot.pojo.vo.UserVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {

//    分页查询

    @Select("select  a.user_id, a.name, a.username, a.role, a.status, a.create_time,c.school, c.type, IFnull(total, 0), IFNull(total2, 0) " +
        "from t_user a left join (select user_id, count(*) as total from t_book where book_type = 1 and book_status = 1 group by user_id) b on a.user_id = b.user_id " +
        "left join (select user_id, count(*) as total2 from t_book where book_type = 2 and book_status = 1 group by user_id) d on a.user_id = d.user_id, t_client c " +
        "where a.user_id = c.user_id order by a.create_time desc limit #{pageNum},#{pageSize} ")
    @Results({
            @Result(id = true, property = "userId",column = "user_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "username", column = "username"),
            @Result(property = "role", column = "role"),
            @Result(property = "status", column = "status"),
            @Result(property = "school", column = "school"),
            @Result(property = "type", column = "type"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "isBookMeet", column = "IFNull(total, 0)"),
            @Result(property = "isBookSeat", column = "IFNull(total2, 0)")

    })
    List<UserVo> selectPage(Integer pageNum, Integer pageSize);

//    分页查询
    @Select("select count(*) from t_user")
    Integer selectTotal();

//    搜索分页查询
    List<UserVo> searchList(@Param("username") String username,
                            @Param("name") String name,
                            @Param("status") Integer status,
                            Integer pageNum,
                            Integer pageSize);

//    搜索分页查询
    Integer searchTotal(@Param("username") String username,
                        @Param("name") String name,
                        @Param("status") Integer status);

//    编辑用户信息
    Integer updateInfo(@Param("name") String name,
                       @Param("username") String username,
                       @Param("school") String school,
                       @Param("type") Integer type,
                       @Param("role") Integer role,
                       @Param("department") String department,
                       @Param("userId") Integer userId );

//    更改用户状态t_user.status
    @Update("update t_user set status = !status where user_id = #{userId}")
    Integer updateStatus(@Param("userId") Integer userId);

//    查询用户身份
    @Select("select type from t_client where user_id = #{userId}")
    Integer searchType(@Param("userId") Integer userId);

//    查询用户所在系
    @Select("select department from t_client where user_id = #{userId}")
    String searchDepartment(@Param("userId") Integer userId);

    @Select("select school from t_client where user_id = #{userId}")
    String searchSchool(@Param("userId") Integer userId);

//    查询用户是否预约会议室
    @Select("select count(*) as isBookMeet from t_book where book_type = 1 and (book_status = 1 or book_status = 6) and user_id = #{userId}")
    Integer searchBookMeet(@Param("userId") Integer userId);

    @Select("select count(*) as isBookSeat from t_book where book_type = 2 and (book_status = 1 or book_status = 6) and user_id = #{userId}")
    Integer searchBookSeat(@Param("userId") Integer userId);

    @Select("select count(*) as isBookClass from t_book where book_type = 3 and (book_status = 1 or book_status = 6) and user_id = #{userId}")
    Integer searchBookClass(@Param("userId") Integer userId);

//    前台修改用户密码
    @Update("update t_user set password = #{password} where user_id = #{userId}")
    Integer changePassword(@Param("userId") Integer userId,
                           @Param("password") String password);

//    前台新增用户
    int insert(@Param("userInfo") User userInfo);

//    前台新增用户基本信息
    Integer insertInfo(@Param("userId") Integer userId,
                       @Param("school") String school,
                       @Param("type") Integer type,
                       @Param("department") String department);

}
