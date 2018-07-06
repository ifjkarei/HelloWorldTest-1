package test;

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
    @Column(name = "id")
    private String id;
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

    public Car(){}

    Car(String name,
        String brand,
        int year,
        Status condition){
        this.name = name;
        this.brand = brand;
        this.year = year;
        this.condition = condition;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(id).append(", ");
        sb.append("name: ").append(name).append(", ");
        sb.append("brand : ").append(brand).append(", ");
        sb.append("year: ").append(year).append(", ");
        sb.append("condition: ").append(condition);
        return sb.toString();
    }
}
