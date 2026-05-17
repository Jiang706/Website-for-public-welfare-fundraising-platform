package com.dao;

import com.entity.DiscussmujuanxiangmuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussmujuanxiangmuVO;
import com.entity.view.DiscussmujuanxiangmuView;


/**
 * 募捐项目评论表
 * 
 * @author 
 * @email 
 * @date 2025-05-18 16:39:31
 */
public interface DiscussmujuanxiangmuDao extends BaseMapper<DiscussmujuanxiangmuEntity> {
	
	List<DiscussmujuanxiangmuVO> selectListVO(@Param("ew") Wrapper<DiscussmujuanxiangmuEntity> wrapper);
	
	DiscussmujuanxiangmuVO selectVO(@Param("ew") Wrapper<DiscussmujuanxiangmuEntity> wrapper);
	
	List<DiscussmujuanxiangmuView> selectListView(@Param("ew") Wrapper<DiscussmujuanxiangmuEntity> wrapper);

	List<DiscussmujuanxiangmuView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussmujuanxiangmuEntity> wrapper);

	
	DiscussmujuanxiangmuView selectView(@Param("ew") Wrapper<DiscussmujuanxiangmuEntity> wrapper);
	

}
