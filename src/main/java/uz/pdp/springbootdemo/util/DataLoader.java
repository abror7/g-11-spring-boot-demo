package uz.pdp.springbootdemo.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.springbootdemo.entity.Brand;
import uz.pdp.springbootdemo.entity.Car;
import uz.pdp.springbootdemo.repository.BrandRepo;
import uz.pdp.springbootdemo.repository.CarRepo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String initMode;

    private final CarRepo carRepo;
    private final BrandRepo brandRepo;

    @Override
    public void run(String... args) throws Exception {

        if (initMode.equals("always")) {

            Brand savedBrand = brandRepo.save(Brand.builder()
                    .name("Chevrolet")
                    .description("This is chevrolet brand...")
                    .ownerFullName("Oybek ")
                    .build());

            List<Car> carList = Arrays.asList(
                    Car.builder()
                            .brand(savedBrand)
                            .model("Nexia 1")
                            .build(),
                    Car.builder()
                            .brand(savedBrand)
                            .model("Nexia 2")
                            .build(),
                    Car.builder()
                            .brand(savedBrand)
                            .model("Nexia 3")
                            .build(),
                    Car.builder()
                            .brand(savedBrand)
                            .model("Nexia 4")
                            .build(),
                    Car.builder()
                            .brand(savedBrand)
                            .model("Nexia 5")
                            .build()
            );


            carRepo.saveAll(carList);


        }
    }
}
