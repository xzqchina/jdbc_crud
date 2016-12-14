package com.shsxt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.shsxt.dao.AccountDao;
import com.shsxt.vo.Account;
@Repository
public class AccountDaoImpl implements AccountDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int addAccountNoKey(Account account) {
	  String sql="insert into account (aname,type,money,remark,create_time,update_time,"
	  		+ " user_id) values (?,?,?,?,?,?,?) ";
	return  jdbcTemplate.update(sql, account.getAname(),account.getType(),account.getMoney(),account.getRemark(),
			  new Date(),new Date(),account.getUserId());
	}

	@Override
	public int addAccountHasKey(final Account account) {
		 final String sql="insert into account (aname,type,money,remark,create_time,update_time,"
			  		+ " user_id) values (?,?,?,?,?,?,?) ";
		 
		 KeyHolder  keyHolder = new GeneratedKeyHolder();
		 jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, account.getAname());
					ps.setString(2, account.getType());
					ps.setDouble(3, account.getMoney());
					ps.setString(4, account.getRemark());
					ps.setObject(5, account.getCreateTime());
					ps.setObject(6, account.getUpdateTime());
					ps.setInt(7, account.getUserId());
				return ps;
			}
			 
		 }, keyHolder);
		 return keyHolder.getKey().intValue();
	}

	@Override
	public int addAccountBatch(final List<Account> accounts) {
		String sql="insert into account (aname,type,money,remark,create_time,update_time,"
		  		+ " user_id) values (?,?,?,?,?,?,?) ";
		
		return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){

			@Override
			public int getBatchSize() {
				return accounts.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setString(1, accounts.get(i).getAname());
				ps.setString(2, accounts.get(i).getType());
				ps.setDouble(3, accounts.get(i).getMoney());
				ps.setString(4, accounts.get(i).getRemark());
				ps.setObject(5, accounts.get(i).getCreateTime());
				ps.setObject(6, accounts.get(i).getUpdateTime());
				ps.setInt(7, accounts.get(i).getUserId());
				
			}
			
		}).length;

	}


	@Override
	public Integer queryAccountCountByUserId(Integer uid) {
		String sql="select count(1) from account where user_id=?";
	
		return jdbcTemplate.queryForObject(sql, new Object[]{uid}, Integer.class);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Account queryAccountById(Integer aid) {
		String sql="select aname,type,money,remark,create_time,update_time,user_id from account where id = ?";
		return	jdbcTemplate.queryForObject(sql, new Object[]{aid}, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int reoNum) throws SQLException {
				Account account = new Account();
				account.setAname(rs.getString("aname"));
				account.setType(rs.getString("type"));
				account.setMoney(rs.getDouble("money"));
				account.setRemark(rs.getString("remark"));
				account.setCreateTime(rs.getDate("create_time"));
				account.setUpdateTime(rs.getDate("update_time"));
				account.setUserId(rs.getInt("user_id"));
				
				return account;
			}
			
		});
	}

	@Override
	public List<Account> queryAccountByParam(Integer uid, String aname,
			String type, String time) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sb=new StringBuffer("select id,aname,type,money,remark,create_time,update_time,user_id"
				+ " from account where user_id = ?");
		
		params.add(uid);
		
		if(null!=aname&&!"".equals(aname.trim())){
			sb.append(" and aname like ? ");
			params.add("%"+aname+"%");
		}
		
		if(null!=type&& !"".equals(type.trim())){
			sb.append(" and type = ?");
			params.add(type);
		}
		
		if(null!=time &&!"".equals(time.trim())){
			sb.append(" and create_time <= ?");
			params.add(time);
		}
		
		return  jdbcTemplate.query(sb.toString(), params.toArray(), new RowMapper<Account>(){

			@Override
			public Account mapRow(ResultSet rs, int i) throws SQLException {
				Account account = new Account();
				account.setId(rs.getInt("id"));
				account.setAname(rs.getString("aname"));
				account.setType(rs.getString("type"));
				account.setMoney(rs.getDouble("money"));
				account.setRemark(rs.getString("remark"));
				account.setCreateTime(rs.getDate("create_time"));
				account.setUpdateTime(rs.getDate("update_time"));
				account.setUserId(rs.getInt("user_id"));
				
				return account;			
			}
			
		});
		
		
		
	}

	@Override
	public int updateAccountById(Account account) {
		String sql="update account set aname=?, type=?, money=?, remark=?,update_time=? where id=?";
		return jdbcTemplate.update(sql, account.getAname(),account.getType(),account.getMoney(),account.getRemark(),
				account.getCreateTime(),account.getId());
	}

	@Override
	public int updateAccountBatch(final List<Account> accounts) {
		String sql="update account set aname=?, type=?, money=?, remark=?,update_time=? where id=?";
		return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, accounts.get(i).getAname());
				ps.setString(2, accounts.get(i).getType());
				ps.setDouble(3, accounts.get(i).getMoney());
				ps.setString(4, accounts.get(i).getRemark());
				ps.setObject(5, accounts.get(i).getUpdateTime());
				ps.setInt(6, accounts.get(i).getId());				
			}
			
			@Override
			public int getBatchSize() {
				return accounts.size();
			}
		}).length;
	}

	

	@Override
	public int delAccountById(Integer aid) {
		String sql="delete from account where id=?";
		
		return jdbcTemplate.update(sql, aid);
	}

	@Override
	public int delAccountBatch(final Integer[] ids) {
		String sql="delete from account where id=?";
		return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
			
				ps.setInt(1, ids[i]);
			}
			
			@Override
			public int getBatchSize() {
				
				return ids.length;
			}
		}).length;
	}

	@Override
	public int inAccount(String tarAid, double money) {
		String sql= "update account set money=money+? where id = ?";
		return jdbcTemplate.update(sql, money,tarAid);
	}

	@Override
	public int outAccount(String souAid, double money) {
		String sql= "update account set money=money-? where id = ?";
		return jdbcTemplate.update(sql, money,souAid);
	}

}
