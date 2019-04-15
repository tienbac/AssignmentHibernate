package models;

import data.HibernateConnection;
import entity.UserInformation;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserInformationModel {
    public boolean addInformation(UserInformation information){
        Session session = HibernateConnection.openSession();
        Transaction transaction = null;
        try{
            transaction = session.getTransaction();
            transaction.begin();
            session.save(information);
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

    public boolean updateInformation(UserInformation information){
        Session session = HibernateConnection.openSession();
        Transaction transaction = null;
        try{
            transaction = session.getTransaction();
            transaction.begin();
            session.update(information);
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
}
