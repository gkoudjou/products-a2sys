package com.a2sys.projects.products.controller;

import com.a2sys.projects.products.model.Product;
import com.a2sys.projects.products.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    Logger logger = LoggerFactory.getLogger(ApiController.class);

    private ProductRepository productRepository;

    @Autowired
    public ApiController(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") String id){
        return ResponseEntity.ok(new Product());
    }
}
