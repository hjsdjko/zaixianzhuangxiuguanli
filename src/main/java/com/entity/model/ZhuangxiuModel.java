package com.entity.model;

import com.entity.ZhuangxiuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 装修
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ZhuangxiuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 装修编号
     */
    private String zhuangxiuUuidNumber;


    /**
     * 装修团队
     */
    private Integer tuanduiId;


    /**
     * 装修名称
     */
    private String zhuangxiuName;


    /**
     * 装修照片
     */
    private String zhuangxiuPhoto;


    /**
     * 装修类型
     */
    private Integer zhuangxiuTypes;


    /**
     * 装修定金
     */
    private Double zhuangxiuNewMoney;


    /**
     * 点击次数
     */
    private Integer zhuangxiuClicknum;


    /**
     * 装修介绍
     */
    private String zhuangxiuContent;


    /**
     * 是否上架
     */
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    private Integer zhuangxiuDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
