package com.seatbooking.springboot.service;

import com.seatbooking.springboot.mapper.RoomMapper;
import com.seatbooking.springboot.mapper.SeatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private SeatMapper seatMapper;

    public Integer insertOrUpdateMeet(String rCode, String district, String roomName, String floor, Integer roomId,Boolean isResearch, String roomDepart) {
        if (roomId == null) {  // 新增
            return roomMapper.insertMeetInfo(rCode, roomName, district, floor,isResearch,roomDepart);
        } else {  // 更新
            return roomMapper.updateMeetInfo(rCode, roomName, district, floor, roomId,isResearch,roomDepart);
        }
    }

    public List<Integer> insertOrUpdateStudy(String rCode, Integer num, String roomName, String district, String floor, String rUrl, Integer roomId) {
        if (roomId == null) {  // 新增
            int j = 0;
            List<Integer> list = new ArrayList<>();
            list.add(roomMapper.insertStudyInfo(rCode, num, roomName, district, floor, rUrl));
            roomId = roomMapper.searchRoomId(rCode, num);
            Format f1 = new DecimalFormat("000");
            for (int i = 1; i <= num; i++) {
                j = j + seatMapper.insertSeatInfo(roomId, rCode, "S" + f1.format(i));
            }
            list.add(j);
            return list;
        } else {  // 更新
            int x = 0;
            x = seatMapper.selectSeatNum(roomId);
            List<Integer> list = new ArrayList<>();
            Format f1 = new DecimalFormat("000");
            list.add(roomMapper.updateStudyInfo(rCode, num, roomName, district, floor, rUrl, roomId));
            if (num < x) {
                int j = 0;
                for (int i = num + 1; i <= x; i++) {
                    j = j + seatMapper.deleteSeatInfo(roomId, "S" + f1.format(i));
                }
                list.add(j);
                return list;
            } else if (num > x) {
                int j = 0;
                for (int i = x + 1; i <= num; i++) {
                    j = j + seatMapper.insertSeatInfo(roomId, rCode, "S" + f1.format(i));
                }
                list.add(j);
                return list;
            } else {
                return list;
            }
        }
    }

    public Integer insertOrUpdateClass(String rCode, Integer num, String roomName, String floor, Integer roomId) {
        if (roomId == null) {  // 新增
            return roomMapper.insertClassInfo(rCode, num, roomName, floor);
        } else {  // 更新
            return roomMapper.updateClassInfo(rCode, num, roomName, floor, roomId);
        }
    }
}
