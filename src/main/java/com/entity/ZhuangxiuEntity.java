package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 装修
 *
 * @author 
 * @email
 */
@TableName("zhuangxiu")
public class ZhuangxiuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ZhuangxiuEntity() {

	}

	public ZhuangxiuEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 装修编号
     */
    @ColumnInfo(comment="装修编号",type="varchar(200)")
    @TableField(value = "zhuangxiu_uuid_number")

    private String zhuangxiuUuidNumber;


    /**
     * 装修团队
     */
    @ColumnInfo(comment="装修团队",type="int(11)")
    @TableField(value = "tuandui_id")

    private Integer tuanduiId;


    /**
     * 装修名称
     */
    @ColumnInfo(comment="装修名称",type="varchar(200)")
    @TableField(value = "zhuangxiu_name")

    private String zhuangxiuName;


    /**
     * 装修照片
     */
    @ColumnInfo(comment="装修照片",type="varchar(200)")
    @TableField(value = "zhuangxiu_photo")

    private String zhuangxiuPhoto;


    /**
     * 装修类型
     */
    @ColumnInfo(comment="装修类型",type="int(11)")
    @TableField(value = "zhuangxiu_types")

    private Integer zhuangxiuTypes;


    /**
     * 装修定金
     */
    @ColumnInfo(comment="装修定金",type="decimal(10,2)")
    @TableField(value = "zhuangxiu_new_money")

    private Double zhuangxiuNewMoney;


    /**
     * 点击次数
     */
    @ColumnInfo(comment="点击次数",type="int(11)")
    @TableField(value = "zhuangxiu_clicknum")

    private Integer zhuangxiuClicknum;


    /**
     * 装修介绍
     */
    @ColumnInfo(comment="装修介绍",type="text")
    @TableField(value = "zhuangxiu_content")

    private String zhuangxiuContent;


    /**
     * 是否上架
     */
    @ColumnInfo(comment="是否上架",type="int(11)")
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "zhuangxiu_delete")

    private Integer zhuangxiuDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：装修编号
	 */
    public String getZhuangxiuUuidNumber() {
        return zhuangxiuUuidNumber;
    }
    /**
	 * 设置：装修编号
	 */

    public void setZhuangxiuUuidNumber(String zhuangxiuUuidNumber) {
        this.zhuangxiuUuidNumber = zhuangxiuUuidNumber;
    }
    /**
	 * 获取：装修团队
	 */
    public Integer getTuanduiId() {
        return tuanduiId;
    }
    /**
	 * 设置：装修团队
	 */

    public void setTuanduiId(Integer tuanduiId) {
        this.tuanduiId = tuanduiId;
    }
    /**
	 * 获取：装修名称
	 */
    public String getZhuangxiuName() {
        return zhuangxiuName;
    }
    /**
	 * 设置：装修名称
	 */

    public void setZhuangxiuName(String zhuangxiuName) {
        this.zhuangxiuName = zhuangxiuName;
    }
    /**
	 * 获取：装修照片
	 */
    public String getZhuangxiuPhoto() {
        return zhuangxiuPhoto;
    }
    /**
	 * 设置：装修照片
	 */

    public void setZhuangxiuPhoto(String zhuangxiuPhoto) {
        this.zhuangxiuPhoto = zhuangxiuPhoto;
    }
    /**
	 * 获取：装修类型
	 */
    public Integer getZhuangxiuTypes() {
        return zhuangxiuTypes;
    }
    /**
	 * 设置：装修类型
	 */

    public void setZhuangxiuTypes(Integer zhuangxiuTypes) {
        this.zhuangxiuTypes = zhuangxiuTypes;
    }
    /**
	 * 获取：装修定金
	 */
    public Double getZhuangxiuNewMoney() {
        return zhuangxiuNewMoney;
    }
    /**
	 * 设置：装修定金
	 */

    public void setZhuangxiuNewMoney(Double zhuangxiuNewMoney) {
        this.zhuangxiuNewMoney = zhuangxiuNewMoney;
    }
    /**
	 * 获取：点击次数
	 */
    public Integer getZhuangxiuClicknum() {
        return zhuangxiuClicknum;
    }
    /**
	 * 设置：点击次数
	 */

    public void setZhuangxiuClicknum(Integer zhuangxiuClicknum) {
        this.zhuangxiuClicknum = zhuangxiuClicknum;
    }
    /**
	 * 获取：装修介绍
	 */
    public String getZhuangxiuContent() {
        return zhuangxiuContent;
    }
    /**
	 * 设置：装修介绍
	 */

    public void setZhuangxiuContent(String zhuangxiuContent) {
        this.zhuangxiuContent = zhuangxiuContent;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }
    /**
	 * 设置：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getZhuangxiuDelete() {
        return zhuangxiuDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setZhuangxiuDelete(Integer zhuangxiuDelete) {
        this.zhuangxiuDelete = zhuangxiuDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Zhuangxiu{" +
            ", id=" + id +
            ", zhuangxiuUuidNumber=" + zhuangxiuUuidNumber +
            ", tuanduiId=" + tuanduiId +
            ", zhuangxiuName=" + zhuangxiuName +
            ", zhuangxiuPhoto=" + zhuangxiuPhoto +
            ", zhuangxiuTypes=" + zhuangxiuTypes +
            ", zhuangxiuNewMoney=" + zhuangxiuNewMoney +
            ", zhuangxiuClicknum=" + zhuangxiuClicknum +
            ", zhuangxiuContent=" + zhuangxiuContent +
            ", shangxiaTypes=" + shangxiaTypes +
            ", zhuangxiuDelete=" + zhuangxiuDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
