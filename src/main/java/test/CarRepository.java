package test;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, String>{
    List<Car> findByRendszam(String rendszam);
    List<Car> findByTipus(String tipus);
    List<Car> findByMarka(String marka);
    List<Car> findByEvjarat(int evjarat);
    List<Car> findByAllapot(Status allapot);
}

