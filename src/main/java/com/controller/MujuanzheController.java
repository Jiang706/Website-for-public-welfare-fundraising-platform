package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.entity.TokenEntity;
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

import com.entity.MujuanzheEntity;
import com.entity.view.MujuanzheView;

import com.service.MujuanzheService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 募捐者
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-18 16:39:30
 */
@RestController
@RequestMapping("/mujuanzhe")
public class MujuanzheController {
    @Autowired
    private MujuanzheService mujuanzheService;






    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        MujuanzheEntity u = mujuanzheService.selectOne(new EntityWrapper<MujuanzheEntity>().eq("mujuanzhezhanghao", username));
        // 判断用户锁定状态
        if(u!=null && u.getStatus().intValue()==1) {
            //返回已锁定提示
            return R.error("账号已锁定，请联系管理员。");
        }
        // 当用户不存在或sha-2方式验证密码不通过时
        if(u==null || !u.getMima().equals(EncryptUtil.sha256(password))) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
        // 如果用户是待审核状态，返回请联系管理员审核提示
        if(!"是".equals(u.getSfsh())) return R.error("账号已锁定，请联系管理员审核。");
        // 获取登录token
		String token = tokenService.generateToken(u.getId(), username,"mujuanzhe",  "募捐者" );
        //返回token
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody MujuanzheEntity mujuanzhe){
    	//ValidatorUtils.validateEntity(mujuanzhe);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	MujuanzheEntity u = mujuanzheService.selectOne(new EntityWrapper<MujuanzheEntity>().eq("mujuanzhezhanghao", mujuanzhe.getMujuanzhezhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同募捐者账号，否则返回错误信息
        if(mujuanzheService.selectCount(new EntityWrapper<MujuanzheEntity>().eq("mujuanzhezhanghao", mujuanzhe.getMujuanzhezhanghao()))>0) {
            return R.error("募捐者账号已存在");
        }
		Long uId = new Date().getTime();
		mujuanzhe.setId(uId);
        //设置登录密码sha-2方式加密
        mujuanzhe.setMima(EncryptUtil.sha256(mujuanzhe.getMima()));
        //保存用户
        mujuanzheService.insert(mujuanzhe);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        MujuanzheEntity u = mujuanzheService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        MujuanzheEntity u = mujuanzheService.selectOne(new EntityWrapper<MujuanzheEntity>().eq("mujuanzhezhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456，并使用sha-2方式加密
        u.setMima(EncryptUtil.sha256("123456"));
        mujuanzheService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,MujuanzheEntity mujuanzhe,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<MujuanzheEntity> ew = new EntityWrapper<MujuanzheEntity>();


        //查询结果
		PageUtils page = mujuanzheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, mujuanzhe), params), params));
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
    public R list(@RequestParam Map<String, Object> params,MujuanzheEntity mujuanzhe, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<MujuanzheEntity> ew = new EntityWrapper<MujuanzheEntity>();

        //查询结果
		PageUtils page = mujuanzheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, mujuanzhe), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( MujuanzheEntity mujuanzhe){
       	EntityWrapper<MujuanzheEntity> ew = new EntityWrapper<MujuanzheEntity>();
      	ew.allEq(MPUtil.allEQMapPre( mujuanzhe, "mujuanzhe")); 
        return R.ok().put("data", mujuanzheService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(MujuanzheEntity mujuanzhe){
        EntityWrapper< MujuanzheEntity> ew = new EntityWrapper< MujuanzheEntity>();
 		ew.allEq(MPUtil.allEQMapPre( mujuanzhe, "mujuanzhe")); 
		MujuanzheView mujuanzheView =  mujuanzheService.selectView(ew);
		return R.ok("查询募捐者成功").put("data", mujuanzheView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        MujuanzheEntity mujuanzhe = mujuanzheService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(mujuanzhe,deSens);
        return R.ok().put("data", mujuanzhe);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        MujuanzheEntity mujuanzhe = mujuanzheService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(mujuanzhe,deSens);
        return R.ok().put("data", mujuanzhe);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增募捐者") 
    public R save(@RequestBody MujuanzheEntity mujuanzhe, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(mujuanzheService.selectCount(new EntityWrapper<MujuanzheEntity>().eq("mujuanzhezhanghao", mujuanzhe.getMujuanzhezhanghao()))>0) {
            return R.error("募捐者账号已存在");
        }
        //ValidatorUtils.validateEntity(mujuanzhe);
        //验证账号唯一性，否则返回错误信息
        MujuanzheEntity u = mujuanzheService.selectOne(new EntityWrapper<MujuanzheEntity>().eq("mujuanzhezhanghao", mujuanzhe.getMujuanzhezhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	mujuanzhe.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		mujuanzhe.setId(new Date().getTime());
        //密码使用sha-2方式加密
        mujuanzhe.setMima(EncryptUtil.sha256(mujuanzhe.getMima()));
        mujuanzheService.insert(mujuanzhe);
        return R.ok().put("data",mujuanzhe.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增募捐者")
    @RequestMapping("/add")
    public R add(@RequestBody MujuanzheEntity mujuanzhe, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(mujuanzheService.selectCount(new EntityWrapper<MujuanzheEntity>().eq("mujuanzhezhanghao", mujuanzhe.getMujuanzhezhanghao()))>0) {
            return R.error("募捐者账号已存在");
        }
        //ValidatorUtils.validateEntity(mujuanzhe);
        //验证账号唯一性，否则返回错误信息
        MujuanzheEntity u = mujuanzheService.selectOne(new EntityWrapper<MujuanzheEntity>().eq("mujuanzhezhanghao", mujuanzhe.getMujuanzhezhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	mujuanzhe.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		mujuanzhe.setId(new Date().getTime());
        //密码使用sha-2方式加密
        mujuanzhe.setMima(EncryptUtil.sha256(mujuanzhe.getMima()));
        mujuanzheService.insert(mujuanzhe);
        return R.ok().put("data",mujuanzhe.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改募捐者")
    public R update(@RequestBody MujuanzheEntity mujuanzhe, HttpServletRequest request){
        //ValidatorUtils.validateEntity(mujuanzhe);
        //验证字段唯一性，否则返回错误信息
        if(mujuanzheService.selectCount(new EntityWrapper<MujuanzheEntity>().ne("id", mujuanzhe.getId()).eq("mujuanzhezhanghao", mujuanzhe.getMujuanzhezhanghao()))>0) {
            return R.error("募捐者账号已存在");
        }
	    MujuanzheEntity mujuanzheEntity = mujuanzheService.selectById(mujuanzhe.getId());
        //如果密码不为空，则判断是否和输入密码一致，不一致则重新设置
        if(StringUtils.isNotBlank(mujuanzhe.getMima()) && !mujuanzhe.getMima().equals(mujuanzheEntity.getMima())) {
            //密码使用sha-2方式加密
            mujuanzhe.setMima(EncryptUtil.sha256(mujuanzhe.getMima()));
        }
        //全部更新
        mujuanzheService.updateById(mujuanzhe);
        if(null!=mujuanzhe.getMujuanzhezhanghao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(mujuanzhe.getMujuanzhezhanghao());
            tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", mujuanzhe.getId()));
        }
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    @SysLog("审核募捐者")
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<MujuanzheEntity> list = new ArrayList<MujuanzheEntity>();
        for(Long id : ids) {
            MujuanzheEntity mujuanzhe = mujuanzheService.selectById(id);
            mujuanzhe.setSfsh(sfsh);
            mujuanzhe.setShhf(shhf);
            list.add(mujuanzhe);
        }
        mujuanzheService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除募捐者")
    public R delete(@RequestBody Long[] ids){
        mujuanzheService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    






    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_mujuanzhe_" + xColumnName + "_" + yColumnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        //构建查询统计条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        EntityWrapper<MujuanzheEntity> ew = new EntityWrapper<MujuanzheEntity>();
            //获取结果
        List<Map<String, Object>> result = mujuanzheService.selectValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        Collections.sort(result, (map1, map2) -> {
            // 假设 total 总是存在并且是数值类型
            Number total1 = (Number) map1.get("total");
            Number total2 = (Number) map2.get("total");
            if(total1==null)
            {
                total1 = 0;
            }
            if(total2==null)
            {
                total2 = 0;
            }
            return Double.compare(total2.doubleValue(), total1.doubleValue());
        });
        return R.ok().put("data", result);
    }
    
    /**
     * （按值统计(多)）
     */
    @RequestMapping("/valueMul/{xColumnName}")
    public R valueMul(@PathVariable("xColumnName") String xColumnName,@RequestParam String yColumnNameMul,HttpServletRequest request)  throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_mujuanzhe_" + xColumnName + "_" + yColumnNameMul + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //构建查询统计条件
        EntityWrapper<MujuanzheEntity> ew = new EntityWrapper<MujuanzheEntity>();
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = mujuanzheService.selectValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }
    
    /**
     * （按值统计）时间统计类型
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
    public R valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_mujuanzhe_" + xColumnName + "_" + yColumnName + "_"+timeStatType+".json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("timeStatType", timeStatType);
        //构建查询统计条件
        EntityWrapper<MujuanzheEntity> ew = new EntityWrapper<MujuanzheEntity>();
        List<Map<String, Object>> result = mujuanzheService.selectTimeStatValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }
    
    /**
     * （按值统计）时间统计类型(多)
     */
    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
    public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,@RequestParam String yColumnNameMul,HttpServletRequest request) throws IOException
    {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_mujuanzhe_" + xColumnName + "_" + yColumnNameMul + "_" + timeStatType + ".json");
        if (java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //构建查询统计条件
        EntityWrapper<MujuanzheEntity> ew = new EntityWrapper<MujuanzheEntity>();
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = mujuanzheService.selectTimeStatValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }
    
    /**
     * 分组统计
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName,HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("group_mujuanzhe_" + columnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)){
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", columnName);
        //构建查询统计条件
        EntityWrapper<MujuanzheEntity> ew = new EntityWrapper<MujuanzheEntity>();
        List<Map<String, Object>> result = mujuanzheService.selectGroup(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }    




    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,MujuanzheEntity mujuanzhe, HttpServletRequest request){
        EntityWrapper<MujuanzheEntity> ew = new EntityWrapper<MujuanzheEntity>();
        int count = mujuanzheService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, mujuanzhe), params), params));
        return R.ok().put("data", count);
    }

}
