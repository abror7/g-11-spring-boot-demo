package uz.pdp.springbootdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootdemo.entity.Continent;
import uz.pdp.springbootdemo.entity.Country;


public interface CountryRepo extends JpaRepository<Country, Integer> {



}
