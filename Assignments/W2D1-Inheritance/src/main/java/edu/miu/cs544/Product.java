package edu.miu.cs544;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    public Product(String name, String description){
        this.name = name;
        this.description = description;
    }

}
