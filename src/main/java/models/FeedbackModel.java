package models;

import data.HibernateConnection;
import entity.Feedback;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class FeedbackModel {
    public boolean addFeedback(Feedback feedback){
        Session session = HibernateConnection.openSession();
        Transaction transaction = null;
        try {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(feedback);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }
        return true;
    }

    public boolean updateFeedback(Feedback feedback){
        Session session = HibernateConnection.openSession();
        Transaction transaction = null;
        try {
            transaction = session.getTransaction();
            transaction.begin();
            session.update(feedback);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }
        return true;
    }

    public boolean deleteFeedback(Feedback feedback){
        Session session = HibernateConnection.openSession();
        Transaction transaction = null;
        if (isExistsFeedback(feedback)){
            try {
                transaction = session.getTransaction();
                transaction.begin();
                session.delete(feedback);
                transaction.commit();
            }catch (Exception ex){
                if (transaction!=null){
                    transaction.rollback();
                }
            }finally {
                session.close();
            }
        }else {
            return false;
        }
        return true;
    }

    public Feedback loadFeedback(int id){
        Session session = HibernateConnection.openSession();
        Transaction transaction = null;
        Feedback feedback = new Feedback();
        try {
            transaction = session.getTransaction();
            transaction.begin();
            feedback = (Feedback) session.get(Feedback.class, id);
            transaction.commit();
        }catch (Exception e){
            if (transaction!= null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }
        return feedback;
    }

    public List<Feedback> loadFeedbackByUserId(int id){
        Session session = HibernateConnection.openSession();
        Transaction transaction = null;
        List<Feedback> list = new ArrayList<Feedback>();

        try {
            transaction = session.getTransaction();
            transaction.begin();
            String str = "SELECT * FROM Feedback f JOIN FETCH Account WHERE f.account.id = " +id;
            Query query = session.createQuery(str);
            list = query.list();
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }
        return list;
    }

    public List<Feedback> loadFeedbacks(){
        Session session = HibernateConnection.openSession();
        Transaction transaction = null;
        List<Feedback> list = new ArrayList<Feedback>();
        try {
            transaction = session.getTransaction();
            session.beginTransaction();
            String str = "FROM Feedback";
            Query query = session.createQuery(str);
            list = query.list();
            transaction.commit();
        }catch (Exception e){
            if (transaction!= null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }
        return list;
    }

    public List<Feedback> loadFeedbacksByStatus(){
        Session session = HibernateConnection.openSession();
        Transaction transaction = null;
        List<Feedback> list = new ArrayList<Feedback>();
        try {
            transaction = session.getTransaction();
            session.beginTransaction();
            String str = "FROM Feedback WHERE status = 1";
            Query query = session.createQuery(str);
            list = query.list();
            transaction.commit();
        }catch (Exception e){
            if (transaction!= null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }
        return list;
    }

    public boolean isExistsFeedback(Feedback feedback){
        Session session = HibernateConnection.openSession();
        boolean result = false;
        Transaction transaction = null;
        try{
            transaction = session.getTransaction();
            transaction.begin();
            String str = "FROM Feedback WHERE id =" + feedback.getFeedbackId();
            Query query = session.createQuery(str);
            Feedback f = (Feedback) query.uniqueResult();
            transaction.commit();
            if (f!=null) result = true;
        }catch (Exception ex){
            if (transaction!=null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }
        return result;
    }
}
