package uz.pdp.springbootdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootdemo.dto.CarByIdDto;
import uz.pdp.springbootdemo.dto.CarDto;
import uz.pdp.springbootdemo.entity.Brand;
import uz.pdp.springbootdemo.entity.Car;
import uz.pdp.springbootdemo.projection.CarByIdProjection;
import uz.pdp.springbootdemo.repository.BrandRepo;
import uz.pdp.springbootdemo.repository.CarRepo;
import uz.pdp.springbootdemo.service.CarService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final CarRepo carRepo;
    private final BrandRepo brandRepo;


    // @ModelAttribute

    @GetMapping
    public String getAllCars(@RequestParam(name = "page", defaultValue = "1") int page,
                             @RequestParam(name = "size", defaultValue = "5") int size,
                             @RequestParam(name = "search", required = false) String search,
                             Model model
    ) {

        List<Car> allCarsFromDb = carService.getAllCarsFromDb(page, size, search);
        model.addAttribute("cars", allCarsFromDb);
        return "view-cars";
    }


    @GetMapping("/{id}")
    public HttpEntity<?> getCarById(@PathVariable Integer id){
        CarByIdProjection carById = carService.getCarById(id);
        return ResponseEntity.ok(carById);
    }

    @GetMapping("/get-form")
    public String getCarForm(Model model,
                             @RequestParam(required = false) Integer id) {

        CarDto carDto = new CarDto();
        List<Brand> brandList = brandRepo.findAll();

        if (id != null) {
            Optional<Car> optionalCar = carRepo.findById(id);
            if (optionalCar.isPresent()) {
                Car car = optionalCar.get();
                carDto.setId(car.getId());
                carDto.setModel(car.getModel());
                carDto.setDescription(car.getDescription());
                carDto.setBrandId(car.getBrand().getId());
            }
        }

        model.addAttribute("carDto", carDto);
        model.addAttribute("brandList", brandList);
        return "car-form";
    }


    @PostMapping
    public String saveCar(@Valid CarDto carDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "car-form";
        }
        carService.saveCar(carDto);
        return "redirect:/cars";
    }


    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable int id) {
        carService.deleteCarById(id);
        return "redirect:/cars";
    }
}
