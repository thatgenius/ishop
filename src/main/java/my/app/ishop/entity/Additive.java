package my.app.ishop.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "additiveTable")
public class Additive {

    private int additiveId;
    private String additiveName;
    private int price;
    private String description;
    private Set<Coffee> coffees = new HashSet<Coffee>();

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "additiveId")
    public int getAdditiveId() {
        return additiveId;
    }

    public void setAdditiveId(int additiveId) {
        this.additiveId = additiveId;
    }

    @Column(name = "additiveName")
    public String getAdditiveName() {
        return additiveName;
    }

    public void setAdditiveName(String additiveName) {
        this.additiveName = additiveName;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "coffeeAdditive", catalog = "coffeeDataBase", joinColumns = @JoinColumn(name = "additiveId"), inverseJoinColumns = @JoinColumn(name = "coffeeId"))
    public Set<Coffee> getCoffees() {
        return coffees;
    }

    public void setCoffees(Set<Coffee> coffees) {
        this.coffees = coffees;
    }

    public void addNewCoffee(Coffee coffee) {
        coffee.getAdditives().add(this);
        getCoffees().add(coffee);
    }


}
