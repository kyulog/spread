package com.example.spread.controller;

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

//    @GetMapping("/test")
//    public SpreadEntity spreadTest(){
//        return new SpreadEntity(1, 10, 5, false, "ss");
//    }

    @GetMapping
    public List<SpreadEntity> getAll() { return spreadService.findAll();}

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SpreadDto spreadDto)
    {
//        spreadService.saveTask(spreadDto);
        return new ResponseEntity(spreadService.saveTask(spreadDto), HttpStatus.OK);
    }

}
