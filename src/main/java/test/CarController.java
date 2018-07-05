package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    private CarRepository repository;

    @PostMapping("/cars")
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
    public String find(@RequestParam(value = "rendszam", defaultValue="") String rendszam,
                       @RequestParam(value = "tipus", defaultValue="") String tipus,
                       @RequestParam(value = "marka", defaultValue="") String marka,
                       @RequestParam(value = "evjarat", defaultValue="") String evjarat,
                       @RequestParam(value = "allapot", defaultValue="") String allapot){
        StringBuilder sb = new StringBuilder();
        for(Car c : repository.findAll()){
            if((rendszam.isEmpty()  || c.getRendszam().equals(rendszam)) &&
               (tipus.isEmpty()     || c.getTipus().equals(tipus)) &&
               (marka.isEmpty()     || c.getMarka().equals(marka)) &&
               (evjarat.isEmpty()   || c.getEvjarat() == (Integer.parseInt(evjarat))) &&
               (rendszam.isEmpty()  || c.getAllapot().equals(Status.valueOf(allapot))) &&
               (!(rendszam+tipus+marka+evjarat+allapot).equals(""))){
                sb.append(c.toString()).append(" ");
            }

        }
        return sb.toString();
    }

    @DeleteMapping("cars/car")
    public String deleteId(@RequestParam(value = "rendszam") String rendszam){
        repository.deleteById(rendszam);
        return "Done";
    }

    @PatchMapping("/cars/car/")
    public String update(String rendszam,
                         @RequestParam(value = "tipus", defaultValue="") String tipus,
                         @RequestParam(value = "marka", defaultValue="") String marka,
                         @RequestParam(value = "evjarat", defaultValue="") String evjarat,
                         @RequestParam(value = "allapot", defaultValue="") String allapot) {
        Car car = repository.findByRendszam(rendszam).get(0);
        if(!tipus.isEmpty()) car.setTipus(tipus);
        if(!marka.isEmpty()) car.setMarka(marka);
        if(!evjarat.isEmpty()) car.setEvjarat(Integer.parseInt(evjarat));
        if(!allapot.isEmpty()) car.setAllapot(Status.valueOf(allapot));
        repository.flush();
        return "Done";
    }

    @PutMapping("/cars/car/")
    public String updateAll(@RequestBody Car car) {
        Car oldCar = repository.findByRendszam(car.getRendszam()).get(0);
        oldCar.setTipus(car.getTipus());
        oldCar.setMarka(car.getMarka());
        oldCar.setEvjarat(car.getEvjarat());
        oldCar.setAllapot(car.getAllapot());
        repository.flush();
        return "Done";
    }
}
