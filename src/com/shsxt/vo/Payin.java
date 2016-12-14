package com.shsxt.vo;

import java.util.Date;

public class Payin {

	private Integer id;
	private String name;
	private String type;
	private Double money;
	private String remark;
	private Date createTime;
	private Date updateTime;
	private Integer aid;
	public Payin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Payin(String name, String type, Double money, String remark,
			Date createTime, Date updateTime, Integer aid) {
		super();
		this.name = name;
		this.type = type;
		this.money = money;
		this.remark = remark;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.aid = aid;
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
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	
}
