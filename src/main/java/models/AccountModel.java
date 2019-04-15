package models;

import data.HibernateConnection;
import entity.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class AccountModel {

    public boolean addAccount(Account account){
        Session session = HibernateConnection.openSession();
        Transaction transaction = null;
        try {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(account);
            transaction.commit();
        }catch (Exception ex){
            if (transaction!=null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }
        return true;
    }

    public boolean updateAccount(Account account){
        Session session = HibernateConnection.openSession();
        Transaction transaction = null;
        try {
            transaction = session.getTransaction();
            transaction.begin();
            session.update(account);
            transaction.commit();
        }catch (Exception ex){
            if (transaction!=null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }
        return true;
    }

    public boolean deleteAccount(Account account){
        Session session = HibernateConnection.openSession();
        Transaction transaction = null;
        if (isAccountExists(account)){
            try {
                transaction = session.getTransaction();
                transaction.begin();
                session.delete(account);
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

    public Account loadAccount(int id){
        Session session = HibernateConnection.openSession();
        Transaction transaction = null;
        Account account = new Account();
        try {
            transaction = session.getTransaction();
            transaction.begin();
            account = (Account) session.get(Account.class, id);
            transaction.commit();
        }catch (Exception e){
            if (transaction!= null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }
        return account;
    }

    public List<Account> loadAccounts(){
        Session session = HibernateConnection.openSession();
        Transaction transaction = null;
        List<Account> list = new ArrayList<Account>();
        try {
            transaction = session.getTransaction();
            session.beginTransaction();
            String str = "FROM Account";
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

    public boolean isAccountExists(Account account){
        Session session = HibernateConnection.openSession();
        boolean result = false;
        Transaction transaction = null;
        try{
            transaction = session.getTransaction();
            transaction.begin();
            String str = "FROM Account WHERE username ='" + account.getUsername()+"'";
            Query query = session.createQuery(str);
            Account r = (Account) query.uniqueResult();
            transaction.commit();
            if (r!=null) result = true;
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
