package edu.mum.cs544.bank.service;

import java.util.Collection;

import edu.mum.cs544.bank.dao.AccountDAO;
import edu.mum.cs544.bank.dao.IAccountDAO;
import edu.mum.cs544.bank.dao.JPAAccountDAO;
import edu.mum.cs544.bank.domain.Account;
import edu.mum.cs544.bank.domain.Customer;
import edu.mum.cs544.bank.helper.EntityManagerHelper;
import edu.mum.cs544.bank.jms.IJMSSender;
import edu.mum.cs544.bank.jms.JMSSender;
import edu.mum.cs544.bank.logging.ILogger;
import edu.mum.cs544.bank.logging.Logger;

import javax.persistence.EntityManager;


public class AccountService implements IAccountService {
	private IAccountDAO accountDAO;
	private ICurrencyConverter currencyConverter;
	private IJMSSender jmsSender;
	private ILogger logger;
	
	public AccountService(){
		accountDAO=new JPAAccountDAO();
		currencyConverter= new CurrencyConverter();
		jmsSender =  new JMSSender();
		logger = new Logger();
	}

	public Account createAccount(long accountNumber, String customerName) {
		EntityManager em = EntityManagerHelper.getCurrent();
		em.getTransaction().begin();
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		accountDAO.saveAccount(account);
		account = accountDAO.loadAccount(accountNumber);
		em.getTransaction().commit();
		em.close();

		logger.log("createAccount with parameters accountNumber= "+accountNumber+" , customerName= "+customerName);
		return account;
	}

	public void deposit(long accountNumber, double amount) {
		EntityManager em = EntityManagerHelper.getCurrent();
		em.getTransaction().begin();
		Account account = accountDAO.loadAccount(accountNumber);
		account.deposit(amount);
		accountDAO.updateAccount(account);
		em.getTransaction().commit();
		em.close();

		logger.log("deposit with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amount > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}

	public Account getAccount(long accountNumber) {
		EntityManager em = EntityManagerHelper.getCurrent();
		em.getTransaction().begin();
		Account account = accountDAO.loadAccount(accountNumber);
		em.getTransaction().commit();
		em.close();

		return account;
	}

	public Collection<Account> getAllAccounts() {
		return accountDAO.getAccounts();
	}

	public void withdraw(long accountNumber, double amount) {
		EntityManager em = EntityManagerHelper.getCurrent();
		em.getTransaction().begin();
		Account account = accountDAO.loadAccount(accountNumber);
		account.withdraw(amount);
		accountDAO.updateAccount(account);
		em.getTransaction().commit();
		em.close();

		logger.log("withdraw with parameters accountNumber= "+accountNumber+" , amount= "+amount);
	}

	public void depositEuros(long accountNumber, double amount) {
		EntityManager em = EntityManagerHelper.getCurrent();
		em.getTransaction().begin();
		Account account = accountDAO.loadAccount(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.deposit(amountDollars);
		accountDAO.updateAccount(account);
		em.getTransaction().commit();
		em.close();
		logger.log("depositEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amountDollars > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}

	public void withdrawEuros(long accountNumber, double amount) {
		EntityManager em = EntityManagerHelper.getCurrent();
		em.getTransaction().begin();
		Account account = accountDAO.loadAccount(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.withdraw(amountDollars);
		accountDAO.updateAccount(account);
		em.getTransaction().commit();
		em.close();
		logger.log("withdrawEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
	}

	public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
		EntityManager em = EntityManagerHelper.getCurrent();
		em.getTransaction().begin();
		Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
		Account toAccount = accountDAO.loadAccount(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		accountDAO.updateAccount(fromAccount);
		accountDAO.updateAccount(toAccount);
		em.getTransaction().commit();
		em.close();

		logger.log("transferFunds with parameters fromAccountNumber= "+fromAccountNumber+" , toAccountNumber= "+toAccountNumber+" , amount= "+amount+" , description= "+description);
		if (amount > 10000){
			jmsSender.sendJMSMessage("TransferFunds of $ "+amount+" from account with accountNumber= "+fromAccount+" to account with accountNumber= "+toAccount);
		}
	}
}
