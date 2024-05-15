package com.brownfield.app.repository;

import com.brownfield.app.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    List<Flight> findByOriginAndDestinationAndFlightDateOrderByDepartureTimeAsc(String origin, String destination, LocalDate date);
    List<Flight> findByFlightInfoAirlineInfoNameOfAirlineOrderByDepartureTimeAsc(String airlineName);
    List<Flight> findByFlightInfoFlightNumberOrderByDepartureTimeAsc(String flightNumber);
    Optional<Flight> findByFlightDateAndDepartureTimeAndFlightInfoFlightNumber(LocalDate flightDate, LocalTime departureTime, String flightNumber);
}
