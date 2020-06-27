package com.example.spread.controller;

import com.example.spread.dao.RequestReceiveDto;
import com.example.spread.dao.RequestDto;
import com.example.spread.dao.ResponseDto;
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
    public ResponseDto getSpreadTask(@RequestHeader("TOKEN") String token,
                                     @RequestHeader("X-USER-ID") long userId) {
        return spreadService.findByTokenId(token, userId);
    }

    @PostMapping("/spread")
    public ResponseEntity<?> create(@RequestHeader("X-ROOM-ID") String roomId,
                                    @RequestHeader("X-USER-ID") long userId,
                                    @RequestBody RequestDto requestDto) {
        return new ResponseEntity(spreadService.saveTask(roomId, userId, requestDto.getAmount(), requestDto.getPplCnt() ), HttpStatus.OK);
    }

    @PostMapping("/receive")
    public ResponseEntity<?> pickMoney(@RequestHeader("X-USER-ID") long userId,
                                       @RequestBody RequestReceiveDto requestReceiveDto)
    {
        //return type, ErrorCase:0~4, SuccessCase:5~10
        //0: The token is invalid.
        //1: Created User and Request User is same.
        //2: The user already got the money.
        //3: The time is passed 10 min.
        //?: Picked the Money.
        String message = "";

        ResponseEntity responseEntity = null;

        int receivedData = spreadService.pickMoney(userId, requestReceiveDto.getToken());
        switch (receivedData){
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
              default:
                responseEntity = new ResponseEntity("Picked: " + receivedData +".", HttpStatus.OK);
                break;
            }
            return responseEntity;
    }
}
