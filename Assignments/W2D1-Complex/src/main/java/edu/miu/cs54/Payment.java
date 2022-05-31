package edu.miu.cs54;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class Payment {

    @Column(name = "PAYDATE")
    private String paydate;

    @Column(name = "AMOUNT")
    private double amount;
}
