package com.brownfield.app.controller;

import com.brownfield.app.model.response.CheckinResponse;
import com.brownfield.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.brownfield.app.entity.Checkin;
import com.brownfield.app.service.PassengerService;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/api/v1/checkin")
public class CheckinController {

    @Autowired
    private PassengerService passengerService;
    @Autowired
    private CheckinService checkinService;

    @PostMapping("/save")
    public ResponseEntity<Checkin> saveCheckin(@RequestBody @Validated Checkin checkin){
        Checkin response=checkinService.saveChecking(checkin);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/{checkinId}")
    public ResponseEntity<CheckinResponse> findByCheckinId(@PathVariable ("checkinId") long checkinId){
        CheckinResponse response = checkinService.findByCheckinId(checkinId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}