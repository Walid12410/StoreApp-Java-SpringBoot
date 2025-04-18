package com.example.ecommerce.storeApp.modular.subCategory;


import com.example.ecommerce.storeApp.modular.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name = "sub_category")
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subCategory_id")
    private Integer id;

    @Column(name = "subCategory_name")
    private String subCategoryName;


    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
