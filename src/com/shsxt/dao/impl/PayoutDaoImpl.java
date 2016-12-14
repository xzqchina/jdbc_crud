package com.shsxt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.shsxt.dao.PayoutDao;
import com.shsxt.vo.Payout;

@Repository
public class PayoutDaoImpl implements PayoutDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int addPayoutNoKey(Payout payout) {
		String sql="insert into pay_out (name,type,money,create_time,"
				+ "update_time,aid,remark) values (?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, payout.getName(),payout.getType(),payout.getMoney(),
				payout.getCreateTime(),payout.getUpdateTime(),payout.getAid(),payout.getRemark());
	}

	@Override
	public int addPayoutHasKey(final Payout payout) {
		final String sql="insert into pay_out (name,type,money,create_time,"
				+ "update_time,aid,remark) values (?,?,?,?,?,?,?)";
		
		KeyHolder keyHolder =new GeneratedKeyHolder();  
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1,payout.getName());
				ps.setString(2, payout.getType());
				ps.setDouble(3, payout.getMoney());
				ps.setObject(4, payout.getCreateTime());
				ps.setObject(5, payout.getUpdateTime());
				ps.setInt(6, payout.getAid());
				ps.setString(7, payout.getRemark());
				return ps;
			}
			
		},keyHolder);
		return keyHolder.getKey().intValue();
	}

	@Override
	public int addPayoutBatch(final List<Payout> payouts) {
		String sql="insert into pay_out (name,type,money,create_time,"
				+ "update_time,aid,remark) values (?,?,?,?,?,?,?)";
		return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1,payouts.get(i).getName());
				ps.setString(2, payouts.get(i).getType());
				ps.setDouble(3, payouts.get(i).getMoney());
				ps.setObject(4, payouts.get(i).getCreateTime());
				ps.setObject(5, payouts.get(i).getUpdateTime());
				ps.setInt(6, payouts.get(i).getAid());
				ps.setString(7, payouts.get(i).getRemark());			
			}
			
			@Override
			public int getBatchSize() {
				return payouts.size();
			}
		}).length;
	}

	@Override
	public int queryPayoutByAccountId(Integer aid) {
		String sql="select count(1) from pay_out where aid=?";
		return jdbcTemplate.update(sql, aid);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Payout queryPayoutById(Integer id) {
		String sql="select name,type,money,create_time,update_time,aid,remark"
				+ " from pay_out where id=?";
		
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int i) throws SQLException {
				Payout p=new Payout();
				p.setName(rs.getString("name"));
				p.setType(rs.getString("type"));
				p.setMoney(rs.getDouble("money"));
				p.setCreateTime(rs.getDate("create_time"));
				p.setUpdateTime(rs.getDate("update_time"));
				p.setAid(rs.getInt("aid"));	
				p.setRemark(rs.getString("remark"));
				return p;
			}
			
		});
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Payout> queryPayoutByParam(Integer aid, String name,
			String type, String time) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sb= new StringBuffer("select name,type,money,create_time,update_time,aid,remark"
				+ " from pay_out where id=?");
		params.add(aid);
		
		if(null!=name&&!"".equals(name.trim())){
			sb.append("and aname like ? ");
			params.add("%"+name+"%");
		}
		
		if(null!=type&& !"".equals(type.trim())){
			sb.append("and type = ?");
			params.add(type);
		}
		
		if(null!=time &&!"".equals(time.trim())){
			sb.append("and create_time <= ?");
			params.add(time);
		}
	return	jdbcTemplate.query(sb.toString(), params.toArray(), new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int i) throws SQLException {
				Payout p= new Payout();
				p.setName(rs.getString("name"));
				p.setType(rs.getString("type"));
				p.setMoney(rs.getDouble("money"));
				p.setCreateTime(rs.getDate("create_time"));
				p.setUpdateTime(rs.getDate("update_time"));
				p.setAid(rs.getInt("aid"));	
				p.setRemark(rs.getString("remark"));
				return p;
			}
			
		});

	}

	@Override
	public int updatePayoutById(Payout payout) {
		String sql="update pay_out set name=?,type=?,money=?,update_time=?,remark=? where id=?";
		return jdbcTemplate.update(sql, payout.getName(),payout.getType(),payout.getMoney(),
				payout.getUpdateTime(),payout.getRemark(),payout.getId());
	}


	@Override
	public int updatePayoutBatch(final List<Payout> payouts) {
		String sql="update pay_out set name=?,type=?,money=?,update_time=?,remark=? where id=?";
		return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){

			@Override
			public int getBatchSize() {
				return payouts.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setString(1, payouts.get(i).getName());
				ps.setString(2, payouts.get(i).getType());
				ps.setDouble(3, payouts.get(i).getMoney());
				ps.setString(4, payouts.get(i).getRemark());
				ps.setObject(5, payouts.get(i).getCreateTime());
				ps.setObject(6, payouts.get(i).getUpdateTime());
				ps.setInt(7, payouts.get(i).getAid());		
				
			}
			
		}).length;
	}

	@Override
	public int delPayoutById(Integer id) {
		String sql="delete from pay_out where id=?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int delPayoutBatch(final List<Payout> payouts) {
		String sql="delete from pay_out where id=?";
		return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){

			@Override
			public int getBatchSize() {
				return payouts.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setString(1, payouts.get(i).getName());
				ps.setString(2, payouts.get(i).getType());
				ps.setDouble(3, payouts.get(i).getMoney());
				ps.setString(4, payouts.get(i).getRemark());
				ps.setObject(5, payouts.get(i).getCreateTime());
				ps.setObject(6, payouts.get(i).getUpdateTime());
				ps.setInt(7, payouts.get(i).getAid());	
				
			}
			
		}).length;
	}
	

}
