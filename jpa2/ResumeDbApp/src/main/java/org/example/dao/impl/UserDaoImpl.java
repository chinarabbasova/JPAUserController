package org.example.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.example.dao.inter.AbstractDAO;
import org.example.dao.inter.UserDaoInter;
import org.example.entity.User;

import java.sql.*;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserDaoImpl extends AbstractDAO implements UserDaoInter {
    @Override
    public List<User> getAllUser(String name, String surname, Integer nationalityId, String address, String email, String phone, String profileDescription) {
      EntityManager em = em();
      String jpql = "select u from User u where 1=1";

            if (name != null && !(name.trim().isEmpty())) {
                jpql += " and u.name =: name ";
            }
            if (surname != null && !(surname.trim().isEmpty())) {
                jpql += "and u.surname =: surname ";
            }
            if (nationalityId != null) {
                jpql += "and u.nationality.id =:nid ";
            }
            if (address != null) {
                jpql += "and u.address =:address";
            }
            if (email != null) {
                jpql += "and u.email = :email";
            }
            if (phone != null) {
                jpql += "and u.phone = :phone";
            }
            if (profileDescription != null) {
                jpql += "and u.profileDesc = : profileDesc";
            }

            Query query=em.createQuery(jpql,User.class);
            if (name != null && !(name.trim().isEmpty())) {
               query.setParameter("name",name);
            }
            if (surname != null && !(surname.trim().isEmpty())) {
                query.setParameter("surname",surname);

            }
            if (nationalityId != null) {
                query.setParameter("nid",nationalityId);

            }
            if (address != null && !(address.trim().isEmpty())) {
                query.setParameter("address",address);

            }
            if (email != null && !(email.trim().isEmpty())) {
                query.setParameter("email",email);

            }
            if (phone != null && !(phone.trim().isEmpty())) {
                query.setParameter("phone",phone);

            }
            if (profileDescription != null && !(profileDescription.trim().isEmpty())) {
                query.setParameter("profileDesc",profileDescription);

            }
         return query.getResultList();
    }

//    public List<User> getAllUser(String name, String surname, String address, String email, String password, String phone, String profileDescription) {
//        List<User> result = new ArrayList<>();
//
//        try (Connection c = connect()) {
//            String sql = "select u.*, n.nationality, c.name as birthplace from \"user\" u left join country n on u.nationality_id = n.id  left join country c on u.birthplace_id = c.id where 1 = 1 ";
//            if (name != null && !(name.trim().isEmpty())) {
//                sql += " and u.name = ? ";
//            }
//            if (surname != null && !(surname.trim().isEmpty())) {
//                sql += "and u.surname = ? ";
//            }
//
//            if (address != null) {
//                sql += "and u.address = ?";
//            }
//            if (email != null) {
//                sql += "and u.email = ?";
//            }
//            if (email != null) {
//                sql += "and u.email = ?";
//            }
//            if (phone != null) {
//                sql += "and u.phone = ?";
//            }
//            if (profileDescription != null) {
//                sql += "and u.profile_description = ?";
//            }
//            PreparedStatement stmt = c.prepareStatement(sql);
//
//            int i = 1;//i indeksi bildirir
//            if (name != null && !(name.trim().isEmpty())) {
//                stmt.setString(i, name);
//                i++;
//            }
//            if (surname != null && !(surname.trim().isEmpty())) {
//                stmt.setString(i, surname);
//                i++;
//            }
//            if (nationalityId != null) {
//                stmt.setInt(i, nationalityId);
//                i++;
//            }
//            if (address != null && !(address.trim().isEmpty())) {
//                stmt.setString(i, address);
//                i++;
//            }
//            if (email != null && !(email.trim().isEmpty())) {
//                stmt.setString(i, email);
//                i++;
//            }
//            if (phone != null && !(phone.trim().isEmpty())) {
//                stmt.setString(i, phone);
//                i++;
//            }
//            if (profileDescription != null && !(profileDescription.trim().isEmpty())) {
//                stmt.setString(i, profileDescription);
//            }
//            stmt.execute();
//            ResultSet rs = stmt.getResultSet();
//
//            while (rs.next()) {
//                User u = getUser(rs);
//                result.add(u);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

//    @Override
//    public User findByEmailAndPassword(String email, String password) {
//       EntityManager em = em();
//       Query q = em.createQuery("select u from User u where u.email=:e and u.password=:p",User.class);
//       q.setParameter("e",email);
//       q.setParameter("p",password);
//
//       List<User> list = q.getResultList();
//        if(list.size()==1){
//            return list.get(0);
//        }
//        return null;
//    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        EntityManager em = em();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> q1 = cb.createQuery(User.class);
        Root<User> postRoot = q1.from(User.class);
        CriteriaQuery<User>q2 = q1.where(cb.equal(postRoot.get("email"),email),cb.equal(postRoot.get("password"),password));

        Query q = em.createQuery(q2);
        List<User> list = q.getResultList();
        if(list.size()==1){
            return list.get(0);
        }
        return null;
    }


    //SQL-nativequery
    @Override
    public User findByEmail(String email) {
        EntityManager em = em();
        Query query= em.createNativeQuery("select * from \"user\" where email=?",User.class);

        query.setParameter(1,email);
        List<User> list = query.getResultList();
        if(list.size()==1){
            return list.get(0);
        }
        return null;
    }

