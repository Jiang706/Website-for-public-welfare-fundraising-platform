package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.MujuanzheEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.MujuanzheVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.MujuanzheView;


/**
 * 募捐者
 *
 * @author 
 * @email 
 * @date 2025-05-18 16:39:30
 */
public interface MujuanzheService extends IService<MujuanzheEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<MujuanzheVO> selectListVO(Wrapper<MujuanzheEntity> wrapper);
   	
   	MujuanzheVO selectVO(@Param("ew") Wrapper<MujuanzheEntity> wrapper);
   	
   	List<MujuanzheView> selectListView(Wrapper<MujuanzheEntity> wrapper);
   	
   	MujuanzheView selectView(@Param("ew") Wrapper<MujuanzheEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<MujuanzheEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<MujuanzheEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<MujuanzheEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<MujuanzheEntity> wrapper);



}

