package com.example.ecommerce.storeApp.modular.product;


import com.example.ecommerce.storeApp.modular.subCategory.SubCategory;
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
    private Double price;

    @Column(name = "product_stock")
    private Integer stock;

    @Column(name = "product_image_url")
    private String imageUrl;

    @Column(name = "product_image_id")
    private String imageId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id", referencedColumnName = "subCategory_id", nullable = false)
    private SubCategory subCategory;
}
