package test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "car")
public class Car implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "car_id")
    private String car_id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "brand")
    private String brand;

    @NotNull
    @Column(name = "year")
    private int year;

    @NotNull
    @Column(name = "condition")
    private Status condition;

    @JsonIgnore
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Person owner;

    //@Transient
    private String ownerId;

    public Car(){}

    Car(String name,
        String brand,
        int year,
        Status condition,
        Person owner){
        this.name = name;
        this.brand = brand;
        this.year = year;
        this.condition = condition;
        this.owner = owner;
        this.ownerId = owner.getPerson_id();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(car_id).append(", ");
        sb.append("name: ").append(name).append(", ");
        sb.append("brand : ").append(brand).append(", ");
        sb.append("year: ").append(year).append(", ");
        sb.append("condition: ").append(condition).append(", ");
        sb.append("ownerId: ").append(ownerId);
        return sb.toString();
    }
}
