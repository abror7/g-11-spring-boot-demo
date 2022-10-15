package uz.pdp.springbootdemo.controller;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.springbootdemo.entity.User;
import uz.pdp.springbootdemo.repository.ProductRepo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepo productRepo;


    /**
     * GET ALL PRODUCTS
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        String currentUser = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Current user: " + currentUser);
        return ResponseEntity.ok(productRepo.getAllProducts());
    }

    /**
     * GET PRODUCT BY ID
     *
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
        return ResponseEntity.ok(productRepo.getProductById(id));
    }
}
