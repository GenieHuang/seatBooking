package com.seatbooking.springboot.controller;

import com.seatbooking.springboot.config.Result;
import com.seatbooking.springboot.entity.Board;
import com.seatbooking.springboot.mapper.BoardMapper;
import com.seatbooking.springboot.pojo.vo.page.BoardPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardMapper boardMapper;

//    新增公告信息

    @PostMapping("/insert")
    public Result<?> insertBoard(@RequestBody Board board){
        String str = board.getBUrl();
        return Result.success(boardMapper.insert(board.getTitle(),board.getMsg(), board.getBUrl()));
    }

//    公告列表分页查询
    @PostMapping("/search")
    public Result<?> selectBoardInfo(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize){
        BoardPageVo pageVo = new BoardPageVo();
        pageVo.setPageNum(pageNum);
        pageVo.setPageSize(pageSize);
        pageNum = (pageNum - 1) * pageSize;
        List<Board> data = boardMapper.searchList(pageNum,pageSize);
        Integer total = boardMapper.searchTotal();
        pageVo.setData(data);
        pageVo.setTotal(total);
        return Result.success(pageVo);
    }

//    查询最新一条公告信息
    @GetMapping("/newMsg")
    public Result<?> selectNewMsg(){
        return Result.success(boardMapper.selectBoard());
    }
}
