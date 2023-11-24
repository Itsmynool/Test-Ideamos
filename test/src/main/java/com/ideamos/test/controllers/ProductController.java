package com.ideamos.test.controllers;

import com.ideamos.test.models.ProductModel;
import com.ideamos.test.services.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ArrayList<ProductModel> getProducts(){
        return this.productService.getProducts();
    }

    @PostMapping
    public ProductModel saveProduct(@RequestBody ProductModel product){
        return this.productService.saveProduct(product);
    }

    @GetMapping(path = "/{id}")
    public Optional <ProductModel> getProductById(@PathVariable Long id){
        return this.productService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public ProductModel updateProductById(@RequestBody ProductModel request, @PathVariable("id") Long id){
        return this.productService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.productService.deleteProduct(id);
        if(ok == true){
            return "Product with id: " + id + " deleted";
        } else {
            return "Not Found";
        }
    }
}
