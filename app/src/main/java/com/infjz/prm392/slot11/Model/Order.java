package com.infjz.prm392.slot11.Model;

import java.util.Date;

public class Order {
    private int OrderID;
    private int CustomerID;
    private Date OrderDate;
    private String ShipAddress;
    private String ShipCity;
    private String ShipPostalCode;
    private String ShipCountry;

    public Order() {
    }

    public Order(int orderID, int customerID, Date orderDate, String shipAddress, String shipCity, String shipPostalCode, String shipCountry) {
        OrderID = orderID;
        CustomerID = customerID;
        OrderDate = orderDate;
        ShipAddress = shipAddress;
        ShipCity = shipCity;
        ShipPostalCode = shipPostalCode;
        ShipCountry = shipCountry;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }

    public String getShipAddress() {
        return ShipAddress;
    }

    public void setShipAddress(String shipAddress) {
        ShipAddress = shipAddress;
    }

    public String getShipCity() {
        return ShipCity;
    }

    public void setShipCity(String shipCity) {
        ShipCity = shipCity;
    }

    public String getShipPostalCode() {
        return ShipPostalCode;
    }

    public void setShipPostalCode(String shipPostalCode) {
        ShipPostalCode = shipPostalCode;
    }

    public String getShipCountry() {
        return ShipCountry;
    }

    public void setShipCountry(String shipCountry) {
        ShipCountry = shipCountry;
    }
}
