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
    @Column(name = "rendszam")
    private String rendszam;
    @NotNull
    @Column(name = "tipus")
    private String tipus;
    @NotNull
    @Column(name = "marka")
    private String marka;
    @NotNull
    @Column(name = "evjarat")
    private int evjarat;
    @NotNull
    @Column(name = "allapot")
    private Status allapot;

    public Car(){}

    Car(String tipus,
        String marka,
        int evjarat,
        Status allapot){
        this.tipus = tipus;
        this.marka = marka;
        this.evjarat = evjarat;
        this.allapot = allapot;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Rendszam: ").append(rendszam).append(", ");
        sb.append("Tipus: ").append(tipus).append(", ");
        sb.append("Marka : ").append(marka).append(", ");
        sb.append("Evjarat: ").append(evjarat).append(", ");
        sb.append("Allapot: ").append(allapot);
        return sb.toString();
    }
}
