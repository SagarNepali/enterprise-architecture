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
//@DiscriminatorValue("book")
public class Book extends  Product{
    private String title;

    public Book(String title, String name, String desc){
        super(name,desc);
        this.title = title;
    }

}
