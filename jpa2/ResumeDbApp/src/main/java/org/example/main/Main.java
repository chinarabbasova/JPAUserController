package org.example.main;

import org.example.dao.inter.UserDaoInter;
import org.example.entity.User;

public class Main {
    public static void main(String[] args) {
        UserDaoInter dao = Context.instanceUserDao();
        User u =dao.findByEmail("salam2@mail.com");
        System.out.println("u= "+ u);

//        User u =dao.findByEmailAndPassword("salam@mail.com","12345");
//        System.out.println("u= "+ u);



//        User u= dao.getById(10);
//        System.out.println("nationality "+u.getNationality().getName());
//        System.out.println(u.getEmail());

//        dao.removeUser(5);

    }
}
