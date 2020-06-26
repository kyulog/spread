package com.example.spread.controller;

import com.example.spread.dao.ReqeustTask;
import com.example.spread.dao.SpreadDto;
import com.example.spread.domain.entity.SpreadEntity;
import com.example.spread.service.SpreadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/spread")
public class SpreadController {
    private final SpreadService spreadService;

    @GetMapping
    public List<SpreadEntity> getAll() { return spreadService.findAll();}

//    @GetMapping List<Re>

    @PostMapping
    public ResponseEntity<?> create(@RequestHeader("X-ROOM-ID") String roomId,
            @RequestHeader("X-USER-ID") long userId,
            @RequestBody ReqeustTask reqeustTask)
    {
        System.out.println("%s" + roomId);
        return new ResponseEntity(spreadService.saveTask(roomId, userId, reqeustTask.getAmount(), reqeustTask.getPplCnt() ), HttpStatus.OK);
    }

}
