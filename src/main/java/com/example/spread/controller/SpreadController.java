package com.example.spread.controller;

import com.example.spread.dao.PickTask;
import com.example.spread.dao.ReqeustTask;
import com.example.spread.dao.ResponseTask;
import com.example.spread.service.SpreadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class SpreadController {
    private final SpreadService spreadService;

    @GetMapping("/spread")
    public ResponseTask getSpreadTask(@RequestHeader() String token) {
        System.out.println(token);
        return spreadService.findByTokenId(token);
    }


//    @GetMapping List<Re>

    @PostMapping("/spread")
    public ResponseEntity<?> create(@RequestHeader("X-ROOM-ID") String roomId,
            @RequestHeader("X-USER-ID") long userId,
            @RequestBody ReqeustTask reqeustTask)
    {
        return new ResponseEntity(spreadService.saveTask(roomId, userId, reqeustTask.getAmount(), reqeustTask.getPplCnt() ), HttpStatus.OK);
    }

    @PostMapping("/pick")
    public ResponseEntity<?> pickMoney(@RequestHeader("X-USER-ID") long userId,
                                       @RequestBody PickTask pickTask)
    {
        //return type, ErrorCase:0~4, SuccessCase:5~10
        //0: The token is invalid.
        //1: Created User and Request User is same.
        //2: The user already got the money.
        //3: The time is passed 10 min.
        //5: Picked the Money.
        String message = "";

        ResponseEntity responseEntity = null;

        switch (spreadService.pickMoney(userId, pickTask.getToken())){
            case 0: responseEntity = new ResponseEntity("The token is invalid.", HttpStatus.EXPECTATION_FAILED);
                break;
            case 1: responseEntity = new ResponseEntity("The Users is created this jobs.", HttpStatus.EXPECTATION_FAILED);
                break;
            case 2: responseEntity = new ResponseEntity("The user already got the money.", HttpStatus.EXPECTATION_FAILED);
                break;
            case 3: responseEntity = new ResponseEntity("The time is passed 10 min.", HttpStatus.EXPECTATION_FAILED);
                break;
            case 4: responseEntity = new ResponseEntity("There is no empty room.", HttpStatus.EXPECTATION_FAILED);
                break;
            case 5: responseEntity = new ResponseEntity("Picked the Money.", HttpStatus.OK);
                break;

            default:
                break;
            }
            return responseEntity;
    }


}
