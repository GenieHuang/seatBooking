package com.seatbooking.springboot.pojo.vo.page;

import com.seatbooking.springboot.pojo.vo.FrontBookVo;
import lombok.Data;

import java.util.List;

@Data
public class FrontBookPageVo {

    private List<FrontBookVo> data;
    private Integer total;
    private Integer pageNum;
    private Integer pageSize;
}
