package edu.miu.cs54;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wheel {


	@Id
    private Long id;



    private int size;



    private double pressure;


}

