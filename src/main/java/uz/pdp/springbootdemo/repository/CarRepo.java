package uz.pdp.springbootdemo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.springbootdemo.dto.CarByIdDto;
import uz.pdp.springbootdemo.entity.Car;
import uz.pdp.springbootdemo.projection.CarByIdProjection;

import java.util.Optional;
import java.util.UUID;


public interface CarRepo extends JpaRepository<Car, Integer> {


    Page<Car> findAllByModel(Pageable pageable, String model);

    Page<Car> findByModelLikeIgnoreCase(Pageable pageable, String model);


    @Query(value = "select c.id              as id,\n" +
            "       c.model,\n" +
            "       b.id              as brandId,\n" +
            "       b.name            as brandName,\n" +
            "       b.owner_full_name as brandOwnerFullName\n" +
            "from cars c\n" +
            "         join brands b on b.id = c.brand_id\n" +
            "where c.id = :carId", nativeQuery = true)
    Optional<CarByIdProjection> getCarById(Integer carId);
}
