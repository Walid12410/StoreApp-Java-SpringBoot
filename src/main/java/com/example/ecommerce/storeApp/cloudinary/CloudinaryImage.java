package com.example.ecommerce.storeApp.cloudinary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CloudinaryImage {
    private String imageUrl;
    private String publicId;
}
