package edu.miu.cs54;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SecondaryTable(name = "Address" , pkJoinColumns = {
        @PrimaryKeyJoinColumn(name = "PATIENT_ID",referencedColumnName = "id")
})
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;


    @Column(table = "Address",name="STREET")
    private String street;
    @Column(table = "Address",name="ZIP")
    private String zip;
    @Column(table = "Address",name="CITY")
    private String city;
}
