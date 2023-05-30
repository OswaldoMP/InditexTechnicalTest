package com.inditex.inditexwebclient.interfaces;

import com.inditex.inditexwebclient.dto.ProductsResponse;

import java.util.List;

public interface IProduct {
    List<ProductsResponse> productSimilar(int productId);
}
