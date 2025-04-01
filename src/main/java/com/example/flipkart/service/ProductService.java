package com.example.flipkart.service;

import com.example.flipkart.model.Product;
import com.example.flipkart.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;
    private final String UPLOAD_DIR = "C:\\Projects\\flipkart\\src\\main\\resources\\static\\assets\\images\\";

    public String  addproduct(MultipartFile file, String name, double price, String description) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        // Save file to local directory
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Save product details to database
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setImagePath(filePath.toString());

        productRepo.save(product);

        return "Product saved successfully with name: " + name;
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
    }

