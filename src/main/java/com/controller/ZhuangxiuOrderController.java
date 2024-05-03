
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
 * 装修订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/zhuangxiuOrder")
public class ZhuangxiuOrderController {
    private static final Logger logger = LoggerFactory.getLogger(ZhuangxiuOrderController.class);

    private static final String TABLE_NAME = "zhuangxiuOrder";

    @Autowired
    private ZhuangxiuOrderService zhuangxiuOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表非注册的service
    @Autowired
    private ZhuangxiuService zhuangxiuService;
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
        CommonUtil.checkMap(params);
        PageUtils page = zhuangxiuOrderService.queryPage(params);

        //字典表数据转换
        List<ZhuangxiuOrderView> list =(List<ZhuangxiuOrderView>)page.getList();
        for(ZhuangxiuOrderView c:list){
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
        ZhuangxiuOrderEntity zhuangxiuOrder = zhuangxiuOrderService.selectById(id);
        if(zhuangxiuOrder !=null){
            //entity转view
            ZhuangxiuOrderView view = new ZhuangxiuOrderView();
            BeanUtils.copyProperties( zhuangxiuOrder , view );//把实体数据重构到view中
            //级联表 装修
            //级联表
            ZhuangxiuEntity zhuangxiu = zhuangxiuService.selectById(zhuangxiuOrder.getZhuangxiuId());
            if(zhuangxiu != null){
            BeanUtils.copyProperties( zhuangxiu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setZhuangxiuId(zhuangxiu.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(zhuangxiuOrder.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
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
    public R save(@RequestBody ZhuangxiuOrderEntity zhuangxiuOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zhuangxiuOrder:{}",this.getClass().getName(),zhuangxiuOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            zhuangxiuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        zhuangxiuOrder.setCreateTime(new Date());
        zhuangxiuOrder.setInsertTime(new Date());
        zhuangxiuOrderService.insert(zhuangxiuOrder);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZhuangxiuOrderEntity zhuangxiuOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,zhuangxiuOrder:{}",this.getClass().getName(),zhuangxiuOrder.toString());
        ZhuangxiuOrderEntity oldZhuangxiuOrderEntity = zhuangxiuOrderService.selectById(zhuangxiuOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            zhuangxiuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<ZhuangxiuOrderEntity> queryWrapper = new EntityWrapper<ZhuangxiuOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhuangxiuOrderEntity zhuangxiuOrderEntity = zhuangxiuOrderService.selectOne(queryWrapper);
        if(zhuangxiuOrderEntity==null){
            zhuangxiuOrderService.updateById(zhuangxiuOrder);//根据id更新
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
        List<ZhuangxiuOrderEntity> oldZhuangxiuOrderList =zhuangxiuOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        zhuangxiuOrderService.deleteBatchIds(Arrays.asList(ids));

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
            List<ZhuangxiuOrderEntity> zhuangxiuOrderList = new ArrayList<>();//上传的东西
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
                            ZhuangxiuOrderEntity zhuangxiuOrderEntity = new ZhuangxiuOrderEntity();
//                            zhuangxiuOrderEntity.setZhuangxiuOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            zhuangxiuOrderEntity.setZhuangxiuId(Integer.valueOf(data.get(0)));   //装修 要改的
//                            zhuangxiuOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            zhuangxiuOrderEntity.setZhuangxiuOrderTime(sdf.parse(data.get(0)));          //预约时间 要改的
//                            zhuangxiuOrderEntity.setZhuangxiuOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            zhuangxiuOrderEntity.setZhuangxiuOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            zhuangxiuOrderEntity.setZhuangxiuOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            zhuangxiuOrderEntity.setInsertTime(date);//时间
//                            zhuangxiuOrderEntity.setCreateTime(date);//时间
                            zhuangxiuOrderList.add(zhuangxiuOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("zhuangxiuOrderUuidNumber")){
                                    List<String> zhuangxiuOrderUuidNumber = seachFields.get("zhuangxiuOrderUuidNumber");
                                    zhuangxiuOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> zhuangxiuOrderUuidNumber = new ArrayList<>();
                                    zhuangxiuOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("zhuangxiuOrderUuidNumber",zhuangxiuOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<ZhuangxiuOrderEntity> zhuangxiuOrderEntities_zhuangxiuOrderUuidNumber = zhuangxiuOrderService.selectList(new EntityWrapper<ZhuangxiuOrderEntity>().in("zhuangxiu_order_uuid_number", seachFields.get("zhuangxiuOrderUuidNumber")));
                        if(zhuangxiuOrderEntities_zhuangxiuOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ZhuangxiuOrderEntity s:zhuangxiuOrderEntities_zhuangxiuOrderUuidNumber){
                                repeatFields.add(s.getZhuangxiuOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        zhuangxiuOrderService.insertBatch(zhuangxiuOrderList);
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
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = zhuangxiuOrderService.queryPage(params);

        //字典表数据转换
        List<ZhuangxiuOrderView> list =(List<ZhuangxiuOrderView>)page.getList();
        for(ZhuangxiuOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZhuangxiuOrderEntity zhuangxiuOrder = zhuangxiuOrderService.selectById(id);
            if(zhuangxiuOrder !=null){


                //entity转view
                ZhuangxiuOrderView view = new ZhuangxiuOrderView();
                BeanUtils.copyProperties( zhuangxiuOrder , view );//把实体数据重构到view中

                //级联表
                    ZhuangxiuEntity zhuangxiu = zhuangxiuService.selectById(zhuangxiuOrder.getZhuangxiuId());
                if(zhuangxiu != null){
                    BeanUtils.copyProperties( zhuangxiu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setZhuangxiuId(zhuangxiu.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(zhuangxiuOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
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
    public R add(@RequestBody ZhuangxiuOrderEntity zhuangxiuOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,zhuangxiuOrder:{}",this.getClass().getName(),zhuangxiuOrder.toString());
            ZhuangxiuEntity zhuangxiuEntity = zhuangxiuService.selectById(zhuangxiuOrder.getZhuangxiuId());
            if(zhuangxiuEntity == null){
                return R.error(511,"查不到该装修");
            }
            // Double zhuangxiuNewMoney = zhuangxiuEntity.getZhuangxiuNewMoney();

            if(false){
            }
            else if(zhuangxiuEntity.getZhuangxiuNewMoney() == null){
                return R.error(511,"装修定金不能为空");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - zhuangxiuEntity.getZhuangxiuNewMoney()*1;//余额
            if(balance<0)
                return R.error(511,"余额不够支付");
            zhuangxiuOrder.setZhuangxiuOrderTypes(101); //设置订单状态为已支付
            zhuangxiuOrder.setZhuangxiuOrderTruePrice(zhuangxiuEntity.getZhuangxiuNewMoney()*1); //设置实付价格
            zhuangxiuOrder.setYonghuId(userId); //设置订单支付人id
            zhuangxiuOrder.setZhuangxiuOrderUuidNumber(String.valueOf(new Date().getTime()));
            zhuangxiuOrder.setZhuangxiuOrderPaymentTypes(1);
            zhuangxiuOrder.setInsertTime(new Date());
            zhuangxiuOrder.setCreateTime(new Date());
                zhuangxiuOrderService.insert(zhuangxiuOrder);//新增订单
                yonghuEntity.setNewMoney(balance);//设置金额
            yonghuService.updateById(yonghuEntity);

            return R.ok();
    }


    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            ZhuangxiuOrderEntity zhuangxiuOrder = zhuangxiuOrderService.selectById(id);
            Integer buyNumber = 1;
            Integer zhuangxiuOrderPaymentTypes = zhuangxiuOrder.getZhuangxiuOrderPaymentTypes();
            Integer zhuangxiuId = zhuangxiuOrder.getZhuangxiuId();
            if(zhuangxiuId == null)
                return R.error(511,"查不到该装修");
            ZhuangxiuEntity zhuangxiuEntity = zhuangxiuService.selectById(zhuangxiuId);
            if(zhuangxiuEntity == null)
                return R.error(511,"查不到该装修");
            Double zhuangxiuNewMoney = zhuangxiuEntity.getZhuangxiuNewMoney();
            if(zhuangxiuNewMoney == null)
                return R.error(511,"装修价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");

            Double zhekou = 1.0;


            //判断是什么支付方式 1代表余额 2代表积分
            if(zhuangxiuOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = zhuangxiuEntity.getZhuangxiuNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额


            }


            zhuangxiuOrder.setZhuangxiuOrderTypes(102);//设置订单状态为已退款
            zhuangxiuOrderService.updateById(zhuangxiuOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            zhuangxiuService.updateById(zhuangxiuEntity);//更新订单中装修的信息

            return R.ok();
    }

    /**
     * 完成
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id ){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        ZhuangxiuOrderEntity  zhuangxiuOrderEntity = zhuangxiuOrderService.selectById(id);
        zhuangxiuOrderEntity.setZhuangxiuOrderTypes(103);//设置订单状态为已完成
        boolean b =  zhuangxiuOrderService.updateById( zhuangxiuOrderEntity);

        if(!b){
            return R.error("完成出错");
        }
        return R.ok();
    }


}
