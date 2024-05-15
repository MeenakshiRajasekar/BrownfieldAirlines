package com.brownfield.app.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="airlineInfo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AirlineInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long airlineId;
    @NotBlank(message = "airline logo cannot be empty")
    private String airlineLogo;
    @NotBlank(message = "airline name cannot be empty")
    private String nameOfAirline;

}
