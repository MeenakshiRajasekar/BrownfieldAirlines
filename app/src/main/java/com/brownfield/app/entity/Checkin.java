package com.brownfield.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "checkin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Checkin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long checkinId;
    @NotNull(message = "Baggage Checking Status is mandatory")
    @Column(name = "Bag_Check")
    private boolean baggage_checking_status;
    @NotNull(message = "Security Checking Status  is mandatory")
    @Column(name = "Security_Check")
    private boolean security_checking_status;
    @NotNull(message = "Immigration Status is mandatory")
    @Column(name = "Immigration_Check")
    private boolean immigration_status;
    private Integer gateNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
    @NotNull(message = "passenger id cannot be empty")
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "checkin")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Passenger passenger;
}
