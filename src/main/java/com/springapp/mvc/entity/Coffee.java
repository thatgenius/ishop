package com.springapp.mvc.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coffeeTable")
public class Coffee {
    @Id
    @GeneratedValue
    private int id;
    private String coffeeType;
    private int price;
    private String description;

    public Coffee(int id, String coffeeType, String description, int price) {
        this.id = id;
        this.coffeeType = coffeeType;
        this.description = description;
        this.price = price;
    }

    public Coffee() {
    }

    public String getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(String coffeeType) {
        this.coffeeType = coffeeType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
