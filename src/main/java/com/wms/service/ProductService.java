package com.wms.service;

import com.wms.entity.Product;
import com.wms.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Create a new product with validation
     * 
     * @param product the product to create
     * @return the created product
     * @throws IllegalArgumentException if SKU or name is null/empty
     */
    public Product createProduct(Product product) {
        if (product.getSku() == null || product.getSku().trim().isEmpty()) {
            throw new IllegalArgumentException("SKU cannot be null or empty");
        }
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        return productRepository.save(product);
    }

    /**
     * Get all products
     * 
     * @return list of all products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Get product by ID
     * 
     * @param id the product ID
     * @return optional containing the product if found
     * @throws IllegalArgumentException if ID is invalid
     */
    public Optional<Product> getProductById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid product ID");
        }
        return productRepository.findById(id);
    }

    /**
     * Update an existing product
     * 
     * @param id             the product ID
     * @param productDetails the updated product details
     * @return the updated product
     * @throws IllegalArgumentException if ID is invalid
     * @throws RuntimeException         if product not found
     */
    public Product updateProduct(Long id, Product productDetails) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid product ID");
        }
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product existingProduct = product.get();
            if (productDetails.getName() != null && !productDetails.getName().trim().isEmpty()) {
                existingProduct.setName(productDetails.getName());
            }
            if (productDetails.getSku() != null && !productDetails.getSku().trim().isEmpty()) {
                existingProduct.setSku(productDetails.getSku());
            }
            if (productDetails.getCategory() != null && !productDetails.getCategory().trim().isEmpty()) {
                existingProduct.setCategory(productDetails.getCategory());
            }
            return productRepository.save(existingProduct);
        }
        throw new RuntimeException("Product not found with ID: " + id);
    }

    /**
     * Delete a product
     * 
     * @param id the product ID
     * @throws IllegalArgumentException if ID is invalid
     * @throws RuntimeException         if product not found
     */
    public void deleteProduct(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid product ID");
        }
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }
}
