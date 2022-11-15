package com.kakaopay.restapi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kakaopay.restapi.model.Category;

@Mapper
public interface CategoryMapper {
    @Select("SELECT categoryId, categoryName FROM category WHERE categoryId = #{categoryId}")
    Category getCategory(@Param("categoryId") Integer categoryId);

    @Select("SELECT categoryId, categoryName FROM category")
    List<Category> getCategoryAll();

    @Insert("INSERT INTO category (categoryName) VALUES (#{categoryName})")
    int insertCategory(@Param("categoryName") String categoryName);

    @Select("SELECT categoryId, CategoryName FROM category WHERE categoryName = #{categoryName}")
    Category getCategoryByCategoryName(@Param("categoryName") String categoryName);
}
