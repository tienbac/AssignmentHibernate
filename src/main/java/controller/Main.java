package controller;

import data.HibernateConnection;
import entity.Account;
import entity.Role;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateConnection.openSession();
        session.beginTransaction();
        Role role = new Role();
        role.setName("Admin");
        role.setDescription("Admin");
        role.setStatus(1);
        role.setCreatedAt(123141234);
        role.setUpdatedAt(123412344);

        Account account = new Account();
        account.setUsername("tienbac");
        account.setPassword("2341234124");
        account.setStatus(1);
        account.setCreatedAt(23141234);
        account.setUpdatedAt(213412341);

        Set<Account> accounts = new HashSet<Account>();
        accounts.add(account);
        role.setAccounts(accounts);

        session.save(role);
        session.getTransaction().commit();
        System.out.println("DONE");
    }
}
