package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.DiscussmujuanxiangmuDao;
import com.entity.DiscussmujuanxiangmuEntity;
import com.service.DiscussmujuanxiangmuService;
import com.entity.vo.DiscussmujuanxiangmuVO;
import com.entity.view.DiscussmujuanxiangmuView;

@Service("discussmujuanxiangmuService")
public class DiscussmujuanxiangmuServiceImpl extends ServiceImpl<DiscussmujuanxiangmuDao, DiscussmujuanxiangmuEntity> implements DiscussmujuanxiangmuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussmujuanxiangmuEntity> page = this.selectPage(
                new Query<DiscussmujuanxiangmuEntity>(params).getPage(),
                new EntityWrapper<DiscussmujuanxiangmuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussmujuanxiangmuEntity> wrapper) {
		  Page<DiscussmujuanxiangmuView> page =new Query<DiscussmujuanxiangmuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussmujuanxiangmuVO> selectListVO(Wrapper<DiscussmujuanxiangmuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussmujuanxiangmuVO selectVO(Wrapper<DiscussmujuanxiangmuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussmujuanxiangmuView> selectListView(Wrapper<DiscussmujuanxiangmuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussmujuanxiangmuView selectView(Wrapper<DiscussmujuanxiangmuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
