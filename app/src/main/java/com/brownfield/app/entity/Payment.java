package com.brownfield.app.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long paymentId;
    @NotBlank(message = "card holder name cannot be empty")
    @Column(nullable = false)
    private String cardholderName;
    @NotBlank(message = "card number cannot be empty")
    @Size(min = 16,max=16,message = "invalid card")
    @Column(nullable = false)
    private String cardNumber;
    @NotNull(message = "card verification value cannot be empty")
    @DecimalMin(value = "100",message = "invalid card")
    @DecimalMax(value = "999",message = "invalid card")
    @Column(nullable = false)
    private Integer cvc;
}
