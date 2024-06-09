package com.infjz.prm392.slot9;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Product implements Parcelable {
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

    protected Product(Parcel in) {
        styleID = in.readString();
        brand = in.readString();
        price = in.readString();
        infor = in.readString();
        searchImage = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(styleID);
        dest.writeString(brand);
        dest.writeString(price);
        dest.writeString(infor);
        dest.writeString(searchImage);
    }
}
