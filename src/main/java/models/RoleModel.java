package models;

import data.HibernateConnection;
import entity.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class RoleModel {
    public boolean addRole(Role role){
        Session session = HibernateConnection.openSession();
        if (isRoleExists(role)) return false;

        Transaction transaction = null;
        try {
            transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(role);
            transaction.commit();
        }catch (Exception e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return true;
    }

    public boolean updateRole(Role role){
        Session session = HibernateConnection.openSession();
        if (isRoleExists(role)) return false;

        Transaction transaction = null;
        try {
            transaction = session.getTransaction();
            transaction.begin();
            session.update(role);
            transaction.commit();
        }catch (Exception e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return true;
    }

    public boolean deteleRole(Role role){
        Session session = HibernateConnection.openSession();
        Transaction transaction = null;
        if (isRoleExists(role)){
            try {
                transaction = session.getTransaction();
                transaction.begin();
                session.delete(role);
                transaction.commit();
            }catch (Exception e){
                if (transaction!= null){
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

    public Role loadRole(int id){
        Session session = HibernateConnection.openSession();
        Transaction transaction = null;
        Role role = new Role();
        try {
            transaction = session.getTransaction();
            transaction.begin();
            role = (Role) session.get(Role.class, id);
            transaction.commit();
        }catch (Exception e){
            if (transaction!= null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }
        return role;
    }

    public List<Role> loadRoles(){
        Session session = HibernateConnection.openSession();
        Transaction transaction = null;
        List<Role> list = new ArrayList<Role>();
        try {
            transaction = session.getTransaction();
            session.beginTransaction();
            String str = "FROM Role";
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

    public boolean isRoleExists(Role role){
        Session session = HibernateConnection.openSession();
        boolean result = false;
        Transaction transaction = null;
        try{
            transaction = session.getTransaction();
            transaction.begin();
            String str = "FROM Role WHERE name ='" + role.getName()+"'";
            Query query = session.createQuery(str);
            Role r = (Role)query.uniqueResult();
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
