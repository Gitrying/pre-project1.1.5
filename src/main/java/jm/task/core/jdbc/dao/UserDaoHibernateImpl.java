package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    SessionFactory SESSION_FACTORY= getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try(Session session = SESSION_FACTORY.getCurrentSession();) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(
                            "CREATE TABLE IF NOT EXISTS User(" +
                                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                                    "name VARCHAR(30)," +
                                    "lastName VARCHAR(30)," +
                                    "age INTEGER)")
                    .executeUpdate();
            transaction.commit();

        }catch (HibernateException e){
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {
        try(Session session = SESSION_FACTORY.getCurrentSession();) {

            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS User").executeUpdate();
            transaction.commit();

        }catch (HibernateException e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try(Session session = SESSION_FACTORY.getCurrentSession();){
            transaction = session.beginTransaction();
            session.save(new User(name,lastName,age));
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try(Session session = SESSION_FACTORY.getCurrentSession();){
            transaction = session.beginTransaction();
            User user=session.get(User.class,id);
            session.delete(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null){
                transaction.rollback();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> users= null;
        try(Session session = SESSION_FACTORY.getCurrentSession();){
            transaction = session.beginTransaction();
            users=session.createQuery("FROM User").getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try(Session session = SESSION_FACTORY.getCurrentSession();){
            transaction = session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null){
                transaction.rollback();
            }
        }
    }
}
