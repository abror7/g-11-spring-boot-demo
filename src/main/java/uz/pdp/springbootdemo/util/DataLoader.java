package uz.pdp.springbootdemo.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.springbootdemo.entity.*;
import uz.pdp.springbootdemo.repository.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String initMode;

    private final CarRepo carRepo;
    private final BrandRepo brandRepo;
    private final UserRepo userRepo;
    private final PassportRepo passportRepo;
    private final ContinentRepo continentRepo;
    private final CountryRepo countryRepo;


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


            Address address = Address.builder()
                    .addressLine("Shayhontohur tumani, Beruniy ko'chasi, Pdp Academy")
                    .build();

            User oybek = userRepo.save(User.builder()
                    .username("oybek123")
                    .email("oybek@mail.com")
                    .password("123")
                    .fullName("Oybek Akhmadjonov")
                    .addressList(Collections.singletonList(address))
                    .build());

            Continent europe = Continent
                    .builder()
                    .name("Europe")
                    .build();

            List<Country> countryList = Arrays.asList(
                    Country
                            .builder()
                            .name("Italy")
                            .continent(europe)
                            .build(),
                    Country
                            .builder()
                            .name("Spain")
                            .continent(europe)
                            .build(),
                    Country
                            .builder()
                            .name("France")
                            .continent(europe)
                            .build()
            );

            europe.setCountryList(countryList);

            continentRepo.save(europe);



//            passportRepo.save(Passport
//                    .builder()
//                    .serial("AA")
//                    .number("0034567")
//                    .owner(oybek)
//                    .build());


        }
    }
}
