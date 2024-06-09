package com.acheron.flowers.store.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() {
        super("Category not found");
    }

    public CategoryNotFoundException(Long id) {
        super("Category with id: " + id + " not found");
    }
}
