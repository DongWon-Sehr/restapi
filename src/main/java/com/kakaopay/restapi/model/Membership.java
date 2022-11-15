package com.kakaopay.restapi.model;

public class Membership {
    private Integer userId;
    private String membershipId;
    private Integer categoryId;
    private Integer point;

    public Membership(Integer userId, String membershipId, Integer categoryId, Integer point) {
        super();
        this.userId = userId;
        this.membershipId = membershipId;
        this.categoryId = categoryId;
        this.point = point;
    }

    public Integer getId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }
    
    public Integer getPoint() {
        return point;
    }

    public void setMembershipId(Integer point) {
        this.point = point;
    }
}
