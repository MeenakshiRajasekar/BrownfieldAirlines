package com.brownfield.app.repository;

import com.brownfield.app.entity.BookingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingRecord,Long> {

    List<BookingRecord>  findByUserId(Long userid);

    Optional<BookingRecord> findByPnrNumber(Long pnrNumber);

}

