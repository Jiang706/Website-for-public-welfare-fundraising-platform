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

import com.entity.JuanzengzheEntity;
import com.entity.view.JuanzengzheView;

import com.service.JuanzengzheService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 捐赠者
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-18 16:39:30
 */
@RestController
@RequestMapping("/juanzengzhe")
public class JuanzengzheController {
    @Autowired
    private JuanzengzheService juanzengzheService;






    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        JuanzengzheEntity u = juanzengzheService.selectOne(new EntityWrapper<JuanzengzheEntity>().eq("juanzengzhanghao", username));
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
        // 获取登录token
		String token = tokenService.generateToken(u.getId(), username,"juanzengzhe",  "捐赠者" );
        //返回token
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody JuanzengzheEntity juanzengzhe){
    	//ValidatorUtils.validateEntity(juanzengzhe);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	JuanzengzheEntity u = juanzengzheService.selectOne(new EntityWrapper<JuanzengzheEntity>().eq("juanzengzhanghao", juanzengzhe.getJuanzengzhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同捐赠账号，否则返回错误信息
        if(juanzengzheService.selectCount(new EntityWrapper<JuanzengzheEntity>().eq("juanzengzhanghao", juanzengzhe.getJuanzengzhanghao()))>0) {
            return R.error("捐赠账号已存在");
        }
		Long uId = new Date().getTime();
		juanzengzhe.setId(uId);
        //设置登录密码sha-2方式加密
        juanzengzhe.setMima(EncryptUtil.sha256(juanzengzhe.getMima()));
        //保存用户
        juanzengzheService.insert(juanzengzhe);
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
        JuanzengzheEntity u = juanzengzheService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        JuanzengzheEntity u = juanzengzheService.selectOne(new EntityWrapper<JuanzengzheEntity>().eq("juanzengzhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456，并使用sha-2方式加密
        u.setMima(EncryptUtil.sha256("123456"));
        juanzengzheService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JuanzengzheEntity juanzengzhe,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<JuanzengzheEntity> ew = new EntityWrapper<JuanzengzheEntity>();


        //查询结果
		PageUtils page = juanzengzheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, juanzengzhe), params), params));
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
    public R list(@RequestParam Map<String, Object> params,JuanzengzheEntity juanzengzhe, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<JuanzengzheEntity> ew = new EntityWrapper<JuanzengzheEntity>();

        //查询结果
		PageUtils page = juanzengzheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, juanzengzhe), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JuanzengzheEntity juanzengzhe){
       	EntityWrapper<JuanzengzheEntity> ew = new EntityWrapper<JuanzengzheEntity>();
      	ew.allEq(MPUtil.allEQMapPre( juanzengzhe, "juanzengzhe")); 
        return R.ok().put("data", juanzengzheService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JuanzengzheEntity juanzengzhe){
        EntityWrapper< JuanzengzheEntity> ew = new EntityWrapper< JuanzengzheEntity>();
 		ew.allEq(MPUtil.allEQMapPre( juanzengzhe, "juanzengzhe")); 
		JuanzengzheView juanzengzheView =  juanzengzheService.selectView(ew);
		return R.ok("查询捐赠者成功").put("data", juanzengzheView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JuanzengzheEntity juanzengzhe = juanzengzheService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(juanzengzhe,deSens);
        return R.ok().put("data", juanzengzhe);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JuanzengzheEntity juanzengzhe = juanzengzheService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(juanzengzhe,deSens);
        return R.ok().put("data", juanzengzhe);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增捐赠者") 
    public R save(@RequestBody JuanzengzheEntity juanzengzhe, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(juanzengzheService.selectCount(new EntityWrapper<JuanzengzheEntity>().eq("juanzengzhanghao", juanzengzhe.getJuanzengzhanghao()))>0) {
            return R.error("捐赠账号已存在");
        }
        //ValidatorUtils.validateEntity(juanzengzhe);
        //验证账号唯一性，否则返回错误信息
        JuanzengzheEntity u = juanzengzheService.selectOne(new EntityWrapper<JuanzengzheEntity>().eq("juanzengzhanghao", juanzengzhe.getJuanzengzhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	juanzengzhe.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		juanzengzhe.setId(new Date().getTime());
        //密码使用sha-2方式加密
        juanzengzhe.setMima(EncryptUtil.sha256(juanzengzhe.getMima()));
        juanzengzheService.insert(juanzengzhe);
        return R.ok().put("data",juanzengzhe.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增捐赠者")
    @RequestMapping("/add")
    public R add(@RequestBody JuanzengzheEntity juanzengzhe, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(juanzengzheService.selectCount(new EntityWrapper<JuanzengzheEntity>().eq("juanzengzhanghao", juanzengzhe.getJuanzengzhanghao()))>0) {
            return R.error("捐赠账号已存在");
        }
        //ValidatorUtils.validateEntity(juanzengzhe);
        //验证账号唯一性，否则返回错误信息
        JuanzengzheEntity u = juanzengzheService.selectOne(new EntityWrapper<JuanzengzheEntity>().eq("juanzengzhanghao", juanzengzhe.getJuanzengzhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	juanzengzhe.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		juanzengzhe.setId(new Date().getTime());
        //密码使用sha-2方式加密
        juanzengzhe.setMima(EncryptUtil.sha256(juanzengzhe.getMima()));
        juanzengzheService.insert(juanzengzhe);
        return R.ok().put("data",juanzengzhe.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改捐赠者")
    public R update(@RequestBody JuanzengzheEntity juanzengzhe, HttpServletRequest request){
        //ValidatorUtils.validateEntity(juanzengzhe);
        //验证字段唯一性，否则返回错误信息
        if(juanzengzheService.selectCount(new EntityWrapper<JuanzengzheEntity>().ne("id", juanzengzhe.getId()).eq("juanzengzhanghao", juanzengzhe.getJuanzengzhanghao()))>0) {
            return R.error("捐赠账号已存在");
        }
	    JuanzengzheEntity juanzengzheEntity = juanzengzheService.selectById(juanzengzhe.getId());
        //如果密码不为空，则判断是否和输入密码一致，不一致则重新设置
        if(StringUtils.isNotBlank(juanzengzhe.getMima()) && !juanzengzhe.getMima().equals(juanzengzheEntity.getMima())) {
            //密码使用sha-2方式加密
            juanzengzhe.setMima(EncryptUtil.sha256(juanzengzhe.getMima()));
        }
        //全部更新
        juanzengzheService.updateById(juanzengzhe);
        if(null!=juanzengzhe.getJuanzengzhanghao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(juanzengzhe.getJuanzengzhanghao());
            tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", juanzengzhe.getId()));
        }
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除捐赠者")
    public R delete(@RequestBody Long[] ids){
        juanzengzheService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    






    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_juanzengzhe_" + xColumnName + "_" + yColumnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        //构建查询统计条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        EntityWrapper<JuanzengzheEntity> ew = new EntityWrapper<JuanzengzheEntity>();
            //获取结果
        List<Map<String, Object>> result = juanzengzheService.selectValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("value_juanzengzhe_" + xColumnName + "_" + yColumnNameMul + "_timeType.json");
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
        EntityWrapper<JuanzengzheEntity> ew = new EntityWrapper<JuanzengzheEntity>();
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = juanzengzheService.selectValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("value_juanzengzhe_" + xColumnName + "_" + yColumnName + "_"+timeStatType+".json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("timeStatType", timeStatType);
        //构建查询统计条件
        EntityWrapper<JuanzengzheEntity> ew = new EntityWrapper<JuanzengzheEntity>();
        List<Map<String, Object>> result = juanzengzheService.selectTimeStatValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("value_juanzengzhe_" + xColumnName + "_" + yColumnNameMul + "_" + timeStatType + ".json");
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
        EntityWrapper<JuanzengzheEntity> ew = new EntityWrapper<JuanzengzheEntity>();
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = juanzengzheService.selectTimeStatValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("group_juanzengzhe_" + columnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)){
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", columnName);
        //构建查询统计条件
        EntityWrapper<JuanzengzheEntity> ew = new EntityWrapper<JuanzengzheEntity>();
        List<Map<String, Object>> result = juanzengzheService.selectGroup(params, ew);
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
    public R count(@RequestParam Map<String, Object> params,JuanzengzheEntity juanzengzhe, HttpServletRequest request){
        EntityWrapper<JuanzengzheEntity> ew = new EntityWrapper<JuanzengzheEntity>();
        int count = juanzengzheService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, juanzengzhe), params), params));
        return R.ok().put("data", count);
    }

}
