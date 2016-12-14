package com.shsxt.vo;

import java.util.Date;

public class Account {
	
	private Integer id;
	
	private String aname;
	
	private String type;
	
	private String remark;
	
	private Integer userId;
	
	private Double money;
	
	private Date createTime;
	
	private Date updateTime;
	
	

	public Account() {
		super();
	}

	public Account(String aname, String type, String remark, Integer userId,
			Double money, Date createTime, Date updateTime) {
		super();
		this.aname = aname;
		this.type = type;
		this.remark = remark;
		this.userId = userId;
		this.money = money;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", aname=" + aname + ", type=" + type
				+ ", remark=" + remark + ", userId=" + userId + ", money="
				+ money + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}
	
	
	
	

}
