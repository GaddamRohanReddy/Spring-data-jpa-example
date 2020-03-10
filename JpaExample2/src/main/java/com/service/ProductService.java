package com.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Product;
import com.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductList productList;
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product createProduct(Product productRequest){
		Product product = new Product();
		product.setProductId(productRequest.getProductId());
		product.setProductName(productRequest.getProductName());
		product.setProductType(productRequest.getProductType());
		product.setProductCost(productRequest.getProductCost());
		productList.addProdToList(product);
		productRepository.save(product);
		return product;
	}
	
	/*public ProductList getProducts(){	
		return productList;
	}*/
	
	public List<Product> getProducts(){	
		return productRepository.findAll();
	}
	
	public Product getProduct(String productId){
		 Product product = productRepository.findById(Integer.parseInt(productId)).get();
		 return product;
	}
	
	public Product deleteProduct(String productId){
		Product product =  productRepository.findById(Integer.parseInt(productId)).get();
		if(product != null)
			productRepository.delete(product);
		return product;
	}
}
