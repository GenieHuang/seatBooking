package com.seatbooking.springboot.pojo.vo;

import lombok.Data;

@Data
public class BookedListVo {
    private Integer roomId;
    private String rCode;
    private String roomName;
    private String roomDepart;
    private Integer seatId;
    private String sCode;
    private Integer num;
    private String rUrl;
    private Boolean isResearch;
}
