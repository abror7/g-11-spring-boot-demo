package uz.pdp.springbootdemo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.springbootdemo.entity.Car;

import java.util.UUID;


public interface CarRepo extends JpaRepository<Car, Integer> {


    Page<Car> findAllByModel(Pageable pageable, String model);

    Page<Car> findAllByModelLikeIgnoreCase(Pageable pageable, String model);

}
