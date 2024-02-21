package com.seatbooking.springboot.pojo.vo.page;

import com.seatbooking.springboot.pojo.vo.BreakVo;
import lombok.Data;

import java.util.List;

@Data
public class BreakPageVo {
    private List<BreakVo> data;
    private Integer total;
    private Integer pageNum;
    private Integer pageSize;
}
