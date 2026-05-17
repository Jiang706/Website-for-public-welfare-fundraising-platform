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


import com.dao.XiangmujinzhanDao;
import com.entity.XiangmujinzhanEntity;
import com.service.XiangmujinzhanService;
import com.entity.vo.XiangmujinzhanVO;
import com.entity.view.XiangmujinzhanView;

@Service("xiangmujinzhanService")
public class XiangmujinzhanServiceImpl extends ServiceImpl<XiangmujinzhanDao, XiangmujinzhanEntity> implements XiangmujinzhanService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XiangmujinzhanEntity> page = this.selectPage(
                new Query<XiangmujinzhanEntity>(params).getPage(),
                new EntityWrapper<XiangmujinzhanEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<XiangmujinzhanEntity> wrapper) {
		  Page<XiangmujinzhanView> page =new Query<XiangmujinzhanView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<XiangmujinzhanVO> selectListVO(Wrapper<XiangmujinzhanEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public XiangmujinzhanVO selectVO(Wrapper<XiangmujinzhanEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<XiangmujinzhanView> selectListView(Wrapper<XiangmujinzhanEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public XiangmujinzhanView selectView(Wrapper<XiangmujinzhanEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
