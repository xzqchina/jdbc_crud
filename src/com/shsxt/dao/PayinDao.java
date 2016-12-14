package com.shsxt.dao;

import java.util.List;

import com.shsxt.vo.Payin;

public interface PayinDao {

	//添加收入返回受影响行数
	public int addPayinNoKey(Payin payin);
	
	//添加收入返回主键
	public int addPayinHasKey(Payin payin);
	
	//批量添加收入
	public int addPayinBatch(List<Payin>  payins);
	
	//根据账户id查询收入
	public int queryPayinByAccountId(Integer aid);
	
	//根据id查询收入详情
	public Payin queryPayinById(Integer id);
	
	//按条件查询
	public List<Payin>  queryPayinByParam(Integer aid,String name,String type,String time);
	
	//根据id更新
	public int updatePayinById(Payin payin);
		
	//更新多条收入记录
	public int updatePayinBatch(List<Payin>  payins);
	
	//根据id删除
	public int delPayinById(Integer id);
	
	//多条删除
	public int delPayinBatch(List<Payin>  payins);
}
