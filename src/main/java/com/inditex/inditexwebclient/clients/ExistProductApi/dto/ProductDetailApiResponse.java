package com.inditex.inditexwebclient.clients.ExistProductApi.dto;

import lombok.Data;

@Data
public class ProductDetailApiResponse {
    private String id;
    private String name;
    private float price;
    private boolean availability;
}
