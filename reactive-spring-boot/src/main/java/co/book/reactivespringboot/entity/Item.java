package co.book.reactivespringboot.entity;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Document(collection = "item")
public class Item {
    private @Id String id;
    private String name;
    private double price;

    private String description;
    private String distributorRegion;
    private Date releaseDate;
    private int availableUnits;
    // private Point location;
    private boolean active;

    public Item(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Item(String id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

}
