package com.shoppingcart.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingcart.service.ProductManager;

@Controller
public class InventoryController{
	
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private ProductManager productManager;

    @RequestMapping({"/hello","/home"})
    public String handleRequest(Map<String, Object> myModel)
            throws ServletException, IOException {

        String now = (new java.util.Date()).toString();
        logger.info("returning hello view with " + now);
        myModel.put("now", now);
        myModel.put("products", this.productManager.getProducts());

        return "hello";
    }

    @Autowired
    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

}
