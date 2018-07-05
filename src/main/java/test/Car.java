package test;
//HELLO
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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

    public Car(String tipus,
               String marka,
               int evjarat,
               Status allapot){
        this.tipus = tipus;
        this.marka = marka;
        this.evjarat = evjarat;
        this.allapot = allapot;
    }

    public String getRendszam() {
        return rendszam;
    }

    public String getTipus() {
        return tipus;
    }

    public String getMarka() {
        return marka;
    }

    public int getEvjarat() {
        return evjarat;
    }

    public Status getAllapot() {
        return allapot;
    }

    public void setRendszam(String rendszam) {
        this.rendszam = rendszam;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public void setEvjarat(int evjarat) {
        this.evjarat = evjarat;
    }

    public void setAllapot(Status allapot) {
        this.allapot = allapot;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Rendszam: " + rendszam + ", ");
        sb.append("Tipus: " + tipus + ", ");
        sb.append("Marka : " + marka + ", ");
        sb.append("Evjarat: " + evjarat + ", ");
        sb.append("Allapot: " + allapot);
        return sb.toString();
    }
}
