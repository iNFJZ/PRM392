package com.infjz.prm392.slot9;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance; //Bien toan cuc
    private List<Product> cartItems; //Danh sach mat hang o trong gio hang
    CartManager(){
        cartItems = new ArrayList<>(); //Khoi tao
    }
    //Phuong thuc xu ly bien tinh => Xu ly de du lieu khong mat di khi chuyen form
    public static synchronized CartManager getInstance(){
        if (instance == null){
            instance = new CartManager();
        }
        return instance;
    }
    //Them san pham vao gio hang
    public void addProductToCart(Product product){
        cartItems.add(product);
    }

    //Lay ve 1 item trong gio hang
    public List<Product> getCartItems(){
        return cartItems;
    }
}
