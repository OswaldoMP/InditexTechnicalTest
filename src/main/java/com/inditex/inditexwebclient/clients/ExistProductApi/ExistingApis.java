package com.inditex.inditexwebclient.clients.ExistProductApi;

import com.inditex.inditexwebclient.clients.ExistProductApi.dto.ProductDetailApiResponse;
import com.inditex.inditexwebclient.clients.ExistProductApi.dto.SimilarProductsApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface ExistingApis {
    @GET("product/{productId}/similarids")
    Call<List<Integer>> getSimilarIdsByProductId(@Path("productId") int productId);

    @GET("product/{productId}")
    Call<ProductDetailApiResponse> getProductDetailById(@Path("productId") int productId);
}
