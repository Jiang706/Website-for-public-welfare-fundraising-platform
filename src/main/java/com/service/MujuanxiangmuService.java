package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.MujuanxiangmuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.MujuanxiangmuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.MujuanxiangmuView;


/**
 * 募捐项目
 *
 * @author 
 * @email 
 * @date 2025-05-18 16:39:30
 */
public interface MujuanxiangmuService extends IService<MujuanxiangmuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<MujuanxiangmuVO> selectListVO(Wrapper<MujuanxiangmuEntity> wrapper);
   	
   	MujuanxiangmuVO selectVO(@Param("ew") Wrapper<MujuanxiangmuEntity> wrapper);
   	
   	List<MujuanxiangmuView> selectListView(Wrapper<MujuanxiangmuEntity> wrapper);
   	
   	MujuanxiangmuView selectView(@Param("ew") Wrapper<MujuanxiangmuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<MujuanxiangmuEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<MujuanxiangmuEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<MujuanxiangmuEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<MujuanxiangmuEntity> wrapper);



}

