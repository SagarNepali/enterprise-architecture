package com.mum.cs544;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@Data
@NoArgsConstructor @AllArgsConstructor

public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String type;
    @ManyToOne
    private Employee employee;
}
