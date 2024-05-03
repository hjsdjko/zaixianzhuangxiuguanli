
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
 * 装修
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/zhuangxiu")
public class ZhuangxiuController {
    private static final Logger logger = LoggerFactory.getLogger(ZhuangxiuController.class);

    private static final String TABLE_NAME = "zhuangxiu";

    @Autowired
    private ZhuangxiuService zhuangxiuService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private ZhuangxiuOrderService zhuangxiuOrderService;
    //级联表非注册的service
    //注册表service
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private TuanduiService tuanduiService;


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
        params.put("zhuangxiuDeleteStart",1);params.put("zhuangxiuDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = zhuangxiuService.queryPage(params);

        //字典表数据转换
        List<ZhuangxiuView> list =(List<ZhuangxiuView>)page.getList();
        for(ZhuangxiuView c:list){
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
        ZhuangxiuEntity zhuangxiu = zhuangxiuService.selectById(id);
        if(zhuangxiu !=null){
            //entity转view
            ZhuangxiuView view = new ZhuangxiuView();
            BeanUtils.copyProperties( zhuangxiu , view );//把实体数据重构到view中
            //级联表 装修队
            //级联表
            TuanduiEntity tuandui = tuanduiService.selectById(zhuangxiu.getTuanduiId());
            if(tuandui != null){
            BeanUtils.copyProperties( tuandui , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "tuanduiId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setTuanduiId(tuandui.getId());
            }
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
    public R save(@RequestBody ZhuangxiuEntity zhuangxiu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zhuangxiu:{}",this.getClass().getName(),zhuangxiu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("装修队".equals(role))
            zhuangxiu.setTuanduiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<ZhuangxiuEntity> queryWrapper = new EntityWrapper<ZhuangxiuEntity>()
            .eq("tuandui_id", zhuangxiu.getTuanduiId())
            .eq("zhuangxiu_name", zhuangxiu.getZhuangxiuName())
            .eq("zhuangxiu_types", zhuangxiu.getZhuangxiuTypes())
            .eq("zhuangxiu_clicknum", zhuangxiu.getZhuangxiuClicknum())
            .eq("shangxia_types", zhuangxiu.getShangxiaTypes())
            .eq("zhuangxiu_delete", zhuangxiu.getZhuangxiuDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhuangxiuEntity zhuangxiuEntity = zhuangxiuService.selectOne(queryWrapper);
        if(zhuangxiuEntity==null){
            zhuangxiu.setZhuangxiuClicknum(1);
            zhuangxiu.setShangxiaTypes(1);
            zhuangxiu.setZhuangxiuDelete(1);
            zhuangxiu.setInsertTime(new Date());
            zhuangxiu.setCreateTime(new Date());
            zhuangxiuService.insert(zhuangxiu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZhuangxiuEntity zhuangxiu, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,zhuangxiu:{}",this.getClass().getName(),zhuangxiu.toString());
        ZhuangxiuEntity oldZhuangxiuEntity = zhuangxiuService.selectById(zhuangxiu.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("装修队".equals(role))
//            zhuangxiu.setTuanduiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<ZhuangxiuEntity> queryWrapper = new EntityWrapper<ZhuangxiuEntity>()
            .notIn("id",zhuangxiu.getId())
            .andNew()
            .eq("tuandui_id", zhuangxiu.getTuanduiId())
            .eq("zhuangxiu_name", zhuangxiu.getZhuangxiuName())
            .eq("zhuangxiu_types", zhuangxiu.getZhuangxiuTypes())
            .eq("zhuangxiu_clicknum", zhuangxiu.getZhuangxiuClicknum())
            .eq("shangxia_types", zhuangxiu.getShangxiaTypes())
            .eq("zhuangxiu_delete", zhuangxiu.getZhuangxiuDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhuangxiuEntity zhuangxiuEntity = zhuangxiuService.selectOne(queryWrapper);
        if("".equals(zhuangxiu.getZhuangxiuPhoto()) || "null".equals(zhuangxiu.getZhuangxiuPhoto())){
                zhuangxiu.setZhuangxiuPhoto(null);
        }
        if(zhuangxiuEntity==null){
            zhuangxiuService.updateById(zhuangxiu);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ZhuangxiuEntity> oldZhuangxiuList =zhuangxiuService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<ZhuangxiuEntity> list = new ArrayList<>();
        for(Integer id:ids){
            ZhuangxiuEntity zhuangxiuEntity = new ZhuangxiuEntity();
            zhuangxiuEntity.setId(id);
            zhuangxiuEntity.setZhuangxiuDelete(2);
            list.add(zhuangxiuEntity);
        }
        if(list != null && list.size() >0){
            zhuangxiuService.updateBatchById(list);
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
            List<ZhuangxiuEntity> zhuangxiuList = new ArrayList<>();//上传的东西
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
                            ZhuangxiuEntity zhuangxiuEntity = new ZhuangxiuEntity();
//                            zhuangxiuEntity.setZhuangxiuUuidNumber(data.get(0));                    //装修编号 要改的
//                            zhuangxiuEntity.setTuanduiId(Integer.valueOf(data.get(0)));   //装修团队 要改的
//                            zhuangxiuEntity.setZhuangxiuName(data.get(0));                    //装修名称 要改的
//                            zhuangxiuEntity.setZhuangxiuPhoto("");//详情和图片
//                            zhuangxiuEntity.setZhuangxiuTypes(Integer.valueOf(data.get(0)));   //装修类型 要改的
//                            zhuangxiuEntity.setZhuangxiuNewMoney(data.get(0));                    //装修定金 要改的
//                            zhuangxiuEntity.setZhuangxiuClicknum(Integer.valueOf(data.get(0)));   //点击次数 要改的
//                            zhuangxiuEntity.setZhuangxiuContent("");//详情和图片
//                            zhuangxiuEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            zhuangxiuEntity.setZhuangxiuDelete(1);//逻辑删除字段
//                            zhuangxiuEntity.setInsertTime(date);//时间
//                            zhuangxiuEntity.setCreateTime(date);//时间
                            zhuangxiuList.add(zhuangxiuEntity);


                            //把要查询是否重复的字段放入map中
                                //装修编号
                                if(seachFields.containsKey("zhuangxiuUuidNumber")){
                                    List<String> zhuangxiuUuidNumber = seachFields.get("zhuangxiuUuidNumber");
                                    zhuangxiuUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> zhuangxiuUuidNumber = new ArrayList<>();
                                    zhuangxiuUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("zhuangxiuUuidNumber",zhuangxiuUuidNumber);
                                }
                        }

                        //查询是否重复
                         //装修编号
                        List<ZhuangxiuEntity> zhuangxiuEntities_zhuangxiuUuidNumber = zhuangxiuService.selectList(new EntityWrapper<ZhuangxiuEntity>().in("zhuangxiu_uuid_number", seachFields.get("zhuangxiuUuidNumber")).eq("zhuangxiu_delete", 1));
                        if(zhuangxiuEntities_zhuangxiuUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ZhuangxiuEntity s:zhuangxiuEntities_zhuangxiuUuidNumber){
                                repeatFields.add(s.getZhuangxiuUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [装修编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        zhuangxiuService.insertBatch(zhuangxiuList);
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
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<ZhuangxiuView> returnZhuangxiuViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        PageUtils pageUtils = zhuangxiuOrderService.queryPage(params1);
        List<ZhuangxiuOrderView> orderViewsList =(List<ZhuangxiuOrderView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(ZhuangxiuOrderView orderView:orderViewsList){
            Integer zhuangxiuTypes = orderView.getZhuangxiuTypes();
            if(typeMap.containsKey(zhuangxiuTypes)){
                typeMap.put(zhuangxiuTypes,typeMap.get(zhuangxiuTypes)+1);
            }else{
                typeMap.put(zhuangxiuTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("zhuangxiuTypes",type);
            PageUtils pageUtils1 = zhuangxiuService.queryPage(params2);
            List<ZhuangxiuView> zhuangxiuViewList =(List<ZhuangxiuView>)pageUtils1.getList();
            returnZhuangxiuViewList.addAll(zhuangxiuViewList);
            if(returnZhuangxiuViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = zhuangxiuService.queryPage(params);
        if(returnZhuangxiuViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnZhuangxiuViewList.size();//要添加的数量
            List<ZhuangxiuView> zhuangxiuViewList =(List<ZhuangxiuView>)page.getList();
            for(ZhuangxiuView zhuangxiuView:zhuangxiuViewList){
                Boolean addFlag = true;
                for(ZhuangxiuView returnZhuangxiuView:returnZhuangxiuViewList){
                    if(returnZhuangxiuView.getId().intValue() ==zhuangxiuView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnZhuangxiuViewList.add(zhuangxiuView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnZhuangxiuViewList = returnZhuangxiuViewList.subList(0, limit);
        }

        for(ZhuangxiuView c:returnZhuangxiuViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnZhuangxiuViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = zhuangxiuService.queryPage(params);

        //字典表数据转换
        List<ZhuangxiuView> list =(List<ZhuangxiuView>)page.getList();
        for(ZhuangxiuView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZhuangxiuEntity zhuangxiu = zhuangxiuService.selectById(id);
            if(zhuangxiu !=null){

                //点击数量加1
                zhuangxiu.setZhuangxiuClicknum(zhuangxiu.getZhuangxiuClicknum()+1);
                zhuangxiuService.updateById(zhuangxiu);

                //entity转view
                ZhuangxiuView view = new ZhuangxiuView();
                BeanUtils.copyProperties( zhuangxiu , view );//把实体数据重构到view中

                //级联表
                    TuanduiEntity tuandui = tuanduiService.selectById(zhuangxiu.getTuanduiId());
                if(tuandui != null){
                    BeanUtils.copyProperties( tuandui , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setTuanduiId(tuandui.getId());
                }
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
    public R add(@RequestBody ZhuangxiuEntity zhuangxiu, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,zhuangxiu:{}",this.getClass().getName(),zhuangxiu.toString());
        Wrapper<ZhuangxiuEntity> queryWrapper = new EntityWrapper<ZhuangxiuEntity>()
            .eq("zhuangxiu_uuid_number", zhuangxiu.getZhuangxiuUuidNumber())
            .eq("tuandui_id", zhuangxiu.getTuanduiId())
            .eq("zhuangxiu_name", zhuangxiu.getZhuangxiuName())
            .eq("zhuangxiu_types", zhuangxiu.getZhuangxiuTypes())
            .eq("zhuangxiu_clicknum", zhuangxiu.getZhuangxiuClicknum())
            .eq("shangxia_types", zhuangxiu.getShangxiaTypes())
            .eq("zhuangxiu_delete", zhuangxiu.getZhuangxiuDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhuangxiuEntity zhuangxiuEntity = zhuangxiuService.selectOne(queryWrapper);
        if(zhuangxiuEntity==null){
            zhuangxiu.setZhuangxiuDelete(1);
            zhuangxiu.setInsertTime(new Date());
            zhuangxiu.setCreateTime(new Date());
        zhuangxiuService.insert(zhuangxiu);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}
