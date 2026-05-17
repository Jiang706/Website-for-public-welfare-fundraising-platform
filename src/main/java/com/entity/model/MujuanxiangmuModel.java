package com.entity.model;

import com.entity.MujuanxiangmuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 募捐项目
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-05-18 16:39:30
 */
public class MujuanxiangmuModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 项目类型
	 */
	
	private String xiangmuleixing;
		
	/**
	 * 地区
	 */
	
	private String diqu;
		
	/**
	 * 图片
	 */
	
	private String tupian;
		
	/**
	 * 目标金额
	 */
	
	private Double mubiaojine;
		
	/**
	 * 已捐金额
	 */
	
	private Double yijuanjine;
		
	/**
	 * 证明材料
	 */
	
	private String zhengmingcailiao;
		
	/**
	 * 申请时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date shenqingshijian;
		
	/**
	 * 项目详情
	 */
	
	private String xiangmuxiangqing;
		
	/**
	 * 项目用途
	 */
	
	private String xiangmuyongtu;
		
	/**
	 * 募捐者账号
	 */
	
	private String mujuanzhezhanghao;
		
	/**
	 * 募捐者姓名
	 */
	
	private String mujuanzhexingming;
		
	/**
	 * 是否审核
	 */
	
	private String sfsh;
		
	/**
	 * 审核回复
	 */
	
	private String shhf;
		
	/**
	 * 赞
	 */
	
	private Integer thumbsupnum;
		
	/**
	 * 踩
	 */
	
	private Integer crazilynum;
		
	/**
	 * 倒计结束时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date reversetime;
		
	/**
	 * 点击次数
	 */
	
	private Integer clicknum;
		
	/**
	 * 评论数
	 */
	
	private Integer discussnum;
		
	/**
	 * 收藏数
	 */
	
	private Integer storeupnum;
				
	
	/**
	 * 设置：项目类型
	 */
	 
	public void setXiangmuleixing(String xiangmuleixing) {
		this.xiangmuleixing = xiangmuleixing;
	}
	
	/**
	 * 获取：项目类型
	 */
	public String getXiangmuleixing() {
		return xiangmuleixing;
	}
				
	
	/**
	 * 设置：地区
	 */
	 
	public void setDiqu(String diqu) {
		this.diqu = diqu;
	}
	
	/**
	 * 获取：地区
	 */
	public String getDiqu() {
		return diqu;
	}
				
	
	/**
	 * 设置：图片
	 */
	 
	public void setTupian(String tupian) {
		this.tupian = tupian;
	}
	
	/**
	 * 获取：图片
	 */
	public String getTupian() {
		return tupian;
	}
				
	
	/**
	 * 设置：目标金额
	 */
	 
	public void setMubiaojine(Double mubiaojine) {
		this.mubiaojine = mubiaojine;
	}
	
	/**
	 * 获取：目标金额
	 */
	public Double getMubiaojine() {
		return mubiaojine;
	}
				
	
	/**
	 * 设置：已捐金额
	 */
	 
	public void setYijuanjine(Double yijuanjine) {
		this.yijuanjine = yijuanjine;
	}
	
	/**
	 * 获取：已捐金额
	 */
	public Double getYijuanjine() {
		return yijuanjine;
	}
				
	
	/**
	 * 设置：证明材料
	 */
	 
	public void setZhengmingcailiao(String zhengmingcailiao) {
		this.zhengmingcailiao = zhengmingcailiao;
	}
	
	/**
	 * 获取：证明材料
	 */
	public String getZhengmingcailiao() {
		return zhengmingcailiao;
	}
				
	
	/**
	 * 设置：申请时间
	 */
	 
	public void setShenqingshijian(Date shenqingshijian) {
		this.shenqingshijian = shenqingshijian;
	}
	
	/**
	 * 获取：申请时间
	 */
	public Date getShenqingshijian() {
		return shenqingshijian;
	}
				
	
	/**
	 * 设置：项目详情
	 */
	 
	public void setXiangmuxiangqing(String xiangmuxiangqing) {
		this.xiangmuxiangqing = xiangmuxiangqing;
	}
	
	/**
	 * 获取：项目详情
	 */
	public String getXiangmuxiangqing() {
		return xiangmuxiangqing;
	}
				
	
	/**
	 * 设置：项目用途
	 */
	 
	public void setXiangmuyongtu(String xiangmuyongtu) {
		this.xiangmuyongtu = xiangmuyongtu;
	}
	
	/**
	 * 获取：项目用途
	 */
	public String getXiangmuyongtu() {
		return xiangmuyongtu;
	}
				
	
	/**
	 * 设置：募捐者账号
	 */
	 
	public void setMujuanzhezhanghao(String mujuanzhezhanghao) {
		this.mujuanzhezhanghao = mujuanzhezhanghao;
	}
	
	/**
	 * 获取：募捐者账号
	 */
	public String getMujuanzhezhanghao() {
		return mujuanzhezhanghao;
	}
				
	
	/**
	 * 设置：募捐者姓名
	 */
	 
	public void setMujuanzhexingming(String mujuanzhexingming) {
		this.mujuanzhexingming = mujuanzhexingming;
	}
	
	/**
	 * 获取：募捐者姓名
	 */
	public String getMujuanzhexingming() {
		return mujuanzhexingming;
	}
				
	
	/**
	 * 设置：是否审核
	 */
	 
	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}
	
	/**
	 * 获取：是否审核
	 */
	public String getSfsh() {
		return sfsh;
	}
				
	
	/**
	 * 设置：审核回复
	 */
	 
	public void setShhf(String shhf) {
		this.shhf = shhf;
	}
	
	/**
	 * 获取：审核回复
	 */
	public String getShhf() {
		return shhf;
	}
				
	
	/**
	 * 设置：赞
	 */
	 
	public void setThumbsupnum(Integer thumbsupnum) {
		this.thumbsupnum = thumbsupnum;
	}
	
	/**
	 * 获取：赞
	 */
	public Integer getThumbsupnum() {
		return thumbsupnum;
	}
				
	
	/**
	 * 设置：踩
	 */
	 
	public void setCrazilynum(Integer crazilynum) {
		this.crazilynum = crazilynum;
	}
	
	/**
	 * 获取：踩
	 */
	public Integer getCrazilynum() {
		return crazilynum;
	}
				
	
	/**
	 * 设置：倒计结束时间
	 */
	 
	public void setReversetime(Date reversetime) {
		this.reversetime = reversetime;
	}
	
	/**
	 * 获取：倒计结束时间
	 */
	public Date getReversetime() {
		return reversetime;
	}
				
	
	/**
	 * 设置：点击次数
	 */
	 
	public void setClicknum(Integer clicknum) {
		this.clicknum = clicknum;
	}
	
	/**
	 * 获取：点击次数
	 */
	public Integer getClicknum() {
		return clicknum;
	}
				
	
	/**
	 * 设置：评论数
	 */
	 
	public void setDiscussnum(Integer discussnum) {
		this.discussnum = discussnum;
	}
	
	/**
	 * 获取：评论数
	 */
	public Integer getDiscussnum() {
		return discussnum;
	}
				
	
	/**
	 * 设置：收藏数
	 */
	 
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}
			
}
