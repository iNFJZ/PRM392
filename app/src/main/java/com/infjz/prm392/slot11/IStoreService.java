package com.infjz.prm392.slot11;

import com.infjz.prm392.slot11.Model.Account;
import com.infjz.prm392.slot11.Model.Customer;
import com.infjz.prm392.slot11.Model.Login;
import com.infjz.prm392.slot11.Model.Product;
import com.infjz.prm392.slot11.Model.ServerResponse;
import com.infjz.prm392.slot11.ServerRespone.ServerResponseCustomer;
import com.infjz.prm392.slot11.ServerRespone.ServerResponseLogin;
import com.infjz.prm392.slot11.ServerRespone.ServerResponseProduct;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IStoreService {

    @FormUrlEncoded
    @POST("CustomerAPI/insertCustomer.php")
    Call<ServerResponse> insertCustomer(
            @Field("CustomerName") String customerName,
            @Field("ContactName") String contactName,
            @Field("Address") String address,
            @Field("City") String city,
            @Field("PostalCode") String postalCode,
            @Field("Country") String country,
            @Field("Phone") String phone
    );

    @FormUrlEncoded
    @POST("CustomerAPI/updateCustomer.php")
    Call<ServerResponse> updateCustomer(
            @Field("CustomerID") int customerID,
            @Field("CustomerName") String customerName,
            @Field("ContactName") String contactName,
            @Field("Address") String address,
            @Field("City") String city,
            @Field("PostalCode") String postalCode,
            @Field("Country") String country,
            @Field("Phone") String phone
    );

    @FormUrlEncoded
    @POST("CustomerAPI/deleteCustomer.php")
    Call<ServerResponse> deleteCustomer(
            @Field("CustomerID") int customerID
    );

    @GET("CustomerAPI/getCustomers.php")
    Call<ServerResponseCustomer> getCustomers();

    @GET("CustomerAPI/selectCustomer.php")
    Call<Customer> getCustomerDetails(@Query("CustomerID") int customerID);

    @FormUrlEncoded
    @POST("ProductAPI/insertProduct.php")
    Call<ServerResponse> insertProduct(
            @Field("ProductName") String productName,
            @Field("SupplierID") int supplierID,
            @Field("CategoryID") int categoryID,
            @Field("UnitPrice") double unitPrice,
            @Field("UnitsInStock") int unitsInStock
    );

    @FormUrlEncoded
    @POST("ProductAPI/updateProduct.php")
    Call<ServerResponse> updateProduct(
            @Field("ProductID") int productID,
            @Field("ProductName") String productName,
            @Field("SupplierID") int supplierID,
            @Field("CategoryID") int categoryID,
            @Field("UnitPrice") double unitPrice,
            @Field("UnitsInStock") int unitsInStock
    );

    @FormUrlEncoded
    @POST("ProductAPI/deleteProduct.php")
    Call<ServerResponse> deleteProduct(
            @Field("ProductID") int productID
    );

    @GET("ProductAPI/getProducts.php")
    Call<ServerResponseProduct> getProducts();

    @GET("ProductAPI/selectProduct.php")
    Call<Product> getProductDetails(@Query("ProductID") int productID);
    @POST("login.php")
    Call<ServerResponseLogin> login(@Body Login login);

    @FormUrlEncoded
    @POST("sendResetCode.php")
    Call<ServerResponseLogin> sendResetCode(@Field("email") String email);

    @FormUrlEncoded
    @POST("verifyResetCode.php")
    Call<ServerResponseLogin> verifyResetCode(@Field("code") String code);

    @POST("updatePassword.php")
    Call<ServerResponseLogin> updatePassword(@Body Account account);
}
