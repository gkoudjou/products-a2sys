package com.a2sys.projects.products.repository;

import com.a2sys.projects.products.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
    List<Product> findByName(String name);
    int countByNameContains(String name);

    @Query("select p from Product p, Review r where p.name = ?1")
    Product customFindProductByName(String name);

    @Query(value = "select * from product where p.name like %?1", nativeQuery = true)
    List<Product> customFindProductLikeName(String name);
}
