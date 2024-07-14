package com.infjz.prm392.slot11.Model;

public class Supplier {
    private int SupplierID;
    private String SupplierName;
    private String ContactName;
    private String Address;
    private String City;
    private String PostalCode;
    private String Country;
    private String Phone;
    private int CategoryID;

    public Supplier() {
    }

    public Supplier(int supplierID, String supplierName, String contactName, String address, String city, String postalCode, String country, String phone, int categoryID) {
        SupplierID = supplierID;
        SupplierName = supplierName;
        ContactName = contactName;
        Address = address;
        City = city;
        PostalCode = postalCode;
        Country = country;
        Phone = phone;
        CategoryID = categoryID;
    }

    public int getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(int supplierID) {
        SupplierID = supplierID;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String supplierName) {
        SupplierName = supplierName;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }
}
