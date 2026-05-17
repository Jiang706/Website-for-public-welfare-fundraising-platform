package com.dao;

import com.entity.XiangmujinzhanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.XiangmujinzhanVO;
import com.entity.view.XiangmujinzhanView;


/**
 * 项目进展
 * 
 * @author 
 * @email 
 * @date 2025-05-18 16:39:31
 */
public interface XiangmujinzhanDao extends BaseMapper<XiangmujinzhanEntity> {
	
	List<XiangmujinzhanVO> selectListVO(@Param("ew") Wrapper<XiangmujinzhanEntity> wrapper);
	
	XiangmujinzhanVO selectVO(@Param("ew") Wrapper<XiangmujinzhanEntity> wrapper);
	
	List<XiangmujinzhanView> selectListView(@Param("ew") Wrapper<XiangmujinzhanEntity> wrapper);

	List<XiangmujinzhanView> selectListView(Pagination page,@Param("ew") Wrapper<XiangmujinzhanEntity> wrapper);

	
	XiangmujinzhanView selectView(@Param("ew") Wrapper<XiangmujinzhanEntity> wrapper);
	

}
