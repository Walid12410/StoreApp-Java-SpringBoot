package com.example.ecommerce.storeApp.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "product")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_desc")
    private String productDesc;

    @Column(name = "product_price")
    private String price;

    @Column(name = "product_stock")
    private String stock;

    @Column(name = "product_image_url")
    private String imageUrl;

    @Column(name = "product_image_id")
    private String imageId;

    @OneToOne(mappedBy = "subCategory", cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_category_id", nullable = false)
    private SubCategory subCategory;
}
