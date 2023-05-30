package com.inditex.inditexwebclient.dto;

import lombok.Data;

@Data
public class ProductsResponse {
    private String id;
    private String name;
    private float price;
    private boolean availability;
}
