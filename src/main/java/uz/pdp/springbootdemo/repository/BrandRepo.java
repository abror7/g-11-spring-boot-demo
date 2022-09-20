package uz.pdp.springbootdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.springbootdemo.entity.Brand;


public interface BrandRepo extends JpaRepository<Brand, Integer> {


}
