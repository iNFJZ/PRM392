package com.infjz.prm392.slot6;

public class Product {
    private String ProductCode;
    private String ProductName;
    private int ProductQuantity;

    public Product() {
    }

    public Product(String productCode, String productName, int productQuantity) {
        ProductCode = productCode;
        ProductName = productName;
        ProductQuantity = productQuantity;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        ProductQuantity = productQuantity;
    }

}
