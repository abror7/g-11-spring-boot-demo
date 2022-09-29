package uz.pdp.springbootdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootdemo.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {
}
