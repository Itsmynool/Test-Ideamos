package com.ideamos.test.services;

import com.ideamos.test.models.ProductoModel;
import com.ideamos.test.repositories.IProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    IProductoRepository productoRepository;

    public ProductoService(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoModel> getProducts() {
        return productoRepository.findAll();
    }

    public Optional<ProductoModel> getById(Long id) {
        return productoRepository.findById(id);
    }

    public ProductoModel saveProduct(ProductoModel producto) {
        return productoRepository.save(producto);
    }

    public Optional<ProductoModel> updateProduct(Long id, ProductoModel producto) {
        return productoRepository.findById(id)
                .map(p -> {
                    p.setNombre(producto.getNombre());
                    p.setPrecio(producto.getPrecio());
                    p.setDescripcion(producto.getDescripcion());
                    return productoRepository.save(p);
                });
    }

    public boolean deleteProduct(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
