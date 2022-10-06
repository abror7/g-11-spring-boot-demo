package uz.pdp.springbootdemo.projection;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.List;

public interface ProductByIdProjection {
    Integer getId();

    String getName();

    BigDecimal getPrice();

    @Value("#{@characteristicsValuesRepo.getAllCharacteristicsValuesByProductId(target.id)}")
    List<CharacteristicsValuesProjection> getCharacters();


}
