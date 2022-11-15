package com.kakaopay.restapi.model;

public class Partner {

    private Integer partnerId;
    private String partnerName;
    private Integer categoryId;

    public Partner(Integer partnerId, String partnerName, Integer categoryId) {
        super();
        this.partnerId = partnerId;
        this.partnerName = partnerName;
        this.categoryId = categoryId;
    }

    public Integer getPartnerId() {
        return partnerId;
    }
    
    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}

