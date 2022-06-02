package edu.mum.cs544.bank.dao;

import edu.mum.cs544.bank.domain.Account;
import edu.mum.cs544.bank.helper.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

public class JPAAccountDAO implements IAccountDAO{
    @Override
    public void saveAccount(Account account) {
        EntityManager em = EntityManagerHelper.getCurrent();
        em.persist(account);
    }

    @Override
    public void updateAccount(Account account) {
        EntityManager em = EntityManagerHelper.getCurrent();
        em.merge(account);
    }

    @Override
    public Account loadAccount(long accountnumber) {
        EntityManager em = EntityManagerHelper.getCurrent();
        return em.find(Account.class,accountnumber);
    }

    @Override
    public Collection<Account> getAccounts() {
        EntityManager em = EntityManagerHelper.getCurrent();
        TypedQuery<Account> query = em.createQuery("SELECT distinct a from Account a" +
                " JOIN FETCH a.entryList" +
                " JOIN FETCH a.customer",Account.class);
        List<Account> accounts = query.getResultList();
        return accounts;
    }
}
