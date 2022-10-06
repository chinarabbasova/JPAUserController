package org.example.dao.inter;

import org.example.entity.Skill;

import java.util.List;

public interface SkillDaoInter {
    List<Skill> getAllSkills();
    public boolean updateSkill(Skill s);
    public Skill getById(int id);
}
