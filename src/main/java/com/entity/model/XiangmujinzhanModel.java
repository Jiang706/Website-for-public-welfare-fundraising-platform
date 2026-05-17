package com.entity.model;

import com.entity.XiangmujinzhanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 项目进展
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-05-18 16:39:31
 */
public class XiangmujinzhanModel  implements Serializable {
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
	 * 项目进展
	 */
	
	private String xiangmujinzhan;
		
	/**
	 * 登记时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date dengjishijian;
		
	/**
	 * 进展详情
	 */
	
	private String jinzhanxiangqing;
		
	/**
	 * 募捐者账号
	 */
	
	private String mujuanzhezhanghao;
		
	/**
	 * 募捐者姓名
	 */
	
	private String mujuanzhexingming;
		
	/**
	 * 赞
	 */
	
	private Integer thumbsupnum;
		
	/**
	 * 踩
	 */
	
	private Integer crazilynum;
		
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
	 * 设置：项目进展
	 */
	 
	public void setXiangmujinzhan(String xiangmujinzhan) {
		this.xiangmujinzhan = xiangmujinzhan;
	}
	
	/**
	 * 获取：项目进展
	 */
	public String getXiangmujinzhan() {
		return xiangmujinzhan;
	}
				
	
	/**
	 * 设置：登记时间
	 */
	 
	public void setDengjishijian(Date dengjishijian) {
		this.dengjishijian = dengjishijian;
	}
	
	/**
	 * 获取：登记时间
	 */
	public Date getDengjishijian() {
		return dengjishijian;
	}
				
	
	/**
	 * 设置：进展详情
	 */
	 
	public void setJinzhanxiangqing(String jinzhanxiangqing) {
		this.jinzhanxiangqing = jinzhanxiangqing;
	}
	
	/**
	 * 获取：进展详情
	 */
	public String getJinzhanxiangqing() {
		return jinzhanxiangqing;
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
