package com.example.ecommerce.storeApp.service;


import com.example.ecommerce.storeApp.model.dto.SubCategoryDTO;
import com.example.ecommerce.storeApp.model.entity.SubCategory;
import com.example.ecommerce.storeApp.model.mapper.SubCategoryMapper;
import com.example.ecommerce.storeApp.repository.SubCatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public SubCategoryDTO saveSubCategory(SubCategoryDTO subCategoryDTO){
        SubCategory subCategory = SubCategoryMapper.toEntity(subCategoryDTO);

        SubCategory savedSubCategory = this.subCatRepo.save(subCategory);

        return SubCategoryMapper.toDto(savedSubCategory);
    }


    public SubCategoryDTO updateSubCategory(Integer id,SubCategoryDTO subCategoryDTO){
        SubCategory existSubCategory = this.subCatRepo.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sub Category not found with ID :" + id));

        existSubCategory.setSubCategoryName(subCategoryDTO.getSubCategoryName());

        SubCategory updateSubCategory = this.subCatRepo.save(existSubCategory);

        return SubCategoryMapper.toDto(updateSubCategory);
    }


    public void deleteSubCategory(Integer id){
        SubCategory existSubCategory = this.subCatRepo.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sub Category not found with ID :" + id));

        this.subCatRepo.delete(existSubCategory);
    }

}
