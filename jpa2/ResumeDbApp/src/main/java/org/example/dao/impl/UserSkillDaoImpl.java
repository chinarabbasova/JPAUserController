package org.example.dao.impl;

import org.example.dao.inter.AbstractDAO;
import org.example.dao.inter.UserSkillDaoInter;
import org.example.entity.UserSkill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {
//    private UserSkill getUserSkill(ResultSet rs) throws SQLException {
//        Integer userSkillId = rs.getInt("userSkillId");
//        Integer skillId = rs.getInt("skill_id");
//        int userId = rs.getInt("id");
//        String skillName = rs.getString("skill_name");
//        int power = rs.getInt("power");
//
//        return new UserSkill(userSkillId, new User(userId), new Skill(skillId, skillName), power);
//    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> result = new ArrayList<>();

        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select  u.* ,us.id as userSkillId, us.skill_id, s.name as skill_name,us.power  from user_skill us\n" +
                    "left join user u on us.user_id = u.id\n" +
                    "left join skill s on us.skill_id = s.id\n" +
                    "where us.user_id = ?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
            //    UserSkill us = getUserSkill(rs);
              //  result.add(us);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}



