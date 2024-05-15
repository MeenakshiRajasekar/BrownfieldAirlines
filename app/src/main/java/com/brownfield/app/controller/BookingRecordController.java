package com.brownfield.app.controller;

import com.brownfield.app.entity.BookingRecord;
import com.brownfield.app.model.request.BookingRequest;
import com.brownfield.app.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookingRecordController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingRecord>> findAllBooking(){
        List<BookingRecord> response = bookingService.findAllBooking();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingRecord>> findAllBookingByUser(@PathVariable("userId") long userId){
        List<BookingRecord> response = bookingService.findAllBookingByUser(userId);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingRecord> findBookingById(@PathVariable("bookingId") long id){
        BookingRecord response = bookingService.findBookingById(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/pnr/{pnr}")
    public ResponseEntity<BookingRecord> findBookingByPnr(@PathVariable("pnr") long pnr){
        BookingRecord response = bookingService.findBookingByPnr(pnr);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<BookingRecord> saveBooking(@RequestBody @Valid BookingRequest bookingRequest){
        BookingRecord response = bookingService.saveBooking(bookingRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/cancel/{bookingId}")
    public ResponseEntity<?> deleteBookingById(@PathVariable("bookingId") long id){
        bookingService.deleteBookingById(id);
        return new ResponseEntity("Entity Deleted", HttpStatus.OK);
    }

}
