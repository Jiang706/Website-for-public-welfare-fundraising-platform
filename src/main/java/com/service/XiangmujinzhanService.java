package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.XiangmujinzhanEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.XiangmujinzhanVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.XiangmujinzhanView;


/**
 * 项目进展
 *
 * @author 
 * @email 
 * @date 2025-05-18 16:39:31
 */
public interface XiangmujinzhanService extends IService<XiangmujinzhanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XiangmujinzhanVO> selectListVO(Wrapper<XiangmujinzhanEntity> wrapper);
   	
   	XiangmujinzhanVO selectVO(@Param("ew") Wrapper<XiangmujinzhanEntity> wrapper);
   	
   	List<XiangmujinzhanView> selectListView(Wrapper<XiangmujinzhanEntity> wrapper);
   	
   	XiangmujinzhanView selectView(@Param("ew") Wrapper<XiangmujinzhanEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<XiangmujinzhanEntity> wrapper);

   	

}

