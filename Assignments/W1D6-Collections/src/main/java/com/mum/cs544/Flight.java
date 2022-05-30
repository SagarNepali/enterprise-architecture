package com.mum.cs544;


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
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer flightNumber;
    @Column(name = "`from`")
    private String from;
    @Column(name = "`to`")
    private String to;
    @Temporal(TemporalType.DATE)
    private Date date;
}
