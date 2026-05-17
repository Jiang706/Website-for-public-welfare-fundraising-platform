package com.dao;

import com.entity.MujuanzheEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.MujuanzheVO;
import com.entity.view.MujuanzheView;


/**
 * 募捐者
 * 
 * @author 
 * @email 
 * @date 2025-05-18 16:39:30
 */
public interface MujuanzheDao extends BaseMapper<MujuanzheEntity> {
	
	List<MujuanzheVO> selectListVO(@Param("ew") Wrapper<MujuanzheEntity> wrapper);
	
	MujuanzheVO selectVO(@Param("ew") Wrapper<MujuanzheEntity> wrapper);
	
	List<MujuanzheView> selectListView(@Param("ew") Wrapper<MujuanzheEntity> wrapper);

	List<MujuanzheView> selectListView(Pagination page,@Param("ew") Wrapper<MujuanzheEntity> wrapper);

	
	MujuanzheView selectView(@Param("ew") Wrapper<MujuanzheEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<MujuanzheEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<MujuanzheEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<MujuanzheEntity> wrapper);



}
