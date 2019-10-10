package com.example.demo;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long categoryid;
    private String description;
    @OneToMany (mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Car> cars;

    // getters and setters
    public long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(long categoryid) {
        this.categoryid = categoryid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
