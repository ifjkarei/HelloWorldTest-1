package test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CarController {

    private final CarRepository repository;

    @PostMapping("/cars")
    public String addCar(@RequestParam(value="name") String name,
                       @RequestParam(value="brand") String brand,
                       @RequestParam(value="year") int year,
                       @RequestParam(value="condition") Status condition){
        // save a single Customer
        repository.save(new Car(name, brand, year, condition));

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
    public String find(@RequestParam(value = "id", defaultValue="") String id,
                       @RequestParam(value = "name", defaultValue="") String name,
                       @RequestParam(value = "brand", defaultValue="") String brand,
                       @RequestParam(value = "year", defaultValue="") String year,
                       @RequestParam(value = "condition", defaultValue="") String condition){
        StringBuilder sb = new StringBuilder();
        for(Car c : repository.findAll()){
            if((id.isEmpty()  || c.getId().equals(id)) &&
               (name.isEmpty()     || c.getName().equals(name)) &&
               (brand.isEmpty()     || c.getBrand().equals(brand)) &&
               (year.isEmpty()   || c.getYear() == (Integer.parseInt(year))) &&
               (id.isEmpty()  || c.getCondition().equals(Status.valueOf(condition))) &&
               (!(id+name+brand+year+condition).equals(""))){
                sb.append(c.toString()).append(" ");
            }

        }
        return sb.toString();
    }

    @DeleteMapping("cars/car")
    public String deleteId(@RequestParam(value = "id") String id){
        repository.deleteById(id);
        return "Done";
    }

    @PatchMapping("/cars/car/")
    public String update(String id,
                         @RequestParam(value = "name", defaultValue="") String name,
                         @RequestParam(value = "brand", defaultValue="") String brand,
                         @RequestParam(value = "year", defaultValue="") String year,
                         @RequestParam(value = "condition", defaultValue="") String condition) {
        Car car = repository.findById(id).get();
        if(!name.isEmpty()) car.setName(name);
        if(!brand.isEmpty()) car.setBrand(brand);
        if(!year.isEmpty()) car.setYear(Integer.parseInt(year));
        if(!condition.isEmpty()) car.setCondition(Status.valueOf(condition));
        repository.flush();
        return "Done";
    }

    @PutMapping("/cars/car/")
    public String updateAll(@RequestBody Car car) {
        Car oldCar = repository.findById(car.getId()).get();
        oldCar.setName(car.getName());
        oldCar.setBrand(car.getBrand());
        oldCar.setYear(car.getYear());
        oldCar.setCondition(car.getCondition());
        repository.flush();
        return "Done";
    }
}
