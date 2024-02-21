package com.seatbooking.springboot.controller;

import com.seatbooking.springboot.service.SeatService_noUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seat")
public class SeatController_noUse {

    @Autowired
    private SeatService_noUse seatService;

//    @PostMapping("/insertOrUpdate")
//    Integer auditSeat(@RequestParam Integer num,
//                      @RequestParam String rCode,
//                      @RequestParam(required = false) Integer roomId){ return seatService.insertOrUpdateSeat(num, rCode,roomId);}
}
