package com.brownfield.app.service;

import com.brownfield.app.entity.Flight;
import com.brownfield.app.model.request.FlightSearchRequest;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    Flight saveFlight(Flight flight);
    Flight updateFlight(Flight flight);
    Flight findFlightById(long id);
    List<Flight> findFlightByAirline(String airlineName);
    List<Flight> findFlightByFlightNumber(String flightNumber);
    List<Flight> findByOriginDestinationDateService(FlightSearchRequest request);
    void deleteFlightById(long id);
    List<Flight> saveAllFlight(List<Flight> flights);
    List<Flight> findAllFlight();

}
