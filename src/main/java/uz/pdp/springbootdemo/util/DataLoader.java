package uz.pdp.springbootdemo.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.springbootdemo.entity.*;
import uz.pdp.springbootdemo.entity.enums.RoleEnum;
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

    private final RoleRepository roleRepo;

    private final CarRepo carRepo;
    private final BrandRepo brandRepo;
    private final UserRepo userRepo;
    private final PassportRepo passportRepo;
    private final ContinentRepo continentRepo;
    private final CountryRepo countryRepo;

    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {

        if (initMode.equals("always")) {
            // ROLE LAR DB GA SAQLANADI...
            roleRepo.save(Role.builder()
                    .name(RoleEnum.ROLE_SUPER_ADMIN)
                    .build());
            roleRepo.save(Role.builder()
                    .name(RoleEnum.ROLE_ADMIN)
                    .build());
            roleRepo.save(Role.builder()
                    .name(RoleEnum.ROLE_USER)
                    .build());

            userRepo.save(User.builder()
                            .username("afzal")
                            .password(passwordEncoder.encode("123"))
                            .fullName("Afzal Abrorjonov")
                            .email("afzal@mail.com")
                            .isEnabled(true)
//                            .roles()
                    .build());
//            ==============================
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
                    .password(passwordEncoder.encode("123"))
                    .fullName("Oybek Akhmadjonov")
                    .addressList(Collections.singletonList(address))
                    .isEnabled(true)
                    .build());


            User ahad = userRepo.save(User.builder()
                    .username("ahad")
                    .email("ahad@mail.com")
                    .password(passwordEncoder.encode("123"))
                    .fullName("Ahad Ahmadov")
                    .isEnabled(true)
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
