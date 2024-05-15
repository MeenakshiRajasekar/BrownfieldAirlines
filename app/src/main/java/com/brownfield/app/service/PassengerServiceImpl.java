package com.brownfield.app.service;


import com.brownfield.app.entity.Passenger;
import com.brownfield.app.exception.RecordNotFoundException;
import com.brownfield.app.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    Logger logger = LoggerFactory.getLogger(PassengerServiceImpl.class);

    @Override
    public Passenger savePassenger(Passenger passenger) {
        logger.info("savePassenger method invoked");
        return passengerRepository.save(passenger);
    }

    @Override
    public Passenger findPassengerById(long id) {
        Optional<Passenger> passenger = passengerRepository.findById(id);
        if(passenger.isEmpty()){
            throw new RecordNotFoundException("No record found for passenger : "+id);
        }
        logger.info("findPassengerById method invoked");
        return passenger.get();
    }

}
