package com.madeintrees.service;

import com.madeintrees.model.Product;
import com.madeintrees.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by root on 16/10/17.
 */
@Service
@Transactional
public class ProductManagerImpl implements ProductManager {
    @Autowired
    ProductRepository productRepository;
    @Override
    public Product save(Product product){
        return productRepository.save(product);
    }

}
