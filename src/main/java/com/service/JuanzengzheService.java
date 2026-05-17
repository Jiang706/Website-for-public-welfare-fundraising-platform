package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.JuanzengzheEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.JuanzengzheVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.JuanzengzheView;


/**
 * 捐赠者
 *
 * @author 
 * @email 
 * @date 2025-05-18 16:39:30
 */
public interface JuanzengzheService extends IService<JuanzengzheEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JuanzengzheVO> selectListVO(Wrapper<JuanzengzheEntity> wrapper);
   	
   	JuanzengzheVO selectVO(@Param("ew") Wrapper<JuanzengzheEntity> wrapper);
   	
   	List<JuanzengzheView> selectListView(Wrapper<JuanzengzheEntity> wrapper);
   	
   	JuanzengzheView selectView(@Param("ew") Wrapper<JuanzengzheEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JuanzengzheEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<JuanzengzheEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<JuanzengzheEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<JuanzengzheEntity> wrapper);



}

