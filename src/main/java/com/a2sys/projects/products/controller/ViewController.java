package com.a2sys.projects.products.controller;

import com.a2sys.projects.products.model.Product;
import com.a2sys.projects.products.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/view")
public class ViewController {

    Logger logger = LoggerFactory.getLogger(ViewController.class);

    private ProductRepository productRepository;

    @Autowired
    public ViewController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable("id") String id, Model model){
        Optional<Product> result = this.productRepository.findById(id);

        if(result.isPresent()){
            model.addAttribute("product", result.get());
            return "view-product";
        }

        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/add")
    public String addProduct(@Valid Product product, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("product", product);
            return "add-product";
        }

        this.productRepository.save(product);

        model.addAttribute("product", product);

        return "redirect:/view/" + product.getId();
    }

    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") String id, Model model){
        Optional<Product> result = this.productRepository.findById(id);
        if(result.isPresent()){
            model.addAttribute("product", result.get());
        }
        else
            model.addAttribute("product", new Product());
        return "update-product";
    }
}
