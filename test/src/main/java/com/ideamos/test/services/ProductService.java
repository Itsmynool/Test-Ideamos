package com.ideamos.test.services;

import com.ideamos.test.models.ProductModel;
import com.ideamos.test.repositories.IProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService {
    IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ArrayList<ProductModel> getProducts(){
        return (ArrayList<ProductModel>) productRepository.findAll();
    }

    public ProductModel saveProduct(ProductModel product){
        return productRepository.save(product);
    }

    public Optional<ProductModel> getById(Long id){
        return productRepository.findById(id);
    }

    public ProductModel updateById(ProductModel request, Long id){
        Optional<ProductModel> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            ProductModel product = productOptional.get();

            if (request.getName() != null) {
                product.setName(request.getName());
            }
            if (request.getPrice() != null) {
                product.setPrice(request.getPrice());
            }
            if (request.getDescription() != null) {
                product.setDescription(request.getDescription());
            }

            return productRepository.save(product);
        } else {
            throw new EntityNotFoundException("No product found with id: " + id);
        }
    }

    public Boolean deleteProduct (Long id){
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}