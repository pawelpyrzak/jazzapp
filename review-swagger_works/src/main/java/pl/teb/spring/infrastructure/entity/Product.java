package pl.teb.spring.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;
@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String uuid;
    @Column(length = 160, nullable = false)
    private String name;

    private int rating;
    @Column(length = 180, nullable = false)
    private String comment;
    private double price;
    @ManyToOne
    @JoinColumn(name = "category_id")

    private Category category;

    public Product(String uuid, String name, int rating, String comment, double price, Category category) {
        this.uuid = uuid;
        this.name = name;
        this.rating = rating;
        this.comment = comment;
        this.price = price;
        this.category = category;
    }
}
