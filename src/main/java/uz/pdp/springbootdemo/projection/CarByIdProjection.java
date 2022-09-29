package uz.pdp.springbootdemo.projection;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.Valid;

public interface CarByIdProjection {
    Integer getId();
    String getModel();
    Integer getBrandId();
    String getBrandName();
    String getBrandOwnerFullName();
}
