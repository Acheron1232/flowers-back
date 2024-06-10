package com.acheron.flowers.store.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Product not found");
    }

    public ProductNotFoundException(Long id) {
        super("Product with id: " + id + " not found");
    }
}
