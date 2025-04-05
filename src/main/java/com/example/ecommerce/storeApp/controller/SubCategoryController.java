package com.example.ecommerce.storeApp.controller;


import com.example.ecommerce.storeApp.model.dto.SubCategoryDTO;
import com.example.ecommerce.storeApp.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/subCategory")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;


    @GetMapping()
    public ResponseEntity<List<SubCategoryDTO>> getAllSubCategoryController(){
        List<SubCategoryDTO> subCategoryDTOList = this.subCategoryService.getAllSubCategory();

        if(subCategoryDTOList.isEmpty()){
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
        }

        return new ResponseEntity<>(subCategoryDTOList,HttpStatus.OK);
    }


    @PostMapping("/save")
    public ResponseEntity<SubCategoryDTO> saveSubCategoryController(@RequestBody SubCategoryDTO subCategoryDTO){
        SubCategoryDTO saveSubCategory = this.subCategoryService.saveSubCategory(subCategoryDTO);

        return new ResponseEntity<>(saveSubCategory,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubCategoryDTO> updateSubCategoryController(@PathVariable Integer id, @RequestBody SubCategoryDTO subCategoryDTO){
        SubCategoryDTO updateSubCategory = this.subCategoryService.updateSubCategory(id,subCategoryDTO);

        return new ResponseEntity<>(updateSubCategory,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubCategoryController(@PathVariable Integer id){
        this.subCategoryService.deleteSubCategory(id);
        return ResponseEntity.noContent().build();
    }

}
