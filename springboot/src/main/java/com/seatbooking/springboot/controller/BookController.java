package com.seatbooking.springboot.controller;

import com.seatbooking.springboot.config.Constants;
import com.seatbooking.springboot.config.Result;
import com.seatbooking.springboot.dto.BookDTO;
import com.seatbooking.springboot.exception.ServiceException;
import com.seatbooking.springboot.mapper.BookMapper;
import com.seatbooking.springboot.pojo.vo.BookVo;
import com.seatbooking.springboot.pojo.vo.FrontBookVo;
import com.seatbooking.springboot.pojo.vo.page.BookPageVo;
import com.seatbooking.springboot.pojo.vo.page.FrontBookPageVo;
import com.seatbooking.springboot.service.BookService;
import com.seatbooking.springboot.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.lang.Console.error;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookService bookService;

    //     分页查询接口
    @GetMapping("/page")
    public Result<?> findpage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        BookPageVo pageVo = new BookPageVo();
        pageVo.setPageNum(pageNum);
        pageVo.setPageSize(pageSize);
        pageNum = (pageNum - 1) * pageSize;
        List<BookVo> data = bookMapper.selectPage(pageNum,pageSize);
        Integer total = bookMapper.selectTotal();
        pageVo.setData(data);
        pageVo.setTotal(total);
        return Result.success(pageVo);
    }

    //    按条件搜索
    @GetMapping("/search")
    public Result<?> searchDetailInfo(@RequestParam(required = false) String bookId,
                                                @RequestParam(required = false) String username,
                                                @RequestParam(required = false) String name,
                                                @RequestParam(required = false) Integer bookType,
                                                @RequestParam(required = false) Integer bookStatus,
                                                @RequestParam Integer pageNum,
                                                @RequestParam Integer pageSize){
        BookPageVo pageVo = new BookPageVo();
        pageVo.setPageNum(pageNum);
        pageVo.setPageSize(pageSize);
        pageNum = (pageNum - 1) * pageSize;
        List<BookVo> data = bookMapper.searchList(bookId, username, name, bookType, bookStatus,pageNum,pageSize);
        Integer total = bookMapper.searchTotal(bookId, username, name, bookType, bookStatus);
        pageVo.setData(data);
        pageVo.setTotal(total);
        return Result.success(pageVo);
    }

//    取消预约和编辑预约状态  注：bookStatus传修改后的status
    @GetMapping("/status")
    public Result<?> switchStatus(@RequestParam String bookId,
                                  @RequestParam Integer bookStatus,
                                  @RequestParam(required = false) Integer userId,
                                  @RequestParam(required = false) String reason){
        return Result.success(bookService.auditStatus(bookStatus, bookId, userId, reason));
    }

//    前台预约记录搜索
    @GetMapping("/frontSearch")
    public Result<?> frontSearchBookInfo(@RequestParam(required = false) String bookId,
                                         @RequestParam(required = false) Integer bookType,
                                         @RequestParam(required = false) Integer bookStatus,
                                         @RequestParam Integer userId,
                                         @RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize){
        FrontBookPageVo pageVo = new FrontBookPageVo();
        pageVo.setPageNum(pageNum);
        pageVo.setPageSize(pageSize);
        pageNum = (pageNum - 1) * pageSize;
        List<FrontBookVo> data = bookMapper.searchByClient(bookId, bookType,bookStatus, userId,pageNum,pageSize);
        Integer total = bookMapper.searchByClientTotal(bookId, bookType,bookStatus, userId);
        pageVo.setData(data);
        pageVo.setTotal(total);
        return Result.success(pageVo);
    }

//    前台新增预约记录
    @PostMapping("/insertBook")
    public Result<?> insertBook(@RequestBody BookDTO bookDTO){
        Integer roomId = bookDTO.getRoomId();
        Integer seatId = bookDTO.getSeatId();
        Timestamp startTime = bookDTO.getStartTime();
        Timestamp endTime = bookDTO.getEndTime();
        String bookId = IDUtils.createID();

        if(bookMapper.checkOverlap(roomId,seatId,startTime,endTime) == 1){
            return Result.error("-1","当前时段已被预约");
        }

        try{
            bookDTO.setBookId(bookId);
            return Result.success(bookMapper.insertBookRecord(bookId,
                    bookDTO.getUserId(),
                    bookDTO.getBookType(),
                    roomId,
                    seatId,
                    bookDTO.getBookDate(),
                    startTime,
                    endTime,
                    bookDTO.getBookStatus(),
                    bookDTO.getReviewTime(),
                    bookDTO.getReason(),
                    bookDTO.getTopic(),
                    bookDTO.getAdvisor()));

        }catch(Exception e){
            error(e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
    }

//    前台查询已预约房间
    @GetMapping("bookedRoom")
    public Result<?> searchBookedRoom(@RequestParam Integer bookType,

                                      @DateTimeFormat(pattern = "yyyy-MM-dd")
                                      @RequestParam Date bookDate,

                                      @RequestParam Timestamp startTime,
                                      @RequestParam Timestamp endTime){

        return Result.success(bookMapper.searchBookedRoomId(bookType,bookDate,startTime,endTime));
    }
//    前台查询可预约房间
    @GetMapping("ableBookRoom")
    public Result<?> ableBookRoomList(@RequestParam Integer bookType,
                                      @DateTimeFormat(pattern = "yyyy-MM-dd")
                                      @RequestParam Date bookDate,

                                      @RequestParam Timestamp startTime,
                                      @RequestParam Timestamp endTime,
                                      @RequestParam Integer rType){
        return Result.success(bookService.searchRoomList(bookType,bookDate,startTime,endTime,rType));
    }
//    小程序查询“已预约”状态记录
    @GetMapping("/uniSearch")
    public Result<?> uniFrontBookList(@RequestParam Integer userId){
        return Result.success(bookMapper.searchUniBookList(userId));
    }
}
