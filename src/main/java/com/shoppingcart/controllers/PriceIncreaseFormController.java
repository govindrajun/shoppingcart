package com.shoppingcart.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shoppingcart.service.PriceIncrease;
import com.shoppingcart.service.PriceIncreaseValidator;
import com.shoppingcart.service.ProductManager;

@Controller
@RequestMapping({"/priceincrease"})
public class PriceIncreaseFormController  {

	 /** Logger for this class and subclasses */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private ProductManager productManager;

   @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new PriceIncreaseValidator());
    }
    
    @RequestMapping(method=RequestMethod.GET,params="new")
    public String createSpitterProfile(Model model){
    model.addAttribute(new PriceIncrease());
    return "priceincrease";
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView onSubmit(@Valid PriceIncrease command,BindingResult bindingResult)
            throws ServletException {
        Map<String, Object> model = new HashMap<String, Object>();

    	if(bindingResult.hasErrors()){
    		return new ModelAndView("priceincrease");
    		}
        int increase = command.getPercentage();
        logger.info("Increasing prices by " + increase + "%.");

        productManager.increasePrice(increase);
        String now = (new java.util.Date()).toString();
        model.put("now", now);
        model.put("products",productManager.getProducts());
        return  new ModelAndView("hello",model);
    }

    @Autowired
    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }
}
