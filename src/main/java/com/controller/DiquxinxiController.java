package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;
import com.annotation.SysLog;

import com.entity.DiquxinxiEntity;
import com.entity.view.DiquxinxiView;

import com.service.DiquxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 地区信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-18 16:39:30
 */
@RestController
@RequestMapping("/diquxinxi")
public class DiquxinxiController {
    @Autowired
    private DiquxinxiService diquxinxiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiquxinxiEntity diquxinxi,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiquxinxiEntity> ew = new EntityWrapper<DiquxinxiEntity>();


        //查询结果
		PageUtils page = diquxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, diquxinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiquxinxiEntity diquxinxi, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiquxinxiEntity> ew = new EntityWrapper<DiquxinxiEntity>();

        //查询结果
		PageUtils page = diquxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, diquxinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiquxinxiEntity diquxinxi){
       	EntityWrapper<DiquxinxiEntity> ew = new EntityWrapper<DiquxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( diquxinxi, "diquxinxi")); 
        return R.ok().put("data", diquxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiquxinxiEntity diquxinxi){
        EntityWrapper< DiquxinxiEntity> ew = new EntityWrapper< DiquxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( diquxinxi, "diquxinxi")); 
		DiquxinxiView diquxinxiView =  diquxinxiService.selectView(ew);
		return R.ok("查询地区信息成功").put("data", diquxinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiquxinxiEntity diquxinxi = diquxinxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(diquxinxi,deSens);
        return R.ok().put("data", diquxinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiquxinxiEntity diquxinxi = diquxinxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(diquxinxi,deSens);
        return R.ok().put("data", diquxinxi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增地区信息") 
    public R save(@RequestBody DiquxinxiEntity diquxinxi, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(diquxinxiService.selectCount(new EntityWrapper<DiquxinxiEntity>().eq("diqu", diquxinxi.getDiqu()))>0) {
            return R.error("地区已存在");
        }
        //ValidatorUtils.validateEntity(diquxinxi);
        diquxinxiService.insert(diquxinxi);
        return R.ok().put("data",diquxinxi.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增地区信息")
    @RequestMapping("/add")
    public R add(@RequestBody DiquxinxiEntity diquxinxi, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(diquxinxiService.selectCount(new EntityWrapper<DiquxinxiEntity>().eq("diqu", diquxinxi.getDiqu()))>0) {
            return R.error("地区已存在");
        }
        //ValidatorUtils.validateEntity(diquxinxi);
        diquxinxiService.insert(diquxinxi);
        return R.ok().put("data",diquxinxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改地区信息")
    public R update(@RequestBody DiquxinxiEntity diquxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(diquxinxi);
        //验证字段唯一性，否则返回错误信息
        if(diquxinxiService.selectCount(new EntityWrapper<DiquxinxiEntity>().ne("id", diquxinxi.getId()).eq("diqu", diquxinxi.getDiqu()))>0) {
            return R.error("地区已存在");
        }
        //全部更新
        diquxinxiService.updateById(diquxinxi);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除地区信息")
    public R delete(@RequestBody Long[] ids){
        diquxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
