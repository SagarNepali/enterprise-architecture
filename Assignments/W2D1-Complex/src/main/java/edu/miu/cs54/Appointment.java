package edu.miu.cs54;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "APPDATE")
    private String appdate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PATIENT")
    private Patient patient;

    @Embedded
    private Payment payment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DOCTOR")
    private Doctor doctor;



}
