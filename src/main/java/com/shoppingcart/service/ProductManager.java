package com.shoppingcart.service;

import java.util.List;

import com.shoppingcart.domain.Product;

public interface ProductManager {
	
    public void increasePrice(int percentage);
    
    public List<Product> getProducts();

}
