package test;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "person")
public class Person {

    @Id
    @NotNull
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String personId;

    @NotNull
    private String name;

    @NotNull
    private String telnum;

    @OneToMany(mappedBy="owner")
    private List<Car> ownedCars;

    protected Person(){}

    public Person(String name, String telnum){
        this.name = name;
        this.telnum = telnum;
        this.ownedCars = new ArrayList<>();
    }

    public String addCar(Car car){
        ownedCars.add(car);
        return "Done";
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("name: ").append(name).append(", ");
        sb.append("telnum: ").append(telnum);
        return sb.toString();
    }
}
