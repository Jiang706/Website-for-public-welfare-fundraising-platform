package com.dao;

import com.entity.MujuanxiangmuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.MujuanxiangmuVO;
import com.entity.view.MujuanxiangmuView;


/**
 * 募捐项目
 * 
 * @author 
 * @email 
 * @date 2025-05-18 16:39:30
 */
public interface MujuanxiangmuDao extends BaseMapper<MujuanxiangmuEntity> {
	
	List<MujuanxiangmuVO> selectListVO(@Param("ew") Wrapper<MujuanxiangmuEntity> wrapper);
	
	MujuanxiangmuVO selectVO(@Param("ew") Wrapper<MujuanxiangmuEntity> wrapper);
	
	List<MujuanxiangmuView> selectListView(@Param("ew") Wrapper<MujuanxiangmuEntity> wrapper);

	List<MujuanxiangmuView> selectListView(Pagination page,@Param("ew") Wrapper<MujuanxiangmuEntity> wrapper);

	
	MujuanxiangmuView selectView(@Param("ew") Wrapper<MujuanxiangmuEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<MujuanxiangmuEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<MujuanxiangmuEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<MujuanxiangmuEntity> wrapper);



}
