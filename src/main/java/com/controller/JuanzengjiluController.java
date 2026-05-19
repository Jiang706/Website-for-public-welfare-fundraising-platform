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

import com.entity.JuanzengjiluEntity;
import com.entity.view.JuanzengjiluView;

import com.service.JuanzengjiluService;
import com.service.MerkleTrustService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 捐赠记录
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-18 16:39:31
 */
@RestController
@RequestMapping("/juanzengjilu")
public class JuanzengjiluController {
    @Autowired
    private JuanzengjiluService juanzengjiluService;

    @Autowired
    private MerkleTrustService merkleTrustService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JuanzengjiluEntity juanzengjilu,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("juanzengzhe")) {
			juanzengjilu.setJuanzengzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("mujuanzhe")) {
			juanzengjilu.setMujuanzhezhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<JuanzengjiluEntity> ew = new EntityWrapper<JuanzengjiluEntity>();


        //查询结果
		PageUtils page = juanzengjiluService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, juanzengjilu), params), params));
        Map<String, String> deSens = new HashMap<>();
        deSens.put("xingming","名");
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JuanzengjiluEntity juanzengjilu, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<JuanzengjiluEntity> ew = new EntityWrapper<JuanzengjiluEntity>();

        //查询结果
		PageUtils page = juanzengjiluService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, juanzengjilu), params), params));
        Map<String, String> deSens = new HashMap<>();
        deSens.put("xingming","名");
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JuanzengjiluEntity juanzengjilu){
       	EntityWrapper<JuanzengjiluEntity> ew = new EntityWrapper<JuanzengjiluEntity>();
      	ew.allEq(MPUtil.allEQMapPre( juanzengjilu, "juanzengjilu")); 
        return R.ok().put("data", juanzengjiluService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JuanzengjiluEntity juanzengjilu){
        EntityWrapper< JuanzengjiluEntity> ew = new EntityWrapper< JuanzengjiluEntity>();
 		ew.allEq(MPUtil.allEQMapPre( juanzengjilu, "juanzengjilu")); 
		JuanzengjiluView juanzengjiluView =  juanzengjiluService.selectView(ew);
		return R.ok("查询捐赠记录成功").put("data", juanzengjiluView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JuanzengjiluEntity juanzengjilu = juanzengjiluService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        deSens.put("xingming","名");
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(juanzengjilu,deSens);
        return R.ok().put("data", juanzengjilu);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JuanzengjiluEntity juanzengjilu = juanzengjiluService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        deSens.put("xingming","名");
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(juanzengjilu,deSens);
        return R.ok().put("data", juanzengjilu);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增捐赠记录") 
    public R save(@RequestBody JuanzengjiluEntity juanzengjilu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(juanzengjilu);
        if(StringUtils.isBlank(juanzengjilu.getIspay())) {
            juanzengjilu.setIspay("未支付");
        }
        if("已支付".equals(juanzengjilu.getIspay())) {
            if(juanzengjilu.getPayTime() == null) {
                juanzengjilu.setPayTime(new Date());
            }
            juanzengjilu.setMerkleStatus(MerkleTrustService.STATUS_PENDING);
            juanzengjilu.setLeafHash(merkleTrustService.buildLeafHash(juanzengjilu));
        } else {
            juanzengjilu.setMerkleStatus(MerkleTrustService.STATUS_UNPAID);
        }
        juanzengjiluService.insert(juanzengjilu);
        return R.ok().put("data",juanzengjilu.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增捐赠记录")
    @RequestMapping("/add")
    public R add(@RequestBody JuanzengjiluEntity juanzengjilu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(juanzengjilu);
        if(StringUtils.isBlank(juanzengjilu.getIspay())) {
            juanzengjilu.setIspay("未支付");
        }
        if("已支付".equals(juanzengjilu.getIspay())) {
            if(juanzengjilu.getPayTime() == null) {
                juanzengjilu.setPayTime(new Date());
            }
            juanzengjilu.setMerkleStatus(MerkleTrustService.STATUS_PENDING);
            juanzengjilu.setLeafHash(merkleTrustService.buildLeafHash(juanzengjilu));
        } else {
            juanzengjilu.setMerkleStatus(MerkleTrustService.STATUS_UNPAID);
        }
        juanzengjiluService.insert(juanzengjilu);
        return R.ok().put("data",juanzengjilu.getId());
    }

    /**
     * 支付
     */
    @RequestMapping("/pay/{id}")
    @Transactional
    @SysLog("支付捐赠记录")
    public R pay(@PathVariable("id") Long id, HttpServletRequest request){
        JuanzengjiluEntity donation = juanzengjiluService.selectById(id);
        if(donation == null){
            return R.error("捐赠记录不存在");
        }

        Object tableObj = request.getSession().getAttribute("tableName");
        Object userObj = request.getSession().getAttribute("username");
        String tableName = tableObj == null ? "" : tableObj.toString();
        String username = userObj == null ? "" : userObj.toString();
        if("juanzengzhe".equals(tableName) && !username.equals(donation.getJuanzengzhanghao())) {
            return R.error("无权限支付该记录");
        }

        if("已支付".equals(donation.getIspay())) {
            return R.ok("该记录已支付").put("data", donation.getId());
        }

        donation.setIspay("已支付");
        donation.setPayTime(new Date());
        donation.setMerkleStatus(MerkleTrustService.STATUS_PENDING);
        donation.setMerkleBatchNo(null);
        donation.setLeafHash(merkleTrustService.buildLeafHash(donation));
        juanzengjiluService.updateById(donation);

        return R.ok().put("data", donation.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改捐赠记录")
    public R update(@RequestBody JuanzengjiluEntity juanzengjilu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(juanzengjilu);
        //全部更新
        juanzengjiluService.updateById(juanzengjilu);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除捐赠记录")
    public R delete(@RequestBody Long[] ids){
        juanzengjiluService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    






    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_juanzengjilu_" + xColumnName + "_" + yColumnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        //构建查询统计条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        EntityWrapper<JuanzengjiluEntity> ew = new EntityWrapper<JuanzengjiluEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
                                            if(tableName.equals("juanzengzhe")) {
            ew.eq("juanzengzhanghao", (String)request.getSession().getAttribute("username"));
        }
                    if(tableName.equals("mujuanzhe")) {
            ew.eq("mujuanzhezhanghao", (String)request.getSession().getAttribute("username"));
        }
                        //获取结果
        List<Map<String, Object>> result = juanzengjiluService.selectValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("value_juanzengjilu_" + xColumnName + "_" + yColumnNameMul + "_timeType.json");
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
        EntityWrapper<JuanzengjiluEntity> ew = new EntityWrapper<JuanzengjiluEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("juanzengzhe")) {
            ew.eq("juanzengzhanghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("mujuanzhe")) {
            ew.eq("mujuanzhezhanghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = juanzengjiluService.selectValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("value_juanzengjilu_" + xColumnName + "_" + yColumnName + "_"+timeStatType+".json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("timeStatType", timeStatType);
        //构建查询统计条件
        EntityWrapper<JuanzengjiluEntity> ew = new EntityWrapper<JuanzengjiluEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("juanzengzhe")) {
            ew.eq("juanzengzhanghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("mujuanzhe")) {
            ew.eq("mujuanzhezhanghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = juanzengjiluService.selectTimeStatValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("value_juanzengjilu_" + xColumnName + "_" + yColumnNameMul + "_" + timeStatType + ".json");
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
        EntityWrapper<JuanzengjiluEntity> ew = new EntityWrapper<JuanzengjiluEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("juanzengzhe")) {
            ew.eq("juanzengzhanghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("mujuanzhe")) {
            ew.eq("mujuanzhezhanghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = juanzengjiluService.selectTimeStatValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("group_juanzengjilu_" + columnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)){
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", columnName);
        //构建查询统计条件
        EntityWrapper<JuanzengjiluEntity> ew = new EntityWrapper<JuanzengjiluEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("juanzengzhe")) {
            ew.eq("juanzengzhanghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("mujuanzhe")) {
            ew.eq("mujuanzhezhanghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = juanzengjiluService.selectGroup(params, ew);
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





}
