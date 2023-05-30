package com.inditex.inditexwebclient.services;

import com.google.gson.Gson;
import com.inditex.inditexwebclient.clients.ExistProductApi.ExistingApis;
import com.inditex.inditexwebclient.clients.ExistProductApi.dto.ProductDetailApiResponse;
import com.inditex.inditexwebclient.clients.ExistProductApi.dto.SimilarProductsApiResponse;
import com.inditex.inditexwebclient.dto.ProductsResponse;
import com.inditex.inditexwebclient.errors.ApiResponseError;
import com.inditex.inditexwebclient.interfaces.IProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductService implements IProduct {

    @Autowired
    private ExistingApis existingApis;

    private Gson gson = new Gson();

    @Override
    public List<ProductsResponse> productSimilar(int productId) {

        List<Integer> similarIds = this.getSimilarIds(productId);

        List<ProductDetailApiResponse> productDetails = similarIds
                .stream().map(this::getProductDetail).collect(Collectors.toList());


        List<ProductsResponse> productsResponse = productDetails
                .stream().map(this::buildResponse).collect(Collectors.toList());

        log.info(gson.toJson(productsResponse));

        return productsResponse;
    }

    private List<Integer> getSimilarIds(int productId) {
        try {
            Response<List<Integer>> response = existingApis.getSimilarIdsByProductId(productId).execute();

            this.validateCodeStatusResponse(response.code());

            if (response.isSuccessful()) {
                log.info("API RESPONSE: {}", gson.toJson(response.body()));
                return response.body();
            }
            return null;
        } catch (IOException e) {
            throw new ApiResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Problems with API, Try again");
        }
    }

    private ProductDetailApiResponse getProductDetail(int productId) {
        try {
            Response<ProductDetailApiResponse> productDetailResponse = existingApis.getProductDetailById(productId)
                    .execute();

            this.validateCodeStatusResponse(productDetailResponse.code());

            if (productDetailResponse.isSuccessful()) {
                log.info("API RESPONSE: {}", gson.toJson(productDetailResponse.body()));
                return productDetailResponse.body();
            }
            return null;
        } catch (IOException e) {
            throw new ApiResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Problems with API, Try again");
        }
    }

    private void validateCodeStatusResponse(int codeStatus) {
        HttpStatus httpStatus = HttpStatus.valueOf(codeStatus);
        switch (httpStatus) {
            case NOT_FOUND:
                throw new ApiResponseError(HttpStatus.NOT_FOUND.value(), "Item didn't find");
            case INTERNAL_SERVER_ERROR:
                throw new ApiResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Problem whit the api client");
        }
    }

    private ProductsResponse buildResponse(ProductDetailApiResponse apiResponse) {
        return gson.fromJson(gson.toJson(apiResponse), ProductsResponse.class);
    }
}
