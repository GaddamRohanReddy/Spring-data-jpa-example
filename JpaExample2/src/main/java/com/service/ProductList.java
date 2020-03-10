package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.model.Product;


@Component
public class ProductList {
	
	List<Product> prodList =  new ArrayList<>();
	
	public List<Product> addProdToList(Product product){
		prodList.add(product);
		return prodList;	
	}

}
