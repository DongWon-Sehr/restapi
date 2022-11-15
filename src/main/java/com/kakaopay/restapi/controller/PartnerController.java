package com.kakaopay.restapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kakaopay.restapi.mapper.CategoryMapper;
import com.kakaopay.restapi.mapper.PartnerMapper;
import com.kakaopay.restapi.model.Category;
import com.kakaopay.restapi.model.Partner;

@RestController
public class PartnerController {
    private PartnerMapper partnerMapper;
    private CategoryMapper categoryMapper;

    public PartnerController(PartnerMapper partnerMapper, CategoryMapper categoryMapper) {
        this.partnerMapper = partnerMapper;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping("/partner/{partnerId}")
    public Partner getPartner(@PathVariable("partnerId") Integer partnerId) {
        return partnerMapper.getPartner(partnerId);
    }

    @GetMapping("/partner/all")
    public List<Partner> getPartnerList() {
        return partnerMapper.getPartnerAll();
    }

    @PutMapping("/partner")
    public void putPartner(
        @RequestParam("partnerName") String partnerName,
        @RequestParam("categoryName") String categoryName
    ) {

        Integer categoryId = categoryMapper.getCategoryByCategoryName(categoryName).getCategoryId();
        Category category = categoryMapper.getCategory(categoryId);
        String _categoryName = category.getCategoryName();
        System.out.print("categoryId : ");
        System.out.print(categoryId);
        System.out.print("\n");
        System.out.print("_categoryName : ");
        System.out.print(_categoryName);
        System.out.print("\n");
        System.out.print("categoryName : ");
        System.out.print(categoryName);
        System.out.print("\n");

        int resp = 0;
        if (categoryName == _categoryName) {
            System.out.print("select categoryId success \n");
            resp = partnerMapper.insertPartner(partnerName, categoryId);
            System.out.print(resp);
        }

        if ( resp == 1) {
            System.out.print("insert partner success \n");
        }
    }
}
