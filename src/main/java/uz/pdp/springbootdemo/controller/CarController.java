package uz.pdp.springbootdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.CarDto;
import uz.pdp.springbootdemo.entity.Car;
import uz.pdp.springbootdemo.service.CarService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;


    // @ModelAttribute

    @GetMapping
    public String getAllCars(@RequestParam(name = "page", defaultValue = "1") int page,
                             @RequestParam(name = "size", defaultValue = "5") int size,
                             @RequestParam(name = "search", required = false) String search
    ) {

        List<Car> allCarsFromDb = carService.getAllCarsFromDb(page, size, search);

        System.out.println(allCarsFromDb);


        return "view-cars";
    }

    @GetMapping
    public String getCarForm() {
        return "car-form";
    }


    @PostMapping
    public String saveCar(@Valid CarDto carDto, BindingResult bindingResult) {


        carService.saveCar(carDto);

        return "redirect:/cars";
    }


    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable int id) {
        carService.deleteCarById(id);
        return "redirect:/cars";
    }
}
