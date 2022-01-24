package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Transaction tr = null;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()){
            tr = session.beginTransaction();
            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                    "name VARCHAR(25) NOT NULL, " +
                    "lastname VARCHAR(25) NOT NULL, " +
                    "age TINYINT NOT NULL);").addEntity(User.class);
            query.executeUpdate();
            tr.commit();
        } catch (Exception e){
            if (tr != null){
                tr.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()){
            tr = session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS users").addEntity(User.class);
            query.executeUpdate();
            tr.commit();
        } catch (Exception e){
            if (tr != null){
                tr.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User();
        try (Session session = Util.getSessionFactory().openSession()){
            tr = session.beginTransaction();

            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            System.out.println("User с именем - " + name + " добавлен в базу данных.");

            tr.commit();
        } catch (Exception e){
            if (tr != null){
                tr.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()){
            tr = session.beginTransaction();
            session.createQuery("DELETE FROM User WHERE id=id").executeUpdate();
            tr.commit();
        } catch (Exception e){
            if (tr != null){
                tr.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()){
            tr = session.beginTransaction();
            users = session.createQuery("FROM User").list();
            tr.commit();
        } catch (Exception e){
            if (tr != null){
                tr.rollback();
            }
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()){
            tr = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User");
            query.executeUpdate();
            tr.commit();
        } catch (Exception e){
            if (tr != null){
                tr.rollback();
            }
        }
    }
}
