package com.kakaopay.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kakaopay.restapi.common.SHA256;
import com.kakaopay.restapi.mapper.CategoryMapper;
import com.kakaopay.restapi.mapper.MembershipMapper;
import com.kakaopay.restapi.mapper.PartnerMapper;
import com.kakaopay.restapi.model.Category;
import com.kakaopay.restapi.model.Membership;
import com.kakaopay.restapi.model.MembershipHistory;

@RestController
public class MembershipController {

    private MembershipMapper membershipMapper;
    private CategoryMapper categoryMapper;
    private PartnerMapper partnerMapper;
    private String salt = "plzHireMe";

    public MembershipController(MembershipMapper membershipMapper, CategoryMapper categoryMapper, PartnerMapper partnerMapper) {
        this.membershipMapper = membershipMapper;
        this.categoryMapper = categoryMapper;
        this.partnerMapper = partnerMapper;
    }

    private String createMembershipId(Integer userId) throws NoSuchAlgorithmException {
        String salted = new StringBuilder(String.valueOf(userId)).append(salt).toString();
        SHA256 sha256 = new SHA256();
        String hashed = sha256.encrypt(salted);
        
        StringBuilder tmp = new StringBuilder("");
        System.out.println(tmp.toString());
        for (int i = 0; i < 4; i++) {
            String ascii = String.format("%03d", (int)hashed.charAt(i));
            tmp.append(ascii);
        }

        return tmp.toString().substring(0, 10);
    }

    // @GetMapping("/membership/{userId}")
    // public Membership getMembership(@PathVariable("userId") Integer userId) {
    //     return membershipMapper.getMembership(userId);
    // }

    // @GetMapping("/membership/all")
    // public List<Membership> getUserList() {
    //     return membershipMapper.getMembershipAll();
    // }

    // create membership api
    @PutMapping("/membership/{userId}")
    public String putUser(@PathVariable("userId") Integer userId) throws NoSuchAlgorithmException {

        String membershipiId = createMembershipId(userId);

        if (membershipiId.equals(membershipMapper.getMembershipIdByUserId(userId))) {
            return membershipiId;
        }

        List<Category> categories = categoryMapper.getCategoryAll();
        List<Integer> categoryIds =  new ArrayList<>();

        categories.stream()
            .forEach(category -> categoryIds.add(category.getCategoryId()));

        int resp = 0;
        for( Integer categoryId : categoryIds) {
            resp += membershipMapper.insertMembership(membershipiId, categoryId);
        }

        if (resp == 3) {
            System.out.print("create membership success \n");
            resp += membershipMapper.updateUser(userId, membershipiId);
        }

        if (resp == 4) {
            System.out.print("update user success \n");
        }

        return membershipMapper.getMembershipIdByUserId(userId);
    }

    // earn point api
    // use point api
    @PostMapping("/membership/point/{membershipId}")
    public void postAccumulatedPoint(
        @PathVariable("membershipId") String membershipId, 
        @RequestParam("partnerId") Integer partnerId, 
        @RequestParam("type") String type,
        @RequestParam("point") Integer point) {
            
            if ( type.equals("earn")) {
                System.out.print("use +point \n");
            } else {
                System.out.print("use -point \n");
                point = (-1) * point;
            }

            System.out.print("type : _");
            System.out.print(type);
            System.out.print("_\n");
            System.out.print("point : ");
            System.out.print(point);
            System.out.print("\n");

            // validate if calculated point < 0 


            String createdAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
            int resp = membershipMapper.insertHistory(membershipId, partnerId, type, point, createdAt);

            if (resp == 1) {
                System.out.print("insert history success \n");
                
                // point = (type == "earn") ? point : (-1) * point;
                Integer categoryId = partnerMapper.getCategoryIdByPartnerId(partnerId);

                resp = membershipMapper.updateMembership(membershipId, categoryId, point);
            }

            if (resp == 1) {
                System.out.print("update membership success \n");
                String approvedAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                resp = membershipMapper.updateHistory(membershipId, partnerId, createdAt, approvedAt);
            }

            if (resp == 1) {
                // sucess
                System.out.print("update history success \n");
            }
    }


    @GetMapping("/membership/history/{membershipId}")
    public HashMap<String, List<MembershipHistory>> getMembershipHistory(
        @PathVariable("membershipId") String membershipId, 
        @RequestParam("startAt") String startAt, 
        @RequestParam("endAt") String endAt) {
        List<MembershipHistory> membershipHistories = membershipMapper.getMembershipHistory(membershipId, startAt, endAt);

        return new HashMap<String, List<MembershipHistory>>(){{
            put("history", membershipHistories);
        }};
    }
}
