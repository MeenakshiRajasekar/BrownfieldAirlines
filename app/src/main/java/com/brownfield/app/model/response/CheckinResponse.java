package com.brownfield.app.model.response;

import com.brownfield.app.entity.Checkin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CheckinResponse {
    private Checkin checkin;
    private String origin;
    private String destination;
    private String flightNumber;
    private LocalDate flightDate;
    private LocalTime departureTime;
}
