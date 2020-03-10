package com.controller;


import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.model.Product;
import com.model.UserNotFoundException;
import com.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/{id}")
	public Resource<Product> getProduct(@PathVariable String id){
		Product product =productService.getProduct(id);
		 if(product == null)
			throw new UserNotFoundException("id - "+id);
		 Resource<Product> resource = new Resource<Product>(product);
		 ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getProducts());
		 resource.add(linkTo.withRel("all-products"));
		return resource;	
	}
	
	@GetMapping()
	public ResponseEntity<List<Product>> getProducts(){
		 return new ResponseEntity<>(productService.getProducts(),HttpStatus.OK);
	}
	@PostMapping(consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
				 produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
		Product createdProduct = productService.createProduct(product);
		URI location = ServletUriComponentsBuilder
					   .fromCurrentRequest()
					   .path("/{id}")
					   .buildAndExpand(createdProduct.getProductId())
					   .toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/{id}")
	public Product deleteProduct(@PathVariable String id){
		Product product =productService.deleteProduct(id);
		 if(product == null)
			throw new UserNotFoundException("id - "+id);
		return product;	
	}
	
}
