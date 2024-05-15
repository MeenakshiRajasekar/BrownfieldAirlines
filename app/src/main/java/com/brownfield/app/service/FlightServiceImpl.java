package com.brownfield.app.service;

import com.brownfield.app.entity.Flight;
import com.brownfield.app.exception.BadRequestException;
import com.brownfield.app.exception.RecordNotFoundException;
import com.brownfield.app.model.request.FlightSearchRequest;
import com.brownfield.app.repository.FlightInfoRepository;
import com.brownfield.app.repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class FlightServiceImpl implements FlightService{

    @Autowired
    private FlightRepository flightRepository;
    Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);
    @Autowired
    private FlightInfoRepository flightInfoRepository;

    @Override
    public Flight saveFlight(Flight flight) {
        flight.setOrigin(StringUtils.trimAllWhitespace(flight.getOrigin().toLowerCase()));
        flight.setDestination(StringUtils.trimAllWhitespace(flight.getDestination().toLowerCase()));
        flight.getFlightInfo().setFlightNumber(flight.getFlightInfo().getFlightNumber().toUpperCase());
        if(flight.getOrigin().equals(flight.getDestination())){
            throw new BadRequestException("invalid origin or destination");
        }
        if(flight.getFlightDate().isBefore(LocalDate.now())){
            throw new BadRequestException("invalid flight date");
        }
        Optional<Flight> flight_db = flightRepository.findByFlightDateAndDepartureTimeAndFlightInfoFlightNumber(flight.getFlightDate()
                ,flight.getDepartureTime(),flight.getFlightInfo().getFlightNumber());
        if(flight_db.isPresent()){
            throw new BadRequestException("flight already exists");
        }
        LocalTime arrivalTime = flight.getDepartureTime().plusHours(flight.getFlightDuration().getHour()
        ).plusMinutes(flight.getFlightDuration().getMinute()).plusSeconds(flight.getFlightDuration().getSecond());
        flight.setArrivalTime(arrivalTime);
        Flight ft=flightRepository.save(flight);
        logger.info("Flight saved with Id:"+flight.getId());
        return ft;
    }

    @Override
    public Flight updateFlight(Flight flight) {
        logger.info(flight.getId()+" Id Flight updated");
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> saveAllFlight(List<Flight> flights) {
        logger.info("All Flight has been saved");
        return flightRepository.saveAll(flights);
    }

    @Override
    public Flight findFlightById(long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        if(flight.isEmpty()){
            throw new RecordNotFoundException("Record not found for flight : "+id);
        }
        logger.info("findFlightById method invoked");
        return flight.get();
    }

    @Override
    public List<Flight> findFlightByAirline(String airlineName) {
        logger.info("findFlightByAirline invoked");
                return flightRepository.findByFlightInfoAirlineInfoNameOfAirlineOrderByDepartureTimeAsc(airlineName);
    }

    @Override
    public List<Flight> findFlightByFlightNumber(String flightNumber) {
        logger.info("findFlightByFlightNumber method invoked");
        return flightRepository.findByFlightInfoFlightNumberOrderByDepartureTimeAsc(flightNumber);
    }

    @Override
    public List<Flight> findAllFlight() {
        logger.info("findAllFlight method invoked");
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> findByOriginDestinationDateService(FlightSearchRequest request) {
        if(request.getDate().isBefore(LocalDate.now())){
            throw new BadRequestException("invalid flight date");
        }
        request.setOrigin(StringUtils.trimAllWhitespace(request.getOrigin().toLowerCase()));
        request.setDestination(StringUtils.trimAllWhitespace(request.getDestination().toLowerCase()));
        if(request.getOrigin().equals(request.getDestination())){
            throw new BadRequestException("origin and destination cannot be same");
        }
        List<Flight> flights = flightRepository.findByOriginAndDestinationAndFlightDateOrderByDepartureTimeAsc(request.getOrigin(), request.getDestination(), request.getDate());

        List<Flight> searchResults = flights.stream().filter(flight -> {
            return flight.getInventory().getCount()>=request.getPassengers()&&LocalDateTime.now().plusHours(2).isBefore(LocalDateTime.of(flight.getFlightDate(),flight.getDepartureTime()));
        }).collect(Collectors.toList());
        logger.info("findByOriginDestinationDateService method invoked");
        return searchResults;

    }

    @Override
    public void deleteFlightById(long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        if(flight.isEmpty()) {
            throw new RecordNotFoundException("Record not found for flight : " + id);
        }
        flightRepository.deleteById(id);
        logger.info("deleteFlightById method invoked");

    }

}
