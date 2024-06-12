package com.infjz.prm392.slot11;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IProduct {
    @FormUrlEncoded
    @POST("create_product.php")
    Call<ServerResponseProduct> insertProduct(
            @Field("name") String name,
            @Field("price") Double price,
            @Field("description") String description);
}
