package com.infjz.prm392.slot11.ServerRespone;

import com.infjz.prm392.slot11.Model.Product;

import java.util.List;

public class ServerResponseProduct {
    private List<Product> products;
    private String message;

    public List<Product> getProducts() {
        return products;
    }

    public String getMessage() {
        return message;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
