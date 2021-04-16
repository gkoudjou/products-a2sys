package com.a2sys.projects.products.controller;

import com.a2sys.projects.products.model.Product;
import com.a2sys.projects.products.repository.ProductJdbcRepository;
import com.a2sys.projects.products.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    Logger logger = LoggerFactory.getLogger(ApiController.class);

    private ProductRepository productRepository;
    private ProductJdbcRepository productJdbcRepository;

    @Autowired
    public ApiController(ProductRepository productRepository, ProductJdbcRepository productJdbcRepository) {
        this.productRepository = productRepository;
        this.productJdbcRepository = productJdbcRepository;
    }

    @GetMapping("/product/{name}")
    public ResponseEntity<Iterable<Product>> getProductByName(@PathVariable("name") String name){
        return ResponseEntity.ok(this.productRepository.findByName(name));
    }

    @GetMapping("/product/custom-like/{name}")
    public ResponseEntity<Iterable<Product>> getProductLikeName(@PathVariable("name") String name){
        return ResponseEntity.ok(this.productRepository.customFindProductLikeName(name));
    }

    @GetMapping("/product/custom/{name}")
    public ResponseEntity<Product> getProductByNameCustom(@PathVariable("name") String name){
        return ResponseEntity.ok(this.productRepository.customFindProductByName(name));
    }

    @GetMapping("/product/count/{name}")
    public ResponseEntity<Integer> countProductHavingName(@PathVariable("name") String name){
        return ResponseEntity.ok(this.productRepository.countByNameContains(name));
    }

    @PostMapping("/product/custom-save/")
    public ResponseEntity<Product> saveViaJDbc(@RequestBody Product product){
        this.productJdbcRepository.save(product);
        return ResponseEntity.ok(product);
    }

}
