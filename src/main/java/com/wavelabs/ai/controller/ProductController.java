package com.wavelabs.ai.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wavelabs.ai.model.Product;
import com.wavelabs.ai.repository.ProductRepository;

@RestController
@RequestMapping("/productapi")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Value("${productservice.url}")
	private String prodctServiceUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/products",method = RequestMethod.POST)
	public Product create(@RequestBody Product product) {
		String couponRes=restTemplate.getForObject(prodctServiceUrl+product.getCouponCode(), String.class);
		//product.setPrice(null)
		JsonObject couponObj = (JsonObject)JsonParser.parseString(couponRes);
		BigDecimal discount = couponObj.get("discount").getAsBigDecimal();
		product.setPrice(product.getPrice().subtract(discount));
		return productRepo.save(product);
	}
	

}
