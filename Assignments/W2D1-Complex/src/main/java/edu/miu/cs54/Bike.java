package edu.miu.cs54;
import javax.persistence.*;
import java.util.List;

@DiscriminatorValue("bike")
@Entity
public class Bike extends Vehicle {

    private int numgears;

    public Bike(String owner, String make, String model, int year, String color, List<Wheel> wheels, int numgears) {
        super(owner, make, model, year, color, wheels);
        this.numgears = numgears;
    }

    public Bike() {

    }
}

