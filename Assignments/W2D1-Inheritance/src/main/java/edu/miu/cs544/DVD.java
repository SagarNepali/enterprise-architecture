package edu.miu.cs544;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data

@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("dvd")
public class DVD extends Product{
    private String genre;
}
