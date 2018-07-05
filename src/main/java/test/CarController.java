package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    CarRepository repository;

    @PostMapping("/cars")
    //@RequestMapping(value = "/addcar", method = RequestMethod.POST)
    public String addCar(@RequestParam(value="tipus") String tipus,
                       @RequestParam(value="marka") String marka,
                       @RequestParam(value="evjarat") int evjarat,
                       @RequestParam(value="allapot") Status allapot){
        // save a single Customer
        repository.save(new Car(tipus, marka, evjarat, allapot));

        return "Done";
    }

    /*@PostMapping("/addall")
    public String addAll(){
        // save a single Customer
        repository.save(new Car("Focus", "Ford", 2012, Status.Jó));

        // save a list of Customers
        repository.saveAll(Arrays.asList(   new Car("Model S", "Tesla", 2016, Status.Rossz),
                                            new Car("Model 3", "Tesla", 2017, Status.Elfogadható),
                                            new Car("Model X", "Tesla", 2018, Status.Elfogadható),
                                            new Car("Ix20", "Hyundai", 2017, Status.Jó)));
        return "Done";
    }*/

    @GetMapping("/cars")
    public List<Car> listCars(){
        return repository.findAll();
    }

    @GetMapping("cars/car")
    public String findId(@RequestParam("rendszam") String rendszam){
        return repository.findByRendszam(rendszam).toString();
    }

    @DeleteMapping("cars/car")
    public String deleteId(@RequestParam("rendszam") String rendszam){
        repository.deleteById(rendszam);
        return "Done";
    }

    @PatchMapping("/cars/car/")
    public String updateAllapot(
            @RequestBody CarAllapot carAllapot) {

        repository.findByRendszam(carAllapot.getRendszam()).get(0).setAllapot(carAllapot.getAllapot());
        repository.flush();
        return "Done";
    }

    @PutMapping("/cars/car/")
    public String updateAll(
            @RequestBody Car car) {

        Car oldCar = repository.findByRendszam(car.getRendszam()).get(0);
        oldCar.setTipus(car.getTipus());
        oldCar.setMarka(car.getMarka());
        oldCar.setEvjarat(car.getEvjarat());
        oldCar.setAllapot(car.getAllapot());
        repository.flush();
        return "Done";
    }
}
