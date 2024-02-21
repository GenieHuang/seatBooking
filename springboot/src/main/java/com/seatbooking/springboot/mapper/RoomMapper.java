package com.seatbooking.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seatbooking.springboot.entity.Room;
import com.seatbooking.springboot.pojo.vo.BookedListVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoomMapper{


//    分页查询（会议室/自习室）

    @Select("select * from t_room where r_type = #{rType} order by create_room desc limit #{pageNum},#{pageSize}")
    List<Room> selectPage(Integer rType, Integer pageNum, Integer pageSize);

//    分页查询（会议室/自习室）

    @Select("select count(*) from t_room where r_type = #{rType}")
    Integer selectTotal(Integer rType);

//    搜索分页查询(会议室/自习室/教室）

    List<Room> searchList(@Param("rCode") String rCode,
                          @Param("rStatus") Integer rStatus,
                          @Param("rType") Integer rType,
                          Integer pageNum,
                          Integer pageSize);

//    搜索分页查询(会议室/自习室/教室）

    Integer searchTotal(@Param("rCode") String rCode,
                        @Param("rStatus") Integer rStatus,
                        @Param("rType") Integer rType);

//    更改会议室/自习室/教室状态t_room.r_status

    @Update("update t_room set r_status = !r_status where room_id = #{roomId}")
    Integer updateStatus(@Param("roomId") Integer roomId);


//    会议室信息管理页面:

//    1.1 新增会议室信息

    @Insert("insert into t_room(r_code, r_type, r_status, floor, district, room_name,is_research,room_depart) " +
            "values (#{rCode}, 1, 0, #{floor}, #{district}, #{roomName},#{isResearch},#{roomDepart})")
    Integer insertMeetInfo(@Param("rCode") String rCode,
                           @Param("district") String district,
                           @Param("roomName") String roomName,
                           @Param("floor") String floor,
                           @Param("isResearch") Boolean isResearch,
                           @Param("roomDepart") String roomDepart);

//    1.2 更新会议室信息
    Integer updateMeetInfo(@Param("rCode") String rCode,
                           @Param("district") String district,
                           @Param("roomName") String roomName,
                           @Param("floor") String floor,
                           @Param("roomId") Integer roomId,
                           @Param("isResearch") Boolean isResearch,
                           @Param("roomDepart") String roomDepart);


//    自习室信息管理页面:

//    2.1 新增自习室信息

    @Insert("insert into t_room(r_code, r_type, num, r_status, r_url, floor, district, room_name) " +
            "values ( #{rCode},2, #{num},0, #{rUrl}, #{floor}, #{district} ,#{roomName})")
    Integer insertStudyInfo(@Param("rCode") String rCode,
                           @Param("num") Integer num,
                            @Param("roomName") String roomName,
                           @Param("district") String district,
                           @Param("floor") String floor,
                           @Param("rUrl") String rUrl);
//    2.2 更新自习室信息
    Integer updateStudyInfo(@Param("rCode") String rCode,
                           @Param("num") Integer num,
                            @Param("roomName") String roomName,
                           @Param("district") String district,
                           @Param("floor") String floor,
                           @Param("rUrl") String rUrl,
                           @Param("roomId") Integer roomId);

//    教室信息管理页面：

//    3.1 新增教室信息

    @Insert("insert into t_room(r_code,r_type,num,r_status,floor,room_name) values (#{rCode},3,#{num},0,#{floor},#{roomName})")
    Integer insertClassInfo(@Param("rCode") String rCode,
                            @Param("num") Integer num,
                            @Param("roomName") String roomName,
                            @Param("floor") String floor);
//    3.2 更新教室信息
    Integer updateClassInfo(@Param("rCode") String rCode,
                            @Param("num") Integer num,
                            @Param("roomName") String roomName,
                            @Param("floor") String floor,
                            @Param("roomId") Integer roomId);
//    查找新增自习室roomId
    @Select("select room_id from t_room where r_code = #{rCode} and num = #{num} order by create_room desc limit 0,1")
    Integer searchRoomId(@Param("rCode") String rCode,
                         @Param("num") Integer num);

//    前台：查询所有房间
    @Select("select a.room_id,a.r_code,a.room_name,a.room_depart,a.num,a.r_url,a.is_research,b.seat_id ,b.s_code from t_room a left join t_seat b on a.room_id = b.room_id where r_status = 0 and r_type = #{rType}")
    List<BookedListVo> searchAllRoomId(@Param("rType") Integer rType);
}
