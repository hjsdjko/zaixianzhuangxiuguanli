package com.entity.model;

import com.entity.ZhuangxiuOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 装修订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ZhuangxiuOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单号
     */
    private String zhuangxiuOrderUuidNumber;


    /**
     * 装修
     */
    private Integer zhuangxiuId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 预约时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date zhuangxiuOrderTime;


    /**
     * 实付价格
     */
    private Double zhuangxiuOrderTruePrice;


    /**
     * 订单类型
     */
    private Integer zhuangxiuOrderTypes;


    /**
     * 支付类型
     */
    private Integer zhuangxiuOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：订单号
	 */
    public String getZhuangxiuOrderUuidNumber() {
        return zhuangxiuOrderUuidNumber;
    }


    /**
	 * 设置：订单号
	 */
    public void setZhuangxiuOrderUuidNumber(String zhuangxiuOrderUuidNumber) {
        this.zhuangxiuOrderUuidNumber = zhuangxiuOrderUuidNumber;
    }
    /**
	 * 获取：装修
	 */
    public Integer getZhuangxiuId() {
        return zhuangxiuId;
    }


    /**
	 * 设置：装修
	 */
    public void setZhuangxiuId(Integer zhuangxiuId) {
        this.zhuangxiuId = zhuangxiuId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：预约时间
	 */
    public Date getZhuangxiuOrderTime() {
        return zhuangxiuOrderTime;
    }


    /**
	 * 设置：预约时间
	 */
    public void setZhuangxiuOrderTime(Date zhuangxiuOrderTime) {
        this.zhuangxiuOrderTime = zhuangxiuOrderTime;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getZhuangxiuOrderTruePrice() {
        return zhuangxiuOrderTruePrice;
    }


    /**
	 * 设置：实付价格
	 */
    public void setZhuangxiuOrderTruePrice(Double zhuangxiuOrderTruePrice) {
        this.zhuangxiuOrderTruePrice = zhuangxiuOrderTruePrice;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getZhuangxiuOrderTypes() {
        return zhuangxiuOrderTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setZhuangxiuOrderTypes(Integer zhuangxiuOrderTypes) {
        this.zhuangxiuOrderTypes = zhuangxiuOrderTypes;
    }
    /**
	 * 获取：支付类型
	 */
    public Integer getZhuangxiuOrderPaymentTypes() {
        return zhuangxiuOrderPaymentTypes;
    }


    /**
	 * 设置：支付类型
	 */
    public void setZhuangxiuOrderPaymentTypes(Integer zhuangxiuOrderPaymentTypes) {
        this.zhuangxiuOrderPaymentTypes = zhuangxiuOrderPaymentTypes;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：订单创建时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
