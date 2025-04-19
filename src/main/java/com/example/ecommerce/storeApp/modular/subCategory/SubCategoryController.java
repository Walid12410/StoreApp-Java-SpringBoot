package com.example.ecommerce.storeApp.modular.subCategory;


import com.example.ecommerce.storeApp.modular.subCategory.dto.SubCategoryCreateDTO;
import com.example.ecommerce.storeApp.modular.subCategory.dto.SubCategoryResponseDTO;
import com.example.ecommerce.storeApp.modular.subCategory.dto.SubCategoryUpdateDTO;
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
    public ResponseEntity<List<SubCategoryResponseDTO>> getAllSubCategoryController(){
        List<SubCategoryResponseDTO> subCategoryDTOList = this.subCategoryService.getAllSubCategory();

        if(subCategoryDTOList.isEmpty()){
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
        }

        return new ResponseEntity<>(subCategoryDTOList,HttpStatus.OK);
    }


    @PostMapping("/save")
    public ResponseEntity<SubCategoryResponseDTO> saveSubCategoryController(@RequestBody SubCategoryCreateDTO subCategoryCreateDTO){
        SubCategoryResponseDTO saveSubCategory = this.subCategoryService.saveSubCategory(subCategoryCreateDTO);

        return new ResponseEntity<>(saveSubCategory,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubCategoryResponseDTO> updateSubCategoryController(@PathVariable Integer id, @RequestBody SubCategoryUpdateDTO subCategoryUpdateDTO){
        SubCategoryResponseDTO updateSubCategory = this.subCategoryService.updateSubCategory(id,subCategoryUpdateDTO);

        return new ResponseEntity<>(updateSubCategory,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubCategoryController(@PathVariable Integer id){
        this.subCategoryService.deleteSubCategory(id);
        return ResponseEntity.noContent().build();
    }
}
