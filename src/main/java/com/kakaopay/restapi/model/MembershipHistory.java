package com.kakaopay.restapi.model;

public class MembershipHistory {
    private String approvedAt;
    private String type;
    private String category;
    private String partner_name;

    public MembershipHistory(String approvedAt, String type, String category, String partner_name) {
        super();
        this.approvedAt = approvedAt;
        this.type = type;
        this.category = category;
        this.partner_name = partner_name;
    }

    public String getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(String approvedAt) {
        this.approvedAt = approvedAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPartnerName() {
        return partner_name;
    }

    public void setPartnerName(String partner_name) {
        this.partner_name = partner_name;
    }
    
}
