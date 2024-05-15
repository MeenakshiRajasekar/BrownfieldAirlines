package com.brownfield.app.repository;

import com.brownfield.app.entity.Fare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FareRepository extends JpaRepository<Fare,Long> {
    Fare findByFlightId(long flightId);
}
