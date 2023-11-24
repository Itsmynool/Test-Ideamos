package com.ideamos.test.controllers;

import com.ideamos.test.models.ProductoModel;
import com.ideamos.test.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    private ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<ProductoModel>> getAllProducts() {
        return ResponseEntity.ok(productoService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoModel> getProductById(@PathVariable Long id) {
        return productoService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductoModel> createProduct(@RequestBody ProductoModel producto) {
        ProductoModel savedProducto = productoService.saveProduct(producto);
        return ResponseEntity.status(201).body(savedProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoModel> updateProduct(@PathVariable Long id, @RequestBody ProductoModel producto) {
        return productoService.updateProduct(id, producto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        if (productoService.deleteProduct(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}