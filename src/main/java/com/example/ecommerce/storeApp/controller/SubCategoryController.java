package com.example.ecommerce.storeApp.controller;


import com.example.ecommerce.storeApp.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subCategory")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;


}
