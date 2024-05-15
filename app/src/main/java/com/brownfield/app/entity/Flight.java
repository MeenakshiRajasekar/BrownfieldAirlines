package com.brownfield.app.entity;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "flight")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @NotBlank(message = "flight origin cannot be empty")
    private String origin;
    @NotBlank(message = "flight destination cannot be empty")
    private String destination;
    @NotNull(message = "invalid flight date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate flightDate;
    @NotNull(message = "invalid departure time")
    @DateTimeFormat(pattern="HH:mm:ss")
    private LocalTime departureTime;
    @DateTimeFormat(pattern="HH:mm:ss")
    private LocalTime arrivalTime;
    @NotNull(message = "invalid flight duration")
    @DateTimeFormat(pattern="HH:mm:ss")
    private LocalTime flightDuration;
    @NotNull(message = "invalid flight info")
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flightInfoId")
    private FlightInfo flightInfo;
    @NotNull(message = "invalid fare")
    @Valid
    @JoinColumn(name = "fareId")
    @OneToOne(cascade = CascadeType.ALL)
    private Fare fare;
    @NotNull(message = "invalid inventory")
    @Valid
    @JoinColumn(name = "inventoryId")
    @OneToOne(cascade = CascadeType.ALL)
    private Inventory inventory;
}
