package com.example.flipkart.controller;

import com.example.flipkart.model.Product;
import com.example.flipkart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class AddproductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?>addproduct (@RequestParam("image") MultipartFile file,
                                        @RequestParam("name") String name,
                                        @RequestParam("price") double price,
                                        @RequestParam("description") String description){
        try {
            return new ResponseEntity(productService.addproduct(file,name,price,description), HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
