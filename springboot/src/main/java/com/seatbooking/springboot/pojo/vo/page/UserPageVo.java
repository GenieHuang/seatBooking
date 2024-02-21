package com.seatbooking.springboot.pojo.vo.page;

import com.seatbooking.springboot.pojo.vo.UserVo;
import lombok.Data;

import java.util.List;

@Data
public class UserPageVo {

    private List<UserVo> data;
    private Integer total;
    private Integer pageNum;
    private Integer pageSize;
}
