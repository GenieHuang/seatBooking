package com.seatbooking.springboot.pojo.vo.page;

import com.seatbooking.springboot.entity.Board;
import lombok.Data;

import java.util.List;

@Data
public class BoardPageVo {
    private List<Board> data;
    private Integer total;
    private Integer pageNum;
    private Integer pageSize;
}
