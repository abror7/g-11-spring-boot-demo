package uz.pdp.springbootdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.springbootdemo.entity.CharacteristicsChValues;
import uz.pdp.springbootdemo.entity.Product;
import uz.pdp.springbootdemo.projection.CharacteristicsValuesProjection;
import uz.pdp.springbootdemo.projection.ProductByIdProjection;
import uz.pdp.springbootdemo.projection.ProductProjection;

import java.util.List;


public interface CharacteristicsValuesRepo extends JpaRepository<CharacteristicsChValues, Integer> {


    @Query(nativeQuery = true, value = "select c.name, cv.value\n" +
            "from characteristics_ch_values\n" +
            "         join characteristics c on c.id = characteristics_ch_values.characteristic_id\n" +
            "         join ch_values cv on characteristics_ch_values.characteristic_value_id = cv.id\n" +
            "         join products_characteristics_ch_values_list pccvl\n" +
            "              on characteristics_ch_values.id = pccvl.characteristics_ch_values_list_id\n" +
            "where pccvl.products_id = :productId")
    List<CharacteristicsValuesProjection> getAllCharacteristicsValuesByProductId(Integer productId);

}
