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


import com.dao.MujuanzheDao;
import com.entity.MujuanzheEntity;
import com.service.MujuanzheService;
import com.entity.vo.MujuanzheVO;
import com.entity.view.MujuanzheView;

@Service("mujuanzheService")
public class MujuanzheServiceImpl extends ServiceImpl<MujuanzheDao, MujuanzheEntity> implements MujuanzheService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MujuanzheEntity> page = this.selectPage(
                new Query<MujuanzheEntity>(params).getPage(),
                new EntityWrapper<MujuanzheEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<MujuanzheEntity> wrapper) {
		  Page<MujuanzheView> page =new Query<MujuanzheView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<MujuanzheVO> selectListVO(Wrapper<MujuanzheEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public MujuanzheVO selectVO(Wrapper<MujuanzheEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<MujuanzheView> selectListView(Wrapper<MujuanzheEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public MujuanzheView selectView(Wrapper<MujuanzheEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<MujuanzheEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<MujuanzheEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<MujuanzheEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
