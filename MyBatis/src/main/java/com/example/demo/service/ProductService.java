package com.example.demo.service;

import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    public Product getProductById(Long id) {
        return productMapper.selectProductById(id);
    }

    public List<Product> getProducts() {
        return productMapper.selectAllProducts();
    }

    @Transactional
    public void addProduct(Product product) {
        productMapper.insertProduct(product);
    }
}
