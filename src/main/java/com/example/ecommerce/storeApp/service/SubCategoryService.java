package com.example.ecommerce.storeApp.service;


import com.example.ecommerce.storeApp.model.dto.SubCategoryDTO;
import com.example.ecommerce.storeApp.model.entity.SubCategory;
import com.example.ecommerce.storeApp.model.mapper.SubCategoryMapper;
import com.example.ecommerce.storeApp.repository.SubCatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubCategoryService {

    @Autowired
    private SubCatRepo subCatRepo;

    public List<SubCategoryDTO> getAllSubCategory(){
        List<SubCategory> subCategories = this.subCatRepo.findAll();

        if(subCategories.isEmpty()){
            return Collections.emptyList();
        }

        return subCategories.stream()
                .map(SubCategoryMapper::toDto)
                .collect(Collectors.toList());
    }



}
