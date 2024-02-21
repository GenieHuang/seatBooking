package com.seatbooking.springboot.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface SeatMapper {

//    插入新座位
    @Insert("insert into t_seat(s_code,r_code,room_id) values ( #{sCode}, #{rCode}, #{roomId})")
    Integer insertSeatInfo(@Param("roomId") Integer roomId,
                           @Param("rCode") String rCode,
                           @Param("sCode") String sCode);

//    查询座位数量
    @Select("select num from t_room where room_id = #{roomId}")
    Integer selectSeatNum(@Param("roomId") Integer roomId);

//    删除座位
    @Delete("delete from t_seat where room_id = #{roomId} and s_code = #{sCode}")
    Integer deleteSeatInfo(@Param("roomId") Integer roomId,
                           @Param("sCode") String sCode);
}
