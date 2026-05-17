package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussxiangmujinzhanEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussxiangmujinzhanVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussxiangmujinzhanView;


/**
 * 项目进展评论表
 *
 * @author 
 * @email 
 * @date 2025-05-18 16:39:32
 */
public interface DiscussxiangmujinzhanService extends IService<DiscussxiangmujinzhanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussxiangmujinzhanVO> selectListVO(Wrapper<DiscussxiangmujinzhanEntity> wrapper);
   	
   	DiscussxiangmujinzhanVO selectVO(@Param("ew") Wrapper<DiscussxiangmujinzhanEntity> wrapper);
   	
   	List<DiscussxiangmujinzhanView> selectListView(Wrapper<DiscussxiangmujinzhanEntity> wrapper);
   	
   	DiscussxiangmujinzhanView selectView(@Param("ew") Wrapper<DiscussxiangmujinzhanEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussxiangmujinzhanEntity> wrapper);

   	

}

