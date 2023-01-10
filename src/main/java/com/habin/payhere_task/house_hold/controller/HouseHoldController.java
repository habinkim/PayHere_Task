package com.habin.payhere_task.house_hold.controller;

import com.habin.payhere_task.house_hold.service.HouseHoldService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/houseHold")
public class HouseHoldController {

    private final HouseHoldService houseHoldService;

}
