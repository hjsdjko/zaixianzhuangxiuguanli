
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 装修队
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/tuandui")
public class TuanduiController {
    private static final Logger logger = LoggerFactory.getLogger(TuanduiController.class);

    private static final String TABLE_NAME = "tuandui";

    @Autowired
    private TuanduiService tuanduiService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表非注册的service
    //注册表service
    @Autowired
    private YonghuService yonghuService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("装修队".equals(role))
            params.put("tuanduiId",request.getSession().getAttribute("userId"));
        params.put("tuanduiDeleteStart",1);params.put("tuanduiDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = tuanduiService.queryPage(params);

        //字典表数据转换
        List<TuanduiView> list =(List<TuanduiView>)page.getList();
        for(TuanduiView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        TuanduiEntity tuandui = tuanduiService.selectById(id);
        if(tuandui !=null){
            //entity转view
            TuanduiView view = new TuanduiView();
            BeanUtils.copyProperties( tuandui , view );//把实体数据重构到view中
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody TuanduiEntity tuandui, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,tuandui:{}",this.getClass().getName(),tuandui.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<TuanduiEntity> queryWrapper = new EntityWrapper<TuanduiEntity>()
            .eq("username", tuandui.getUsername())
            .or()
            .eq("tuandui_phone", tuandui.getTuanduiPhone())
            .andNew()
            .eq("tuandui_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TuanduiEntity tuanduiEntity = tuanduiService.selectOne(queryWrapper);
        if(tuanduiEntity==null){
            tuandui.setTuanduiDelete(1);
            tuandui.setCreateTime(new Date());
            tuandui.setPassword("123456");
            tuanduiService.insert(tuandui);
            return R.ok();
        }else {
            return R.error(511,"账户或者联系方式已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody TuanduiEntity tuandui, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,tuandui:{}",this.getClass().getName(),tuandui.toString());
        TuanduiEntity oldTuanduiEntity = tuanduiService.selectById(tuandui.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<TuanduiEntity> queryWrapper = new EntityWrapper<TuanduiEntity>()
            .notIn("id",tuandui.getId())
            .andNew()
            .eq("username", tuandui.getUsername())
            .or()
            .eq("tuandui_phone", tuandui.getTuanduiPhone())
            .andNew()
            .eq("tuandui_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TuanduiEntity tuanduiEntity = tuanduiService.selectOne(queryWrapper);
        if("".equals(tuandui.getTuanduiPhoto()) || "null".equals(tuandui.getTuanduiPhoto())){
                tuandui.setTuanduiPhoto(null);
        }
        if(tuanduiEntity==null){
            tuanduiService.updateById(tuandui);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者联系方式已经被使用");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<TuanduiEntity> oldTuanduiList =tuanduiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<TuanduiEntity> list = new ArrayList<>();
        for(Integer id:ids){
            TuanduiEntity tuanduiEntity = new TuanduiEntity();
            tuanduiEntity.setId(id);
            tuanduiEntity.setTuanduiDelete(2);
            list.add(tuanduiEntity);
        }
        if(list != null && list.size() >0){
            tuanduiService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<TuanduiEntity> tuanduiList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            TuanduiEntity tuanduiEntity = new TuanduiEntity();
//                            tuanduiEntity.setUsername(data.get(0));                    //账户 要改的
//                            //tuanduiEntity.setPassword("123456");//密码
//                            tuanduiEntity.setTuanduiName(data.get(0));                    //团队名称 要改的
//                            tuanduiEntity.setSexTypes(Integer.valueOf(data.get(0)));   //负责人性别 要改的
//                            tuanduiEntity.setTuanduiPhoto("");//详情和图片
//                            tuanduiEntity.setTuanduiPhone(data.get(0));                    //联系方式 要改的
//                            tuanduiEntity.setTuanduiEmail(data.get(0));                    //电子邮箱 要改的
//                            tuanduiEntity.setTuanduiContent("");//详情和图片
//                            tuanduiEntity.setTuanduiDelete(1);//逻辑删除字段
//                            tuanduiEntity.setCreateTime(date);//时间
                            tuanduiList.add(tuanduiEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //联系方式
                                if(seachFields.containsKey("tuanduiPhone")){
                                    List<String> tuanduiPhone = seachFields.get("tuanduiPhone");
                                    tuanduiPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> tuanduiPhone = new ArrayList<>();
                                    tuanduiPhone.add(data.get(0));//要改的
                                    seachFields.put("tuanduiPhone",tuanduiPhone);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<TuanduiEntity> tuanduiEntities_username = tuanduiService.selectList(new EntityWrapper<TuanduiEntity>().in("username", seachFields.get("username")).eq("tuandui_delete", 1));
                        if(tuanduiEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(TuanduiEntity s:tuanduiEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //联系方式
                        List<TuanduiEntity> tuanduiEntities_tuanduiPhone = tuanduiService.selectList(new EntityWrapper<TuanduiEntity>().in("tuandui_phone", seachFields.get("tuanduiPhone")).eq("tuandui_delete", 1));
                        if(tuanduiEntities_tuanduiPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(TuanduiEntity s:tuanduiEntities_tuanduiPhone){
                                repeatFields.add(s.getTuanduiPhone());
                            }
                            return R.error(511,"数据库的该表中的 [联系方式] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        tuanduiService.insertBatch(tuanduiList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }


    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        TuanduiEntity tuandui = tuanduiService.selectOne(new EntityWrapper<TuanduiEntity>().eq("username", username));
        if(tuandui==null || !tuandui.getPassword().equals(password))
            return R.error("账号或密码不正确");
        else if(tuandui.getTuanduiDelete() != 1)
            return R.error("账户已被删除");
        String token = tokenService.generateToken(tuandui.getId(),username, "tuandui", "装修队");
        R r = R.ok();
        r.put("token", token);
        r.put("role","装修队");
        r.put("username",tuandui.getTuanduiName());
        r.put("tableName","tuandui");
        r.put("userId",tuandui.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody TuanduiEntity tuandui, HttpServletRequest request) {
//    	ValidatorUtils.validateEntity(user);
        Wrapper<TuanduiEntity> queryWrapper = new EntityWrapper<TuanduiEntity>()
            .eq("username", tuandui.getUsername())
            .or()
            .eq("tuandui_phone", tuandui.getTuanduiPhone())
            .andNew()
            .eq("tuandui_delete", 1)
            ;
        TuanduiEntity tuanduiEntity = tuanduiService.selectOne(queryWrapper);
        if(tuanduiEntity != null)
            return R.error("账户或者联系方式已经被使用");
        tuandui.setTuanduiDelete(1);
        tuandui.setCreateTime(new Date());
        tuanduiService.insert(tuandui);

        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id, HttpServletRequest request) {
        TuanduiEntity tuandui = tuanduiService.selectById(id);
        tuandui.setPassword("123456");
        tuanduiService.updateById(tuandui);
        return R.ok();
    }


    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        TuanduiEntity tuandui = tuanduiService.selectOne(new EntityWrapper<TuanduiEntity>().eq("username", username));
        if(tuandui!=null){
            tuandui.setPassword("123456");
            boolean b = tuanduiService.updateById(tuandui);
            if(!b){
               return R.error();
            }
            return R.ok();
        }else{
           return R.error("账号不存在");
        }
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrTuandui(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        TuanduiEntity tuandui = tuanduiService.selectById(id);
        if(tuandui !=null){
            //entity转view
            TuanduiView view = new TuanduiView();
            BeanUtils.copyProperties( tuandui , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }



    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = tuanduiService.queryPage(params);

        //字典表数据转换
        List<TuanduiView> list =(List<TuanduiView>)page.getList();
        for(TuanduiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        TuanduiEntity tuandui = tuanduiService.selectById(id);
            if(tuandui !=null){


                //entity转view
                TuanduiView view = new TuanduiView();
                BeanUtils.copyProperties( tuandui , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody TuanduiEntity tuandui, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,tuandui:{}",this.getClass().getName(),tuandui.toString());
        Wrapper<TuanduiEntity> queryWrapper = new EntityWrapper<TuanduiEntity>()
            .eq("username", tuandui.getUsername())
            .or()
            .eq("tuandui_phone", tuandui.getTuanduiPhone())
            .andNew()
            .eq("tuandui_delete", 1)
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TuanduiEntity tuanduiEntity = tuanduiService.selectOne(queryWrapper);
        if(tuanduiEntity==null){
            tuandui.setTuanduiDelete(1);
            tuandui.setCreateTime(new Date());
        tuandui.setPassword("123456");
        tuanduiService.insert(tuandui);

            return R.ok();
        }else {
            return R.error(511,"账户或者联系方式已经被使用");
        }
    }

}
