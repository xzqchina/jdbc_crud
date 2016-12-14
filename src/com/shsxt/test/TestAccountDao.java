package com.shsxt.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.format.datetime.DateFormatter;

import com.shsxt.dao.AccountDao;
import com.shsxt.vo.Account;

public class TestAccountDao {

	private AccountDao accountDao;
	
	@Before
	public void init(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
		accountDao=(AccountDao) ac.getBean("accountDaoImpl");
	}
	
	@Test
	public void testAddAccount01() {
		Account account=new Account("sxt", "1", "sxt", 1, 1000.0, new Date(), new Date());
		
		System.out.println("受影响行数:"+accountDao.addAccountNoKey(account));
		
	}
	
	@Test
	public void testAddAccount02() {
		Account account=new Account("sxt02", "0", "sxt02", 1, 1000.0, new Date(), new Date());
		
		System.out.println("主键:"+accountDao.addAccountHasKey(account));
		
	}
	
	
	
	@Test
	public void testAddAccount03() {
		List<Account> accounts=new ArrayList<>();
		
		for(int i=10;i<20;i++){
			Account account=new Account("sxt0"+i, "0", "sxt0"+i, 1, 1000.0, new Date(), new Date());
			accounts.add(account);
		}
		System.out.println("受影响行数:"+accountDao.addAccountBatch(accounts));
		
	}
	
	@Test
	public void testQueryAccount01(){
		System.out.println(accountDao.queryAccountCountByUserId(1));
	}
	
	
	@Test
	public void testQueryAccount02(){
		Account account=accountDao.queryAccountById(1);
		
		System.out.println(account);
	}
	@Test
	public void testQueryAccount03(){
		
		List<Account> accounts=accountDao.queryAccountByParam(1, null, "1", "2016-12-06");
		
		for(Account account:accounts){
			System.out.println(account);
		}
	}
	
	@Test
	public void updateAccount01(){
		Account account=accountDao.queryAccountById(1);
		
		account.setAname("test03");
		account.setType("0");
		System.out.println("影响行数:"+accountDao.updateAccountById(account));
		
	}
	
	@Test
	public void updateAccount02(){
		List<Account> accounts=accountDao.queryAccountByParam(1, "test", null, null);
		
		for(Account account:accounts){
			account.setAname("stx020");
			account.setType("0");
		}
		
		System.out.println("影响行数:"+accountDao.updateAccountBatch(accounts));
		
	}
	
	
	@Test
	public void delAccountById01(){
		System.out.println("影响行数:"+accountDao.delAccountById(14));
	}
	
	
	@Test
	public void delAccountById02(){
		Integer[] ids={10,11,12,13};
		System.out.println("影响行数:"+accountDao.delAccountBatch(ids));
	}



}
