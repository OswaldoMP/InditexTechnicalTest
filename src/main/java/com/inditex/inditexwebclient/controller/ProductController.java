package com.inditex.inditexwebclient.controller;

import com.inditex.inditexwebclient.dto.ProductsResponse;
import com.inditex.inditexwebclient.interfaces.IProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private IProduct iProduct;

    @GetMapping("{productId}/similar")
    public ResponseEntity<List<ProductsResponse>> getProductSimilar(
            @Pattern(regexp = "\\d{10}", message = "productId must be digit")
            @PathVariable("productId") int productId
    ) {
        List<ProductsResponse> objects = iProduct.productSimilar(productId);
        return ResponseEntity.ok(objects);
    }
}
