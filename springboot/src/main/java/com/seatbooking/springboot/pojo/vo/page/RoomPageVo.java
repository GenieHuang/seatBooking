package com.seatbooking.springboot.pojo.vo.page;

import com.seatbooking.springboot.entity.Room;
import lombok.Data;

import java.util.List;

@Data
public class RoomPageVo {

    private List<Room> data;
    private Integer total;
    private Integer pageNum;
    private Integer pageSize;
}
