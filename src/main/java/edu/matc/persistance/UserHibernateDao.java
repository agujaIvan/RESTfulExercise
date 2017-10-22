package edu.matc.persistance;


import edu.matc.entity.GendertableEntity;
import edu.matc.entity.UsertableEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserHibernateDao {

    private final Logger log = Logger.getLogger(this.getClass());
    Session session = null;
    Transaction tx = null;

    public UserHibernateDao(){}

    public List<UsertableEntity> getAllUser() {

        List<UsertableEntity> user = new ArrayList();
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // create a query
            String hql = "SELECT U.userName FROM edu.matc.entity.UsertableEntity U";

            Query theQuery =
                    session.createQuery(hql);

            // execute query and get result list
            user = theQuery.list();

            //session.save(user);
            //tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            log.info("Error getting all the user from DATABASE", he);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        // return the results
        return user;
    }

    public List<UsertableEntity> getUserById(int id) {

        List<UsertableEntity> user = new ArrayList<>();
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // create a query
            String hql = "SELECT U.userName FROM edu.matc.entity.UsertableEntity U WHERE U.idUserTable = :userId";

            Query theQuery =
                    session.createQuery(hql);

            theQuery.setParameter("userId", id);

            // execute query and get result list
            user = theQuery.list();

            //session.save(user);
            //tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            log.info("Error searching for user ID in database", he);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        // return the results
        return user;
    }
}
