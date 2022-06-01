package edu.miu.cs54;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@SecondaryTable(name="Owners")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
public class Vehicle {


	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


	@Column(table="Owners")
//, pkJoinColums= {@JoinColumn(name = "id", referencedColumnName="id"})
    private String owner;


@Column(name="manufacturer")
    private String make;



    private String model;



    private int year;



    private String color;


	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "wheels_id")
	@OrderColumn(name= "wheels_ORDER")

    private List<Wheel> wheels = new ArrayList<>();

    public Vehicle(String owner, String make, String model, int year, String color,List<Wheel> wheels) {
        this.owner = owner;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.wheels = wheels;
    }
}





