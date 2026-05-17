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


import com.dao.DiscussxiangmujinzhanDao;
import com.entity.DiscussxiangmujinzhanEntity;
import com.service.DiscussxiangmujinzhanService;
import com.entity.vo.DiscussxiangmujinzhanVO;
import com.entity.view.DiscussxiangmujinzhanView;

@Service("discussxiangmujinzhanService")
public class DiscussxiangmujinzhanServiceImpl extends ServiceImpl<DiscussxiangmujinzhanDao, DiscussxiangmujinzhanEntity> implements DiscussxiangmujinzhanService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussxiangmujinzhanEntity> page = this.selectPage(
                new Query<DiscussxiangmujinzhanEntity>(params).getPage(),
                new EntityWrapper<DiscussxiangmujinzhanEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussxiangmujinzhanEntity> wrapper) {
		  Page<DiscussxiangmujinzhanView> page =new Query<DiscussxiangmujinzhanView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussxiangmujinzhanVO> selectListVO(Wrapper<DiscussxiangmujinzhanEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussxiangmujinzhanVO selectVO(Wrapper<DiscussxiangmujinzhanEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussxiangmujinzhanView> selectListView(Wrapper<DiscussxiangmujinzhanEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussxiangmujinzhanView selectView(Wrapper<DiscussxiangmujinzhanEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
