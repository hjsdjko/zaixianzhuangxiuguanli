package com.entity.vo;

import com.entity.ZhuangxiuOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 装修订单
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("zhuangxiu_order")
public class ZhuangxiuOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单号
     */

    @TableField(value = "zhuangxiu_order_uuid_number")
    private String zhuangxiuOrderUuidNumber;


    /**
     * 装修
     */

    @TableField(value = "zhuangxiu_id")
    private Integer zhuangxiuId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 预约时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "zhuangxiu_order_time")
    private Date zhuangxiuOrderTime;


    /**
     * 实付价格
     */

    @TableField(value = "zhuangxiu_order_true_price")
    private Double zhuangxiuOrderTruePrice;


    /**
     * 订单类型
     */

    @TableField(value = "zhuangxiu_order_types")
    private Integer zhuangxiuOrderTypes;


    /**
     * 支付类型
     */

    @TableField(value = "zhuangxiu_order_payment_types")
    private Integer zhuangxiuOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：订单号
	 */
    public String getZhuangxiuOrderUuidNumber() {
        return zhuangxiuOrderUuidNumber;
    }


    /**
	 * 获取：订单号
	 */

    public void setZhuangxiuOrderUuidNumber(String zhuangxiuOrderUuidNumber) {
        this.zhuangxiuOrderUuidNumber = zhuangxiuOrderUuidNumber;
    }
    /**
	 * 设置：装修
	 */
    public Integer getZhuangxiuId() {
        return zhuangxiuId;
    }


    /**
	 * 获取：装修
	 */

    public void setZhuangxiuId(Integer zhuangxiuId) {
        this.zhuangxiuId = zhuangxiuId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：预约时间
	 */
    public Date getZhuangxiuOrderTime() {
        return zhuangxiuOrderTime;
    }


    /**
	 * 获取：预约时间
	 */

    public void setZhuangxiuOrderTime(Date zhuangxiuOrderTime) {
        this.zhuangxiuOrderTime = zhuangxiuOrderTime;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getZhuangxiuOrderTruePrice() {
        return zhuangxiuOrderTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setZhuangxiuOrderTruePrice(Double zhuangxiuOrderTruePrice) {
        this.zhuangxiuOrderTruePrice = zhuangxiuOrderTruePrice;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getZhuangxiuOrderTypes() {
        return zhuangxiuOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setZhuangxiuOrderTypes(Integer zhuangxiuOrderTypes) {
        this.zhuangxiuOrderTypes = zhuangxiuOrderTypes;
    }
    /**
	 * 设置：支付类型
	 */
    public Integer getZhuangxiuOrderPaymentTypes() {
        return zhuangxiuOrderPaymentTypes;
    }


    /**
	 * 获取：支付类型
	 */

    public void setZhuangxiuOrderPaymentTypes(Integer zhuangxiuOrderPaymentTypes) {
        this.zhuangxiuOrderPaymentTypes = zhuangxiuOrderPaymentTypes;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
