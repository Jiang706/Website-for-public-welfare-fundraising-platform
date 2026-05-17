package com.entity.model;

import com.entity.JuanzengjiluEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 捐赠记录
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-05-18 16:39:31
 */
public class JuanzengjiluModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 项目名称
	 */
	
	private String xiangmumingcheng;
		
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
	 * 捐赠金额
	 */
	
	private Double yijuanjine;
		
	/**
	 * 捐赠详情
	 */
	
	private String juanzengxiangqing;
		
	/**
	 * 捐赠时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date juanzengshijian;
		
	/**
	 * 捐赠账号
	 */
	
	private String juanzengzhanghao;
		
	/**
	 * 捐赠姓名
	 */
	
	private String xingming;
		
	/**
	 * 募捐者账号
	 */
	
	private String mujuanzhezhanghao;
		
	/**
	 * 募捐者姓名
	 */
	
	private String mujuanzhexingming;
		
	/**
	 * 是否支付
	 */
	
	private String ispay;
				
	
	/**
	 * 设置：项目名称
	 */
	 
	public void setXiangmumingcheng(String xiangmumingcheng) {
		this.xiangmumingcheng = xiangmumingcheng;
	}
	
	/**
	 * 获取：项目名称
	 */
	public String getXiangmumingcheng() {
		return xiangmumingcheng;
	}
				
	
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
	 * 设置：捐赠金额
	 */
	 
	public void setYijuanjine(Double yijuanjine) {
		this.yijuanjine = yijuanjine;
	}
	
	/**
	 * 获取：捐赠金额
	 */
	public Double getYijuanjine() {
		return yijuanjine;
	}
				
	
	/**
	 * 设置：捐赠详情
	 */
	 
	public void setJuanzengxiangqing(String juanzengxiangqing) {
		this.juanzengxiangqing = juanzengxiangqing;
	}
	
	/**
	 * 获取：捐赠详情
	 */
	public String getJuanzengxiangqing() {
		return juanzengxiangqing;
	}
				
	
	/**
	 * 设置：捐赠时间
	 */
	 
	public void setJuanzengshijian(Date juanzengshijian) {
		this.juanzengshijian = juanzengshijian;
	}
	
	/**
	 * 获取：捐赠时间
	 */
	public Date getJuanzengshijian() {
		return juanzengshijian;
	}
				
	
	/**
	 * 设置：捐赠账号
	 */
	 
	public void setJuanzengzhanghao(String juanzengzhanghao) {
		this.juanzengzhanghao = juanzengzhanghao;
	}
	
	/**
	 * 获取：捐赠账号
	 */
	public String getJuanzengzhanghao() {
		return juanzengzhanghao;
	}
				
	
	/**
	 * 设置：捐赠姓名
	 */
	 
	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	
	/**
	 * 获取：捐赠姓名
	 */
	public String getXingming() {
		return xingming;
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
	 * 设置：是否支付
	 */
	 
	public void setIspay(String ispay) {
		this.ispay = ispay;
	}
	
	/**
	 * 获取：是否支付
	 */
	public String getIspay() {
		return ispay;
	}
			
}
