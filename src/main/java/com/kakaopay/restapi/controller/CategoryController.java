package com.kakaopay.restapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kakaopay.restapi.mapper.CategoryMapper;
import com.kakaopay.restapi.model.Category;

@RestController
public class CategoryController {
    private CategoryMapper categoryMapper;

    public CategoryController(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @GetMapping("/category/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") Integer categoryId) {
        return categoryMapper.getCategory(categoryId);
    }

    @GetMapping("/category/all")
    public List<Category> getCategoryList() {
        return categoryMapper.getCategoryAll();
    }

    @PutMapping("/category")
    public void putCategory(@RequestParam("categoryName") String categoryName) {

        System.out.print("categoryName : ");
        System.out.print(categoryName);
        System.out.print("\n");
        int resp = categoryMapper.insertCategory(categoryName);
    }
}
