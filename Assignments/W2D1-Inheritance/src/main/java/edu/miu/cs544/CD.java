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
//@DiscriminatorValue("cd")
public class CD extends Product{

    private String artist;

    public CD(String artist, String name, String desc){
        super(name,desc);
        this.artist = artist;
    }
}
