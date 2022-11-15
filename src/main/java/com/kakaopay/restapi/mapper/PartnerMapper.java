package com.kakaopay.restapi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kakaopay.restapi.model.Partner;

@Mapper
public interface PartnerMapper {
    @Select("SELECT partnerId, partnerName, categoryId FROM partner WHERE partnerId = #{partnerId}")
    Partner getPartner(@Param("partnerId") Integer partnerId);

    @Select("SELECT partnerId, partnerName, categoryId FROM partner")
    List<Partner> getPartnerAll();

    @Insert("INSERT INTO partner (partnerName, categoryId) VALUES (#{partnerName}, #{categoryId})")
    int insertPartner(@Param("partnerName") String partnerName, @Param("categoryId") Integer categoryId);

    @Select("SELECT p.categoryId " +
            "  FROM partner p " +
            " WHERE p.partnerId = #{partnerId}")
    int getCategoryIdByPartnerId(@Param("partnerId") Integer partnerId);
}
