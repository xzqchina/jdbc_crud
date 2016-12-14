package com.shsxt.dao;

import java.text.SimpleDateFormat;
import java.util.List;

import com.shsxt.vo.Account;

public interface AccountDao {

	/**
	 * 添加记录返回受影响行数
	 * @param account
	 * @return
	 */
	public int addAccountNoKey(Account account);
	
	/**
	 * 添加记录返回主键
	 */
	public int addAccountHasKey(Account account);
	
	/**
	 * 批量添加记录
	 * @param accounts
	 * @return
	 */
	public int addAccountBatch(List<Account> accounts);
	
	/**
	 * 查询当前登录用户的账户记录数
	 * @param uid
	 * @return
	 */
	public Integer queryAccountCountByUserId(Integer uid);
	
	/**
	 * 根据账户ID查询账户详情
	 * @param id
	 * @return
	 */
	public  Account queryAccountById(Integer id);
	
	/**
	 * 根据条件查询账户详情
	 * @param uid
	 * @param ananme
	 * @param type
	 * @param s1
	 * @return
	 */
	public List<Account> queryAccountByParam(Integer uid,String aname,String type, String time);
	
	/**
	 * 更新账户记录
	 * @param account
	 * @return
	 */
	public int updateAccountById(Account account);
	
	/**
	 * 批量修改用户信息
	 * @param accounts
	 * @return
	 */
	public int updateAccountBatch(List<Account> accounts);
	
	/**
	 * 删除单条记录
	 * @param aid
	 * @return
	 */
	public int delAccountById(Integer aid);
	
	/**
	 * 批量删除账户记录
	 * @param ids
	 * @return
	 */
	public int delAccountBatch(Integer[] ids);

	/**
	 * 转账入账
	 * @param tarAid
	 * @param money
	 * @return
	 */
	public int inAccount(String tarAid,double money);
	
	/**
	 * 转账出账
	 * @param souAid
	 * @param money
	 * @return
	 */
	
	public int outAccount(String souAid,double money);
	
}
