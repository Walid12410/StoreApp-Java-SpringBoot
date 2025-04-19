package com.example.ecommerce.storeApp.modular.subCategory;


import com.example.ecommerce.storeApp.modular.category.CategoryRepository;
import com.example.ecommerce.storeApp.modular.category.Category;
import com.example.ecommerce.storeApp.modular.subCategory.dto.SubCategoryCreateDTO;
import com.example.ecommerce.storeApp.modular.subCategory.dto.SubCategoryResponseDTO;
import com.example.ecommerce.storeApp.modular.subCategory.dto.SubCategoryUpdateDTO;
import com.example.ecommerce.storeApp.modular.subCategory.mapper.SubCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public SubCategoryService(SubCategoryRepository subCategoryRepository, CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    public List<SubCategoryResponseDTO> getAllSubCategory(){
        List<SubCategory> subCategories = this.subCategoryRepository.findAll();

        if(subCategories.isEmpty()){
            return Collections.emptyList();
        }

        return subCategories.stream()
                .map(SubCategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public SubCategoryResponseDTO saveSubCategory(SubCategoryCreateDTO subCategoryCreateDTO){
        Category exists = this.categoryRepository.findById(subCategoryCreateDTO.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        SubCategory subCategory = SubCategoryMapper.toEntity(subCategoryCreateDTO);
        subCategory.setCategory(exists);

        SubCategory savedSubCategory = this.subCategoryRepository.save(subCategory);

        return SubCategoryMapper.toDto(savedSubCategory);
    }


    public SubCategoryResponseDTO updateSubCategory(Integer id, SubCategoryUpdateDTO subCategoryUpdateDTO){
        SubCategory existSubCategory = this.subCategoryRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sub Category not found with ID :" + id));

        existSubCategory.setSubCategoryName(subCategoryUpdateDTO.getSubCategoryName());

        if(subCategoryUpdateDTO.getId() != null){
            Category existCategory = this.categoryRepository.findById(subCategoryUpdateDTO.getCategoryId())
                    .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with ID"));

            existSubCategory.setCategory(existCategory);
        }

        SubCategory updateSubCategory = this.subCategoryRepository.save(existSubCategory);

        return SubCategoryMapper.toDto(updateSubCategory);
    }


    public void deleteSubCategory(Integer id){
        SubCategory existSubCategory = this.subCategoryRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sub Category not found with ID :" + id));

        // TODO: Check if subCategory used in product before deleting it
        this.subCategoryRepository.delete(existSubCategory);
    }

}
