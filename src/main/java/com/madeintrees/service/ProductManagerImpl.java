package com.madeintrees.service;

import com.madeintrees.model.Product;
import com.madeintrees.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by root on 16/10/17.
 */
@Service
@Transactional
public class ProductManagerImpl implements ProductManager {
    @Autowired
    ProductRepository productRepository;

    @Override
    public void deleteById(int id) {
        productRepository.delete(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    @Override
    public Product save(Product product){
        return productRepository.save(product);

    }

}
