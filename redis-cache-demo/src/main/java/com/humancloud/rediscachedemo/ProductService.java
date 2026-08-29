package com.humancloud.rediscachedemo;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class ProductService 
{
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) 
    {
        return productRepository.save(product);
    }

    @Cacheable(value = "products", key = "#id")
    public Product getProduct(Long id) 
    {
        System.out.println("Getting product from MySQL...");
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> getAllProducts() 
    {
        return productRepository.findAll();
    }
    
    @CachePut(value = "products",key = "#id")
    public Product updateProduct(Long id, Product product)
    {
        Product existingProduct = getProduct(id);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        return productRepository.save(existingProduct);
    }
    
    @CacheEvict(value = "products",key = "#id")
    public void deleteProduct(Long id) 
    {
        productRepository.deleteById(id);
    }
}