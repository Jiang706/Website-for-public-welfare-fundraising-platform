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


import com.dao.MujuanxiangmuDao;
import com.entity.MujuanxiangmuEntity;
import com.service.MujuanxiangmuService;
import com.entity.vo.MujuanxiangmuVO;
import com.entity.view.MujuanxiangmuView;

@Service("mujuanxiangmuService")
public class MujuanxiangmuServiceImpl extends ServiceImpl<MujuanxiangmuDao, MujuanxiangmuEntity> implements MujuanxiangmuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MujuanxiangmuEntity> page = this.selectPage(
                new Query<MujuanxiangmuEntity>(params).getPage(),
                new EntityWrapper<MujuanxiangmuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<MujuanxiangmuEntity> wrapper) {
		  Page<MujuanxiangmuView> page =new Query<MujuanxiangmuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<MujuanxiangmuVO> selectListVO(Wrapper<MujuanxiangmuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public MujuanxiangmuVO selectVO(Wrapper<MujuanxiangmuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<MujuanxiangmuView> selectListView(Wrapper<MujuanxiangmuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public MujuanxiangmuView selectView(Wrapper<MujuanxiangmuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<MujuanxiangmuEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<MujuanxiangmuEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<MujuanxiangmuEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
