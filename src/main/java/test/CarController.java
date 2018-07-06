package test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CarController {

    private final CarRepository repository;

    public void addCarToPerson(String name, String brand, int year, Status condition, Person owner){
        repository.save(new Car(name, brand, year, condition, owner));
    }

    @GetMapping("/cars")
    public List<Car> listCars(){
        return repository.findAll();
    }

    @GetMapping("cars/car")
    public String find(@RequestParam(value = "car_id", defaultValue="") String car_id,
                       @RequestParam(value = "name", defaultValue="") String name,
                       @RequestParam(value = "brand", defaultValue="") String brand,
                       @RequestParam(value = "year", defaultValue="") String year,
                       @RequestParam(value = "condition", defaultValue="") String condition){
        StringBuilder sb = new StringBuilder();
        for(Car c : repository.findAll()){
            if((car_id.isEmpty()  || c.getCar_id().equals(car_id)) &&
               (name.isEmpty()     || c.getName().equals(name)) &&
               (brand.isEmpty()     || c.getBrand().equals(brand)) &&
               (year.isEmpty()   || c.getYear() == (Integer.parseInt(year))) &&
               (condition.isEmpty()  || c.getCondition().equals(Status.valueOf(condition))) &&
               (!(car_id+name+brand+year+condition).equals(""))){
                sb.append(c.toString()).append(" ");
            }

        }
        return sb.toString();
    }

    @DeleteMapping("cars/car")
    public String deleteId(@RequestParam(value = "car_id") String car_id){
        repository.deleteById(car_id);
        return "Done";
    }

    @PatchMapping("/cars/car/")
    public String update(String car_id,
                         @RequestParam(value = "name", defaultValue="") String name,
                         @RequestParam(value = "brand", defaultValue="") String brand,
                         @RequestParam(value = "year", defaultValue="") String year,
                         @RequestParam(value = "condition", defaultValue="") String condition) {
        Car car = repository.findById(car_id).get();
        if(!name.isEmpty()) car.setName(name);
        if(!brand.isEmpty()) car.setBrand(brand);
        if(!year.isEmpty()) car.setYear(Integer.parseInt(year));
        if(!condition.isEmpty()) car.setCondition(Status.valueOf(condition));
        repository.flush();
        return "Done";
    }

    @PutMapping("/cars/car/")
    public String updateAll(@RequestBody Car car) {
        Car oldCar = repository.findById(car.getCar_id()).get();
        oldCar.setName(car.getName());
        oldCar.setBrand(car.getBrand());
        oldCar.setYear(car.getYear());
        oldCar.setCondition(car.getCondition());
        repository.flush();
        return "Done";
    }
}
