package com.dao;

import com.entity.DiscussxiangmujinzhanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussxiangmujinzhanVO;
import com.entity.view.DiscussxiangmujinzhanView;


/**
 * 项目进展评论表
 * 
 * @author 
 * @email 
 * @date 2025-05-18 16:39:32
 */
public interface DiscussxiangmujinzhanDao extends BaseMapper<DiscussxiangmujinzhanEntity> {
	
	List<DiscussxiangmujinzhanVO> selectListVO(@Param("ew") Wrapper<DiscussxiangmujinzhanEntity> wrapper);
	
	DiscussxiangmujinzhanVO selectVO(@Param("ew") Wrapper<DiscussxiangmujinzhanEntity> wrapper);
	
	List<DiscussxiangmujinzhanView> selectListView(@Param("ew") Wrapper<DiscussxiangmujinzhanEntity> wrapper);

	List<DiscussxiangmujinzhanView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussxiangmujinzhanEntity> wrapper);

	
	DiscussxiangmujinzhanView selectView(@Param("ew") Wrapper<DiscussxiangmujinzhanEntity> wrapper);
	

}
