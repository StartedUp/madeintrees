package com.madeintrees.service;

import com.madeintrees.model.Product;

import java.util.Date;
import java.util.List;

/**
 * Created by root on 16/10/17.
 */
public interface ProductManager {
    public Product save(Product product);
    public List<Product> findAll();
    public void deleteById(int id);
  /*  public List<Event> findByEndDateAfter(Date endDate);
    public Event findById(Integer eventId);*/

}
