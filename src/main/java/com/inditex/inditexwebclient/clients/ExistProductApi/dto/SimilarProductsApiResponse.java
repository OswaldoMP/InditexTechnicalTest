package com.inditex.inditexwebclient.clients.ExistProductApi.dto;

import lombok.Data;

import java.util.List;

@Data
public class SimilarProductsApiResponse {
    private List<Integer> ids;
}
