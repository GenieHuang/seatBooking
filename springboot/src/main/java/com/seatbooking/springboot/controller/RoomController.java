package com.seatbooking.springboot.controller;

import com.seatbooking.springboot.config.Result;
import com.seatbooking.springboot.entity.Room;
import com.seatbooking.springboot.mapper.RoomMapper;
import com.seatbooking.springboot.pojo.vo.page.RoomPageVo;
import com.seatbooking.springboot.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private RoomService roomService;

//    分页查询接口（会议室/自习室/教室）
    @GetMapping("/page")
    public Result<?> findpage(@RequestParam Integer pageNum,
                              @RequestParam Integer pageSize,
                              @RequestParam Integer rType){
        RoomPageVo pageVo = new RoomPageVo();
        pageVo.setPageNum(pageNum);
        pageVo.setPageSize(pageSize);
        pageNum = (pageNum - 1) * pageSize;
        List<Room> data = roomMapper.selectPage(rType,pageNum,pageSize);
        Integer total = roomMapper.selectTotal(rType);
        pageVo.setData(data);
        pageVo.setTotal(total);
        return Result.success(pageVo);
    }

//    按条件搜索
    @GetMapping("/search")
    public Result<?> searchDetailInfo(@RequestParam(required=false) String rCode,
                                                @RequestParam(required=false) Integer rStatus,
                                                @RequestParam Integer rType,
                                                @RequestParam Integer pageNum,
                                                @RequestParam Integer pageSize){
        RoomPageVo pageVo = new RoomPageVo();
        pageVo.setPageNum(pageNum);
        pageVo.setPageSize(pageSize);
        pageNum = (pageNum - 1) * pageSize;
        List<Room> data = roomMapper.searchList(rCode, rStatus,rType,pageNum,pageSize);
        Integer total = roomMapper.searchTotal(rCode, rStatus,rType);
        pageVo.setData(data);
        pageVo.setTotal(total);
        return Result.success(pageVo);
    }

//    新增或更新会议室
    @GetMapping("/auditMeet")
    public Result<?> auditMeet(@RequestParam(required = false) String rCode,
                               @RequestParam(required = false) String roomName,
                             @RequestParam(required = false) String district,
                             @RequestParam(required = false) String floor,
                             @RequestParam(required = false) Integer roomId,
                               @RequestParam(required = false) Boolean isResearch,
                               @RequestParam(required = false) String roomDepart){
        return Result.success(roomService.insertOrUpdateMeet(rCode,roomName,district,floor,roomId,isResearch,roomDepart));
    }

//    新增或更新自习室
    @GetMapping("/auditStudy")
    public Result<?> auditStudy(@RequestParam(required = false) String rCode,
                                @RequestParam(required = false) Integer num,
                                @RequestParam(required = false) String roomName,
                                @RequestParam(required = false) String district,
                                @RequestParam(required = false) String floor,
                                @RequestParam(required = false) String rUrl,
                                @RequestParam(required = false) Integer roomId) {
        return Result.success(roomService.insertOrUpdateStudy(rCode,num,roomName,district,floor,rUrl,roomId));
    }

//    新增或更新教室
    @GetMapping("/auditClassroom")
    public Result<?> auditClass(@RequestParam(required = false) String rCode,
                                    @RequestParam(required = false) Integer num,
                                    @RequestParam(required = false) String roomName,
                                    @RequestParam(required = false) String floor,
                                    @RequestParam(required = false) Integer roomId){
        return Result.success(roomService.insertOrUpdateClass(rCode, num, roomName, floor, roomId));
    }
//    禁用/启用会议室/自习室
    @GetMapping("/status")
    public Result<?> switchStatus(@RequestParam Integer roomId){return Result.success(roomMapper.updateStatus(roomId));}


}
