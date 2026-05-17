package com.entity.view;

import com.entity.MujuanzheEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 募捐者
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-05-18 16:39:30
 */
@TableName("mujuanzhe")
public class MujuanzheView  extends MujuanzheEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public MujuanzheView(){
	}
 
 	public MujuanzheView(MujuanzheEntity mujuanzheEntity){
 	try {
			BeanUtils.copyProperties(this, mujuanzheEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
