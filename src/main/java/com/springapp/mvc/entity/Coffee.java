package com.springapp.mvc.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "coffeeTable")
public class Coffee {
    private int coffeeId;
    private String coffeeType;
    private int price;
    private String description;
    private Set<Additive> additives = new HashSet<Additive>();

    public Coffee(int id, String coffeeType, String description, int price) {
        this.coffeeId = id;
        this.coffeeType = coffeeType;
        this.description = description;
        this.price = price;
    }

    public Coffee() {
    }

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "coffees", fetch = FetchType.EAGER)
    public Set<Additive> getAdditives() {
        return additives;
    }

    public void setAdditives(Set<Additive> additives) {
        this.additives = additives;
    }

    @Column(name = "coffeeType")
    public String getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(String coffeeType) {
        this.coffeeType = coffeeType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "coffeeId")
    public int getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(int coffeeId) {
        this.coffeeId = coffeeId;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void addNewAdditive(Additive additive) {
        additive.getCoffees().add(this);
        getAdditives().add(additive);
    }
}
