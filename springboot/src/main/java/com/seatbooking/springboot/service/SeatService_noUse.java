package com.seatbooking.springboot.service;

import com.seatbooking.springboot.mapper.RoomMapper;
import com.seatbooking.springboot.mapper.SeatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.Format;

@Service
public class SeatService_noUse {

    @Autowired
    private SeatMapper seatMapper;

    @Autowired
    private RoomMapper roomMapper;


//    public Integer insertOrUpdateSeat(Integer num, String rCode, Integer roomId){
//        if(roomId == null){  // 新增
//            int j = 0;
//            Format f1 = new DecimalFormat("000");
//            roomId = roomMapper.searchRoomId(rCode,num);
//            for(int i = 1;i<=num;i++){
//                j = j + seatMapper.insertSeatInfo(roomId,rCode,"S"+f1.format(i));
//            }
//            return j;
//        }else{  // 更新
//            int x = 0;
//            x = seatMapper.selectSeatNum(roomId);
//            Format f1 = new DecimalFormat("000");
//            if(num < x) {
//                int j = 0;
//                for (int i = num + 1; i <= x; i++) {
//                    j = j + seatMapper.deleteSeatInfo(roomId, "S" + f1.format(i));
//                }
//                return j;
//            }else if(num > x) {
//                    int j = 0;
//                    for (int i = x + 1; i <= num; i++) {
//                        j = j + seatMapper.insertSeatInfo(roomId, rCode, "S" + f1.format(i));
//                    }
//                    return j;
//                }else{
//                return 0;
//            }
//        }

//    }
}
