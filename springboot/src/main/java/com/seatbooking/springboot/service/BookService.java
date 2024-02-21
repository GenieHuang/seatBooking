package com.seatbooking.springboot.service;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.seatbooking.springboot.mapper.BookMapper;
import com.seatbooking.springboot.mapper.BreakMapper;
import com.seatbooking.springboot.mapper.RoomMapper;
import com.seatbooking.springboot.pojo.vo.BookedListVo;
import com.seatbooking.springboot.pojo.vo.FrontBookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BreakMapper breakMapper;

    @Autowired
    private RoomMapper roomMapper;

    public List<Integer> auditStatus(Integer bookStatus, String bookId, Integer userId, String reason){
        if (bookStatus == 4){
//            修改状态为“已违约”
            List<Integer> list = new ArrayList<>();
            list.add(bookMapper.updateStatus(bookId, bookStatus));
            list.add(breakMapper.insert(bookId, userId, reason));
            return list;
        }else{
//            修改为其他状态
                List<Integer> list = new ArrayList<>();
                list.add(bookMapper.updateStatus(bookId, bookStatus));
                return list;
        }
    }

//    查询可预约房间
    public List<BookedListVo> searchRoomList(Integer bookType, Date bookDate, Timestamp startTime, Timestamp endTime, Integer rType){
        List<BookedListVo> list = roomMapper.searchAllRoomId(rType);
        List<BookedListVo> list2 = bookMapper.searchBookedRoomId(bookType,bookDate,startTime,endTime);
        list.removeAll(list2);
        return list;
    }
//    public List<FrontBookVo> searchBookByClient(){
//
//    }
}
