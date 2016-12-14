package com.shsxt.dao;

import java.util.List;

import com.shsxt.vo.Payout;

public interface PayoutDao {

	//添加支出返回受影响行数
	public int addPayoutNoKey(Payout payout);
	
	//添加支出返回主键
	public int addPayoutHasKey(Payout payout);
	
	//批量添加
	public int addPayoutBatch(List<Payout> payouts);
	
	//根据账户id查询收入
	public int queryPayoutByAccountId(Integer aid);
	
	//根据id查询收入详情
	public Payout queryPayoutById(Integer id);
	
	//按条件查询
	public List<Payout>  queryPayoutByParam(Integer aid,String name,String type,String time);
	
	//根据id更新
	public int updatePayoutById(Payout payout);
		
	//更新多条收入记录
	public int updatePayoutBatch(List<Payout>  payouts);
	
	//根据id删除
	public int delPayoutById(Integer id);
	
	//多条删除
	public int delPayoutBatch(List<Payout>  payouts);

	
	
	
	
}
