package com.a2sys.projects.products.repository;

import com.a2sys.projects.products.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class ProductJdbcRepository {

    private static final String INSERT_SQL = "insert into product (id, name, quantity, price) values (:id, :name, :quantity, :price)";

    private NamedParameterJdbcTemplate template;

    @Autowired
    public ProductJdbcRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public void save(Product product){
        HashMap<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", product.getId());
        namedParameters.put("name", product.getName());
        namedParameters.put("quantity", product.getQuantity());
        namedParameters.put("price", product.getPrice());

        this.template.update(INSERT_SQL, namedParameters);
    }
}
