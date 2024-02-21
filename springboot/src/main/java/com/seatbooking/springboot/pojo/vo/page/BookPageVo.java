package com.seatbooking.springboot.pojo.vo.page;

import com.seatbooking.springboot.pojo.vo.BookVo;
import lombok.Data;

import java.util.List;

@Data
public class BookPageVo {

    private List<BookVo> data;
    private Integer total;
    private Integer pageNum;
    private Integer pageSize;
}
