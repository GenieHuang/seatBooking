package com.seatbooking.springboot.mapper;

import com.seatbooking.springboot.pojo.vo.BreakVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BreakMapper {

//    新增违约记录
    @Insert("insert into t_break (book_id, user_id, reason, break_status) " +
            "select #{bookId}, #{userId},#{reason}, 0 from dual " +
            "where not exists(select * from t_break where book_id = #{bookId}) ")
    Integer insert(@Param("bookId") String bookId,
                   @Param("userId") Integer userId,
                   @Param("reason") String reason);

//    分页查询

    @Select("select a.*, b.name, b.username, c.create_book from t_break a " +
            "left join t_user b on a.user_id = b.user_id " +
            "left join t_book c on a.book_id = c.book_id order by a.break_time desc")
    List<BreakVo> selectPage(Integer pageNum, Integer pageSize);

//    分页查询
    @Select("select count(*) from t_break")
    Integer selectTotal();

//    搜索分页查询
    List<BreakVo> searchList(@Param("bookId") String bookId,
                             @Param("username") String username,
                             @Param("name") String name,
                             Integer pageNum,
                             Integer pageSize);

//    搜索分页查询
    Integer searchTotal(@Param("bookId") String bookId,
                        @Param("username") String username,
                        @Param("name") String name);

//    更改违约状态 t_break.break_status

    @Update("update t_break set break_status = !break_status where break_id = #{breakId}")
    Integer updateStatus(@Param("breakId") Integer breakId);
}