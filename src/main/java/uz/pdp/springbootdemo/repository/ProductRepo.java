package uz.pdp.springbootdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.springbootdemo.entity.Country;
import uz.pdp.springbootdemo.entity.Product;
import uz.pdp.springbootdemo.projection.ProductByIdProjection;
import uz.pdp.springbootdemo.projection.ProductProjection;

import java.util.List;


public interface ProductRepo extends JpaRepository<Product, Integer> {


    @Query(nativeQuery = true, value = "select p.id as id,\n" +
            "       p.name as name,\n" +
            "       p.price\n" +
            "from products p\n")
    List<ProductProjection> getAllProducts();

    @Query(nativeQuery = true, value = "select p.id as id,\n" +
            "       p.name as name,\n" +
            "       p.price\n" +
            "from products p where p.id = :productId\n")
    ProductByIdProjection getProductById(Integer productId);
}
