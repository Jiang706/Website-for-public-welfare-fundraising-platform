package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussmujuanxiangmuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussmujuanxiangmuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussmujuanxiangmuView;


/**
 * 募捐项目评论表
 *
 * @author 
 * @email 
 * @date 2025-05-18 16:39:31
 */
public interface DiscussmujuanxiangmuService extends IService<DiscussmujuanxiangmuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussmujuanxiangmuVO> selectListVO(Wrapper<DiscussmujuanxiangmuEntity> wrapper);
   	
   	DiscussmujuanxiangmuVO selectVO(@Param("ew") Wrapper<DiscussmujuanxiangmuEntity> wrapper);
   	
   	List<DiscussmujuanxiangmuView> selectListView(Wrapper<DiscussmujuanxiangmuEntity> wrapper);
   	
   	DiscussmujuanxiangmuView selectView(@Param("ew") Wrapper<DiscussmujuanxiangmuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussmujuanxiangmuEntity> wrapper);

   	

}

