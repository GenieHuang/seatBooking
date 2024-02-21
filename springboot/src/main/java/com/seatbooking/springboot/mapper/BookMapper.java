package com.seatbooking.springboot.mapper;

import com.seatbooking.springboot.entity.Book;
import com.seatbooking.springboot.pojo.vo.BookVo;
import com.seatbooking.springboot.pojo.vo.BookedListVo;
import com.seatbooking.springboot.pojo.vo.FrontBookVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper {

//    分页查询
    @Select("select a.*, b.name, b.username,c.r_code,c.room_name, d.s_code " +
            "from t_book a left join t_user b on a.user_id = b.user_id " +
            "left join t_room c on a.room_id = c.room_id " +
            "left join t_seat d on a.seat_id = d.seat_id order by a.create_book desc limit #{pageNum},#{pageSize}")
    List<BookVo> selectPage(Integer pageNum, Integer pageSize);

//    分页查询
    @Select("select count(*) from t_book")
    Integer selectTotal();

//    搜索分页查询
    List<BookVo> searchList(@Param("bookId") String bookId,
                            @Param("username") String username,
                            @Param("name") String name,
                            @Param("bookType") Integer bookType,
                            @Param("bookStatus") Integer bookStatus,
                            Integer pageNum,
                            Integer pageSize);

//    搜索分页查询
    Integer searchTotal(@Param("bookId") String bookId,
                        @Param("username") String username,
                        @Param("name") String name,
                        @Param("bookType") Integer bookType,
                        @Param("bookStatus") Integer bookStatus);

//    取消预约和编辑预约状态
//    @Update("update t_book set book_status = #{bookStatus} where book_id = #{bookId}")
    Integer updateStatus(@Param("bookId") String bookId,
                         @Param("bookStatus") Integer bookStatus);

//    前台：返回某一用户的预约记录(分页)
    List<FrontBookVo> searchByClient(@Param("bookId") String bookId,
                                     @Param("bookType") Integer bookType,
                                     @Param("bookStatus") Integer bookStatus,
                                     @Param("userId") Integer userId,
                                     Integer pageNum,
                                     Integer pageSize);

//    前台：返回某一用户的预约记录(分页)
    Integer searchByClientTotal(@Param("bookId") String bookId,
                                @Param("bookType") Integer bookType,
                                @Param("bookStatus") Integer bookStatus,
                                @Param("userId") Integer userId);

//    前台：新增预约记录

    Integer insertBookRecord(@Param("bookId") String bookId,
                             @Param("userId") Integer userId,
                             @Param("bookType") Integer bookType,
                             @Param("roomId") Integer roomId,
                             @Param("seatId") Integer seatId,
                             @Param("bookDate") Date bookDate,
                             @Param("startTime") Timestamp startTime,
                             @Param("endTime") Timestamp endTime,
                             @Param("bookStatus") Integer bookStatus,
                             @Param("reviewTime") Timestamp reviewTime,
                             @Param("reason") String reason,
                             @Param("topic") String topic,
                             @Param("advisor") String advisor);

//    前台：查询同天同房间是否有重复预约段
    Integer checkOverlap(@Param("roomId") Integer roomId,
                         @Param("seatId") Integer seatId,
                         @Param("startTime") Timestamp startTime,
                         @Param("endTime") Timestamp endTime);

//    前台：查询某日期某时段已占用的房间roomId
    @Select("select a.room_id, b.r_code, b.room_name, b.room_depart, b.num, b.r_url,b.is_research,a.seat_id, c.s_code " +
            "from t_book a " +
            "left join t_room b on a.room_id = b.room_id " +
            "left join t_seat c on a.seat_id = c.seat_id " +
            "where a.book_type = #{bookType} " +
            "and a.book_status = (1 or 2 or 6)" +
            "and a.book_date = #{bookDate} " +
            "and ((a.start_time between timestamp(#{startTime}) and timestamp(#{endTime})) " +
            "or (a.end_time between timestamp(#{startTime}) and timestamp(#{endTime})) " +
            "or (a.start_time <= timestamp(#{startTime}) and a.end_time >= timestamp(#{endTime}) ) ) ")
     List<BookedListVo> searchBookedRoomId(@Param("bookType") Integer bookType,
                                           @Param("bookDate") Date bookDate,
                                           @Param("startTime") Timestamp startTime,
                                           @Param("endTime") Timestamp endTime);

//    小程序：查询已预约状态记录
    @Select("select a.book_id, a.book_type, b.room_name, concat_ws('-',b.r_code,c.s_code) as book_space,a.book_date, a.start_time, a.end_time, a.review_time, a.book_status,a.reason,a.topic,a.advisor " +
            "from t_book a " +
            "left join t_room b on a.room_id = b.room_id " +
            "left join t_seat c on a.seat_id = c.seat_id " +
            "where user_id = #{userId} " +
            "and book_status = 1")
    List<FrontBookVo> searchUniBookList(@Param("userId") Integer userId);
}

