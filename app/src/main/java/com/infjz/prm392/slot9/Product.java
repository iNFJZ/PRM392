package com.infjz.prm392.slot9;

public class Product {
    private String styleID;
    private String brand;
    private String price;
    private String infor;
    private String searchImage;

    public Product() {
    }

    public Product(String styleID, String brand, String price, String infor, String searchImage) {
        this.styleID = styleID;
        this.brand = brand;
        this.price = price;
        this.infor = infor;
        this.searchImage = searchImage;
    }

    public String getStyleID() {
        return styleID;
    }

    public void setStyleID(String styleID) {
        this.styleID = styleID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public String getSearchImage() {
        return searchImage;
    }

    public void setSearchImage(String searchImage) {
        this.searchImage = searchImage;
    }
}
