package com.example;

import org.springframework.stereotype.Repository;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Repository
public class ProductService implements CrudListener<Product> {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product add(Product product) {
        return repository.insert(product);
    }

    @Override
    public Product update(Product product) {
        return repository.save(product);
    }

    @Override
    public void delete(Product product) {
        repository.delete(product);
    }

}