//    //NamedQuery
//    @Override
//    public User findByEmail(String email) {
//        EntityManager em = em();
//        Query query= em.createNamedQuery("User.findByEmail",User.class);
//
//       query.setParameter("email",email);
//        List<User> list = query.getResultList();
//        if(list.size()==1){
//            return list.get(0);
//        }
//        return null;
//    }


    //With Criteria Builder
//    @Override
//    public User findByEmail(String email) {
//        EntityManager em = em();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<User> q1 = cb.createQuery(User.class);
//        Root<User> postRoot = q1.from(User.class);
//        CriteriaQuery<User>q2 = q1.where(cb.equal(postRoot.get("email"),email));
//
//        Query q = em.createQuery(q2);
//        List<User> list = q.getResultList();
//        if(list.size()==1){
//            return list.get(0);
//        }
//        return null;
//    }


       //WithJPQL
//    @Override
//    public User findByEmail(String email) {
//        EntityManager em = em();
//        Query q = em.createQuery("select u from User u where u.email=:e ",User.class);
//        q.setParameter("e",email);
//
//        List<User> list = q.getResultList();
//        if(list.size()==1){
//            return list.get(0);
//        }
//        return null;
//    }


//    @Override
//    public User findByEmailAndPassword(String email, String password) {
//        User result = null;
//        try (Connection c = connect()) {
//            PreparedStatement stmt = c.prepareStatement("select * from \"user\" where email=? and password=?");
//            stmt.setString(1, email);
//            stmt.setString(2, password);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                //  result = getUserSimple(rs);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return result;
//    }

    //withJDBC
//    @Override
//    public User findByEmail(String email) {
//        User result = null;
//        try (Connection c = connect()) {
//            PreparedStatement stmt = c.prepareStatement("select * from \"user\" where email=? ");
//            stmt.setString(1, email);
//
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                //    result = getUserSimple(rs);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return result;
//    }

    @Override
    public User findByName(String name) {
        User result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select  * from \"user\" where name = ?");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                //       result = getUserSimple(rs);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User getById(int id) {
        EntityManager em = em();
        User u = em.find(User.class, id);
        em.close();


        return u;
    }

    @Override
    public boolean removeUser(int id) {
        EntityManager em = em();
        User u = em.find(User.class, id);
        em.getTransaction().begin();  //transaction aciriq
        em.remove(u);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public boolean updateUser(User u) {
        EntityManager em = em();
        em.getTransaction().begin();  //transaction aciriq
        em.merge(u);
        em.getTransaction().commit();

        em.close();

        return true;
    }

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {
        u.setPassword(crypt.hashToString(4, u.getPassword().toCharArray()));
        EntityManager em = em();
        em.getTransaction().begin();  //transaction aciriq
        em.persist(u);
        em.getTransaction().commit();
        em.close();
        return true;
    }

  

}



