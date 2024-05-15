package com.brownfield.app.service;

import com.brownfield.app.entity.Fare;

public interface FareService {
    Fare saveFare(Fare request);
    Fare updateFare(Fare fare);
    Fare findFareByID(long id);
    Fare findFareByFlightId(long flightId);
}
