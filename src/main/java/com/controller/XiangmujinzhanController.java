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

import com.entity.XiangmujinzhanEntity;
import com.entity.view.XiangmujinzhanView;

import com.service.XiangmujinzhanService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 项目进展
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-18 16:39:31
 */
@RestController
@RequestMapping("/xiangmujinzhan")
public class XiangmujinzhanController {
    @Autowired
    private XiangmujinzhanService xiangmujinzhanService;

    @Autowired
    private StoreupService storeupService;





    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XiangmujinzhanEntity xiangmujinzhan,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("mujuanzhe")) {
			xiangmujinzhan.setMujuanzhezhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<XiangmujinzhanEntity> ew = new EntityWrapper<XiangmujinzhanEntity>();


        //查询结果
		PageUtils page = xiangmujinzhanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xiangmujinzhan), params), params));
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
    public R list(@RequestParam Map<String, Object> params,XiangmujinzhanEntity xiangmujinzhan, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<XiangmujinzhanEntity> ew = new EntityWrapper<XiangmujinzhanEntity>();

        //查询结果
		PageUtils page = xiangmujinzhanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xiangmujinzhan), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XiangmujinzhanEntity xiangmujinzhan){
       	EntityWrapper<XiangmujinzhanEntity> ew = new EntityWrapper<XiangmujinzhanEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xiangmujinzhan, "xiangmujinzhan")); 
        return R.ok().put("data", xiangmujinzhanService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XiangmujinzhanEntity xiangmujinzhan){
        EntityWrapper< XiangmujinzhanEntity> ew = new EntityWrapper< XiangmujinzhanEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xiangmujinzhan, "xiangmujinzhan")); 
		XiangmujinzhanView xiangmujinzhanView =  xiangmujinzhanService.selectView(ew);
		return R.ok("查询项目进展成功").put("data", xiangmujinzhanView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XiangmujinzhanEntity xiangmujinzhan = xiangmujinzhanService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xiangmujinzhan,deSens);
        return R.ok().put("data", xiangmujinzhan);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XiangmujinzhanEntity xiangmujinzhan = xiangmujinzhanService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xiangmujinzhan,deSens);
        return R.ok().put("data", xiangmujinzhan);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        XiangmujinzhanEntity xiangmujinzhan = xiangmujinzhanService.selectById(id);
        if(type.equals("1")) {
        	xiangmujinzhan.setThumbsupnum(xiangmujinzhan.getThumbsupnum()+1);
        } else {
        	xiangmujinzhan.setCrazilynum(xiangmujinzhan.getCrazilynum()+1);
        }
        xiangmujinzhanService.updateById(xiangmujinzhan);
        return R.ok("投票成功");
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增项目进展") 
    public R save(@RequestBody XiangmujinzhanEntity xiangmujinzhan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xiangmujinzhan);
        xiangmujinzhanService.insert(xiangmujinzhan);
        return R.ok().put("data",xiangmujinzhan.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增项目进展")
    @RequestMapping("/add")
    public R add(@RequestBody XiangmujinzhanEntity xiangmujinzhan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xiangmujinzhan);
        xiangmujinzhanService.insert(xiangmujinzhan);
        return R.ok().put("data",xiangmujinzhan.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改项目进展")
    public R update(@RequestBody XiangmujinzhanEntity xiangmujinzhan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xiangmujinzhan);
        //全部更新
        xiangmujinzhanService.updateById(xiangmujinzhan);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除项目进展")
    public R delete(@RequestBody Long[] ids){
        xiangmujinzhanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
