package com.seatbooking.springboot.mapper;


import com.seatbooking.springboot.entity.Board;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardMapper {

//    保存公告

    @Insert("insert into t_board(title, msg, b_url) values (#{title},#{msg},#{bUrl})")
    Integer insert(@Param("title") String title,
                   @Param("msg") String msg,
                   @Param("bUrl") String bUrl);

//    查询最新公告
    @Select("select * from t_board order by board_time desc limit 1")
    List<Board> selectBoard();

    @Select("select * from t_board order by board_time desc limit #{pageNum},#{pageSize} ")
    List<Board> searchList(@Param("pageNum") Integer pageNum,
                           @Param("pageSize") Integer pageSize);

    @Select("select count(*) from t_board")
    Integer searchTotal();
}
