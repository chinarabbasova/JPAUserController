package org.example.main;

import org.example.dao.impl.*;
import org.example.dao.inter.*;

public class Context {

        public static UserDaoInter instanceUserDao(){
            return new UserDaoImpl();
        }
        public static UserSkillDaoInter instanceUserSkillDao(){
            return new UserSkillDaoImpl();
        }

        public static EmployementHistoryDaoInter instanceEmploymentHistoryDao(){
            return new EmploymentHistoryDaoImpl();
        }
        public static SkillDaoInter instanceSkillDao(){
            return new SkillDaoImpl();
        }
        public static CountryDaoInter instanceCountryDao(){
            return new CountryDaoImpl();
        }
    }
