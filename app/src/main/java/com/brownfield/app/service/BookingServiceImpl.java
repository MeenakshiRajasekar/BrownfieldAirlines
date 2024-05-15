package com.brownfield.app.service;

import com.brownfield.app.entity.*;
import com.brownfield.app.exception.BadRequestException;
import com.brownfield.app.exception.RecordNotFoundException;
import com.brownfield.app.repository.BookingRepository;
import com.brownfield.app.model.request.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class BookingServiceImpl implements BookingService{
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private FlightService flightService;
    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Override
    @Transactional
    public BookingRecord saveBooking(BookingRequest bookingRequest) {
        Flight flight = flightService.findFlightById(bookingRequest.getFlightId());
        if(flight.getInventory().getCount()<bookingRequest.getPassengers().size()){
            throw new BadRequestException("invalid number of passengers");
        }
        BookingRecord bookingRecord = new BookingRecord();
        User user = userService.findById(bookingRequest.getUserId());
        bookingRecord.setUser(user);
        long pnr = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        bookingRecord.setFlightId(bookingRequest.getFlightId());
        bookingRecord.setFlightNumber(flight.getFlightInfo().getFlightNumber());
        bookingRecord.setPnrNumber(pnr);
        bookingRecord.setPassengers(bookingRequest.getPassengers());
        bookingRecord.setOrigin(flight.getOrigin());
        bookingRecord.setDestination(flight.getDestination());
        bookingRecord.setFlightDate(flight.getFlightDate());
        bookingRecord.setDepartureTime(flight.getDepartureTime());
        bookingRecord.setArrivalTime(flight.getArrivalTime());
        bookingRecord.setFare(flight.getFare().getFare()*bookingRequest.getPassengers().size());
        bookingRecord.setBookingDate(LocalDateTime.now());
        bookingRecord.setPayment(bookingRequest.getPayment());
        BookingRecord bookingRecordDb = bookingRepository.save(bookingRecord);
        for(Passenger passenger : bookingRecordDb.getPassengers()){
            passenger.setBookingRecord(bookingRecord);
            passenger.setChecked_in(false);
            passengerService.savePassenger(passenger);
        }
        flight.getInventory().setCount(flight.getInventory().getCount()-bookingRecord.getPassengers().size());
        flightService.updateFlight(flight);
        logger.info("saveBooking method invoked");
        return bookingRecordDb;

    }

    @Override
    public BookingRecord findBookingById(long id) {
        Optional<BookingRecord> bookingRecord = bookingRepository.findById(id);
        if(bookingRecord.isEmpty()){
            throw new RecordNotFoundException("Record not found for booking : "+id);
        }
        logger.info("findBookingById method invoked");
        return bookingRecord.get();
    }

    @Override
    public BookingRecord findBookingByPnr(long pnr) {
        Optional<BookingRecord> bookingRecord = bookingRepository.findByPnrNumber(pnr);
        if(bookingRecord.isEmpty()){
            throw new RecordNotFoundException("Record not found for pnr : "+pnr);
        }
        logger.info("findBookingByPnr method invoked");
        return bookingRecord.get();
    }

    @Override
    public List<BookingRecord> findAllBookingByUser(long userId) {
        User user = userService.findById(userId);
        List<BookingRecord> bookingRecords = bookingRepository.findByUserId(user.getId());
        bookingRecords.sort(Comparator.comparing(BookingRecord::getFlightDate));
        logger.info("findAllBookingByUser method invoked");
        return bookingRecords;
    }

    @Override
    public List<BookingRecord> findAllBooking() {
        return bookingRepository.findAll();
    }

    @Override
    public void deleteBookingById(long id) {
        Optional<BookingRecord> bookingRecord = bookingRepository.findById(id);
        if(bookingRecord.isEmpty()){
            throw new RecordNotFoundException("Record not found for booking : "+id);
        }
        Flight flight = flightService.findFlightById(bookingRecord.get().getFlightId());
        bookingRepository.deleteById(id);
        flight.getInventory().setCount(flight.getInventory().getCount()+bookingRecord.get().getPassengers().size());
        flightService.updateFlight(flight);
        logger.info("deleteBookingById method invoked");
    }
}
