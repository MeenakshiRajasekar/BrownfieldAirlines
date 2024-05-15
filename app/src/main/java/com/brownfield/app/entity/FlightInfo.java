package com.brownfield.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "flightInfo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlightInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long flightInfoId;
    @NotBlank(message = "flightnumber cannot be empty")
    private String flightNumber;
    @NotBlank(message = "flighttype cannot be empty")
    private String flightType;
    @NotNull(message = "invalid number of seats")
    @Min(value = 10,message = "invalid number of seats")
    private Integer numberOfSeats;
    @NotNull(message = "airline info cannot be empty")
    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "flightAirlineInfo", joinColumns = {
            @JoinColumn(name = "flightInfoId", referencedColumnName = "flightInfoId") }, inverseJoinColumns = {
            @JoinColumn(name = "airlineId", referencedColumnName = "airlineId") })
    private AirlineInfo airlineInfo;

}
