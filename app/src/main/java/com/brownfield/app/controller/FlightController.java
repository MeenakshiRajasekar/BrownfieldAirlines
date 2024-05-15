package com.brownfield.app.controller;

import com.brownfield.app.entity.BookingRecord;
import com.brownfield.app.entity.Flight;
import com.brownfield.app.model.request.FlightSearchRequest;
import com.brownfield.app.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/v1/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public ResponseEntity<List<BookingRecord>> findAllFlight(){
        List<Flight> response = flightService.findAllFlight();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{flightId}")
    @ResponseBody
    public ResponseEntity<Flight> findFlightById(@PathVariable("flightId") long id){

        Flight response = flightService.findFlightById(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/airline/{airlineName}")
    @ResponseBody
    public ResponseEntity<List<Flight>> findFlightByAirline(@PathVariable("airlineName") String  airlineName){

        List<Flight> response = flightService.findFlightByAirline(airlineName);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/info/{flightNumber}")
    @ResponseBody
    public ResponseEntity<List<Flight>> findFlightByFlightNumber(@PathVariable("flightNumber") String  flightNumber){
        List<Flight> response = flightService.findFlightByFlightNumber(flightNumber);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<Flight> saveFlight(@RequestBody @Valid Flight flight){
        Flight response = flightService.saveFlight(flight);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PostMapping("/find")
    @ResponseBody
    public ResponseEntity<List<Flight>> findFlightByOriginDestinationDate(@RequestBody @Valid FlightSearchRequest request){
        List<Flight> response = flightService.findByOriginDestinationDateService(request);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @DeleteMapping("/cancel/{flightId}")
    public ResponseEntity<String> deleteFlightById(@PathVariable("flightId") long id){
        flightService.deleteFlightById(id);
        return new ResponseEntity("Entity Deleted", HttpStatus.OK);
    }
}
