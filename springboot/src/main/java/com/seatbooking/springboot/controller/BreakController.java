package com.seatbooking.springboot.controller;

import com.seatbooking.springboot.config.Result;
import com.seatbooking.springboot.mapper.BreakMapper;
import com.seatbooking.springboot.pojo.vo.BreakVo;
import com.seatbooking.springboot.pojo.vo.UserVo;
import com.seatbooking.springboot.pojo.vo.page.BreakPageVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/break")
public class BreakController {

    @Autowired
    private BreakMapper breakMapper;

//    分页查询接口
    @GetMapping("/page")
    public Result<?> findpage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        BreakPageVo pageVo = new BreakPageVo();
        pageVo.setPageNum(pageNum);
        pageVo.setPageSize(pageSize);
        pageNum = (pageNum - 1) * pageSize;
        List<BreakVo> data = breakMapper.selectPage(pageNum,pageSize);
        Integer total = breakMapper.selectTotal();
        pageVo.setData(data);
        pageVo.setTotal(total);
        return Result.success(pageVo);
    }

//    按条件搜索
    @GetMapping("/search")
    public Result<?> searchDetailInfo(@RequestParam(required=false) String bookId,
                                                @RequestParam(required=false) String username,
                                                @RequestParam(required=false) String name,
                                                @RequestParam Integer pageNum,
                                                @RequestParam Integer pageSize){
        BreakPageVo pageVo = new BreakPageVo();
        pageVo.setPageNum(pageNum);
        pageVo.setPageSize(pageSize);
        pageNum = (pageNum - 1) * pageSize;
        List<BreakVo> data = breakMapper.searchList(bookId, username, name, pageNum,pageSize);
        Integer total = breakMapper.searchTotal(bookId, username, name);
        pageVo.setData(data);
        pageVo.setTotal(total);
        return Result.success(pageVo);
    }

//    更改违约记录状态t_break.break_status
    @GetMapping("/status")
    public Result<?> switchStatus(@RequestParam Integer breakId){
        return Result.success(breakMapper.updateStatus(breakId));
    }
}


