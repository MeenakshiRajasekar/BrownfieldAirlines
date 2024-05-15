package com.brownfield.app.service;

import com.brownfield.app.entity.Passenger;

public interface PassengerService {
    Passenger savePassenger(Passenger passenger);
    Passenger findPassengerById(long id);
}
