package com.kakaopay.restapi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kakaopay.restapi.model.Membership;
import com.kakaopay.restapi.model.MembershipHistory;

@Mapper
public interface MembershipMapper {
    
    @Select("SELECT user.userId, membership.membershipId, membership.categoryId, membership.point FROM user LEFT JOIN membership ON user.membershipId = membership.membershipId WHERE userId=#{userId}")
    Membership getMembership(@Param("userId") Integer userId);
    
    @Select("SELECT DISTINCT m.membershipId FROM user u LEFT JOIN membership m ON u.membershipId = m.membershipId WHERE u.userId=#{userId}")
    String getMembershipIdByUserId(@Param("userId") Integer userId);

    @Select("SELECT DISTINCT user.userId, membership.membershipId, membership.categoryId, membership.point FROM user LEFT JOIN membership ON user.membershipId = membership.membershipId")
    List<Membership> getMembershipAll();

    @Insert("INSERT INTO membership (membershipId, categoryId) VALUES (#{membershipId}, #{caregoryId})")
    int insertMembership(@Param("membershipId") String membershipId, @Param("caregoryId") Integer categoryId);
    
    @Update("UPDATE user SET membershipId=#{membershipId} WHERE userId=#{userId}")
    int updateUser(@Param("userId") Integer userId, @Param("membershipId") String membershipId);

    @Insert("INSERT INTO history (type, membershipId, partnerId, point, createdAt) VALUES(#{type}, #{membershipId}, #{partnerId}, #{point}, #{createdAt})")
    int insertHistory(@Param("membershipId") String membershipId, @Param("partnerId") Integer partnerId, @Param("type") String type, @Param("point") Integer point, @Param("createdAt") String createdAt);

    @Update("UPDATE membership " +
            "SET point = point + #{point} " +
            "WHERE membershipId = #{membershipId} " +
            "AND categoryId = #{categoryId}")
    int updateMembership(@Param("membershipId") String membershipId, @Param("categoryId") Integer categoryId, @Param("point") Integer point);

    @Update("UPDATE history SET approvedAt=#{approvedAt} WHERE membershipId=#{membershipId} AND partnerId=#{partnerId} AND createdAt=#{createdAt}")
    int updateHistory(@Param("membershipId") String membershipId, @Param("partnerId") Integer partnerId,  @Param("createdAt") String createdAt, @Param("approvedAt") String approvedAt);

    @Select("SELECT DISTINCT h.approvedAt, h.type, pc.categoryName as category, pc.partnerName as partner_name " +
            "FROM history h " +
            "LEFT JOIN membership m " +
            "  ON h.membershipId = m.membershipId " +
            "LEFT JOIN ( " +
                "SELECT p.*, c.categoryName " +
                "  FROM partner p" +
                "  LEFT JOIN category c" +
                "    ON p.categoryId = c.categoryId) pc " + 
            "ON h.partnerId = pc.partnerId " +
            "WHERE h.membershipId = #{membershipId} " +
            "AND h.approvedAt >= #{startAt} " +
            "AND h.approvedAt <= #{endAt}")
    List<MembershipHistory> getMembershipHistory(@Param("membershipId") String membershipId, @Param("startAt") String startAt, @Param("endAt") String endAt);
}
