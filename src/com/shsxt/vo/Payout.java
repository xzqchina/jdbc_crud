package com.shsxt.vo;

import java.util.Date;

public class Payout {

	private Integer id;
	private String name;
	private String type;
	private double money;
	private Date createTime;
	private Date UpdateTime;;
	private Integer aid;
	private String remark;
	public Payout() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Payout(String name, String type, double money, Date createTime,
			Date updateTime, Integer aid, String remark) {
		super();
		this.name = name;
		this.type = type;
		this.money = money;
		this.createTime = createTime;
		UpdateTime = updateTime;
		this.aid = aid;
		this.remark = remark;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return UpdateTime;
	}
	public void setUpdateTime(Date updateTime) {
		UpdateTime = updateTime;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
