package com.brownfield.app.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlightSearchRequest {
    @NotBlank(message = "origin cannot be empty")
    private String origin;
    @NotBlank(message = "destination cannot be empty")
    private String destination;
    @NotNull(message = "travel date cannot be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotNull(message = "number of passengers cannot be empty")
    @Min(value = 1,message = "number of passengers cannot be less than 0")
    @Max(value = 10,message = "number of passengers cannot be greater than 10")
    private Integer Passengers;
}
