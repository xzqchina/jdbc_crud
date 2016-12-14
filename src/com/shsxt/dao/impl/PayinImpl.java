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

import com.shsxt.dao.PayinDao;
import com.shsxt.vo.Payin;

@Repository
public class PayinImpl implements PayinDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int addPayinNoKey(Payin payin) {
		String sql="insert into pay_in (name,type,money,remark,create_time,"
				+ "update_time,aid) values (?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, payin.getName(),payin.getType(),
				payin.getMoney(),payin.getRemark(),
				payin.getCreateTime(),payin.getUpdateTime(),payin.getAid());
	}

	@Override
	public int addPayinHasKey(final Payin payin) {
		final String sql="insert into pay_in (name,type,money,remark,create_time,"
				+ "update_time,aid) values (?,?,?,?,?,?,?)";
		
		KeyHolder keyHolder=new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, payin.getName());
				ps.setString(2, payin.getType());
				ps.setDouble(3, payin.getMoney());
				ps.setString(4, payin.getRemark());
				ps.setObject(5, payin.getCreateTime());
				ps.setObject(6, payin.getUpdateTime());
				ps.setInt(7, payin.getAid());
				return ps;
			}
			
		}, keyHolder);
		
		return keyHolder.getKey().intValue();
	}

	@Override
	public int addPayinBatch(final List<Payin> payins) {
		 String sql="insert into pay_in (name,type,money,remark,create_time,"
					+ "update_time,aid) values (?,?,?,?,?,?,?)";
	return	 jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){

			@Override
			public int getBatchSize() {			
				return payins.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setString(1, payins.get(i).getName());
				ps.setString(2, payins.get(i).getType());
				ps.setDouble(3, payins.get(i).getMoney());
				ps.setString(4, payins.get(i).getRemark());
				ps.setObject(5, payins.get(i).getCreateTime());
				ps.setObject(6, payins.get(i).getUpdateTime());
				ps.setInt(7, payins.get(i).getAid());						
			}
			 
		 }).length;

	}

	@Override
	public int queryPayinByAccountId(Integer aid) {
		String sql="select count(1) from pay_in where aid=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{aid}, Integer.class);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Payin queryPayinById(Integer id) {
		String sql="select name,type,money,remark,create_time,update_time,"
				+ "aid from pay_in where id=?";
		
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int i) throws SQLException {
				Payin p=new Payin();
				p.setName(rs.getString("name"));
				p.setType(rs.getString("type"));
				p.setMoney(rs.getDouble("money"));
				p.setRemark(rs.getString("remark"));
				p.setCreateTime(rs.getDate("create_time"));
				p.setUpdateTime(rs.getDate("update_time"));
				p.setAid(rs.getInt("aid"));	
				return p;
			}
			
		});
		
	}


	@Override
	public List<Payin> queryPayinByParam(Integer aid, String name, String type,
			String time) {
		List<Object> params = new ArrayList<Object>();
		
		StringBuffer sb=new StringBuffer("select name,type,money,remark,create_time,update_time,"
				+ "aid from pay_in where aid=?");
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
		
	return	jdbcTemplate.query(sb.toString(), params.toArray(), new RowMapper<Payin>(){

			@Override
			public Payin mapRow(ResultSet rs, int i) throws SQLException {
				Payin p=new Payin();
				p.setName(rs.getString("name"));
				p.setType(rs.getString("type"));
				p.setMoney(rs.getDouble("money"));
				p.setRemark(rs.getString("remark"));
				p.setCreateTime(rs.getDate("create_time"));
				p.setUpdateTime(rs.getDate("update_time"));
				p.setAid(rs.getInt("aid"));	
				return p;
			}
			
		});

	}

	@Override
	public int updatePayinById(Payin  payin) {
		String sql="update pay_in set name=?,type=?,money=?,remark=?,update_time=? where id=?";
		return jdbcTemplate.update(sql, payin.getName(),payin.getType(),
				payin.getMoney(),payin.getRemark(),
				payin.getUpdateTime(),payin.getId());
	}

	@Override
	public int updatePayinBatch(final List<Payin> payins) {
		String sql="update pay_in set name=?,type=?,money=?,remark=?,update_time=? where id=?";
		return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){

			@Override
			public int getBatchSize() {
				return payins.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setString(1, payins.get(i).getName());
				ps.setString(2, payins.get(i).getType());
				ps.setDouble(3, payins.get(i).getMoney());
				ps.setString(4, payins.get(i).getRemark());
				ps.setObject(5, payins.get(i).getCreateTime());
				ps.setObject(6, payins.get(i).getUpdateTime());
				ps.setInt(7, payins.get(i).getAid());		
				
			}
			
		}).length;
	}

	@Override
	public int delPayinById(Integer id) {
		String sql="delete from pay_in where id=?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int delPayinBatch(final List<Payin> payins) {
		String sql="delete from pay_in where id=?";
		return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){

			@Override
			public int getBatchSize() {
				return payins.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setString(1, payins.get(i).getName());
				ps.setString(2, payins.get(i).getType());
				ps.setDouble(3, payins.get(i).getMoney());
				ps.setString(4, payins.get(i).getRemark());
				ps.setObject(5, payins.get(i).getCreateTime());
				ps.setObject(6, payins.get(i).getUpdateTime());
				ps.setInt(7, payins.get(i).getAid());	
				
			}
			
		}).length;
	}

}
