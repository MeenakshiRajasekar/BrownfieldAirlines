package com.brownfield.app.service;

import com.brownfield.app.entity.Checkin;
import com.brownfield.app.entity.Passenger;
import com.brownfield.app.exception.RecordNotFoundException;
import com.brownfield.app.model.response.CheckinResponse;
import com.brownfield.app.repository.CheckinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CheckinServiceImpl implements CheckinService{

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private CheckinRepository checkinRepository;

    Logger logger = LoggerFactory.getLogger(CheckinServiceImpl.class);

    @Override
    public Checkin saveChecking(Checkin checkin) {
        Passenger passenger=passengerService.findPassengerById(checkin.getPassenger().getPassengerId());
        checkin.setDateTime(LocalDateTime.now());
        checkin.setGateNumber((int) (Math.random() * 12-1) + 1);
        checkin.setPassenger(passenger);
        Checkin checkin_db =  checkinRepository.save(checkin);
        passenger.setCheckin(checkin_db);
        passenger.setChecked_in(true);
        passenger.setSeatNumber((long) (Math.random() * 300-1) + 1);
        passengerService.savePassenger(passenger);
        logger.info("saveChecking method invoked");
        return checkin_db;
    }

    @Override
    public CheckinResponse findByCheckinId(long checkinId) {
        Optional<Checkin> checkin = checkinRepository.findById(checkinId);
        if(checkin.isEmpty())
            throw new RecordNotFoundException("No Checkin record found "+checkinId);
        CheckinResponse response = new CheckinResponse();
        response.setCheckin(checkin.get());
        response.setOrigin(checkin.get().getPassenger().getBookingRecord().getOrigin());
        response.setDestination(checkin.get().getPassenger().getBookingRecord().getDestination());
        response.setFlightNumber(checkin.get().getPassenger().getBookingRecord().getFlightNumber());
        response.setFlightDate(checkin.get().getPassenger().getBookingRecord().getFlightDate());
        response.setDepartureTime(checkin.get().getPassenger().getBookingRecord().getDepartureTime());
        logger.info("findByCheckinId method invoked");
        return response;
    }
}
