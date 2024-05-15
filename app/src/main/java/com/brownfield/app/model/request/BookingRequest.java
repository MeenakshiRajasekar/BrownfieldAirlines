package com.brownfield.app.model.request;

import com.brownfield.app.entity.Passenger;
import com.brownfield.app.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingRequest {

    @Min(value = 1,message = "invalid flight id")
    private long flightId;
    @Min(value = 1,message = "invalid user id")
    private long userId;
    @NotEmpty(message = "passengers cannot be empty")
    @Valid
    List<Passenger> passengers;
    @NotNull(message = "invalid payment")
    @Valid
    private Payment payment;
}
