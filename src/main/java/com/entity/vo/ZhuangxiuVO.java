package com.entity.vo;

import com.entity.ZhuangxiuEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 装修
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("zhuangxiu")
public class ZhuangxiuVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 装修编号
     */

    @TableField(value = "zhuangxiu_uuid_number")
    private String zhuangxiuUuidNumber;


    /**
     * 装修团队
     */

    @TableField(value = "tuandui_id")
    private Integer tuanduiId;


    /**
     * 装修名称
     */

    @TableField(value = "zhuangxiu_name")
    private String zhuangxiuName;


    /**
     * 装修照片
     */

    @TableField(value = "zhuangxiu_photo")
    private String zhuangxiuPhoto;


    /**
     * 装修类型
     */

    @TableField(value = "zhuangxiu_types")
    private Integer zhuangxiuTypes;


    /**
     * 装修定金
     */

    @TableField(value = "zhuangxiu_new_money")
    private Double zhuangxiuNewMoney;


    /**
     * 点击次数
     */

    @TableField(value = "zhuangxiu_clicknum")
    private Integer zhuangxiuClicknum;


    /**
     * 装修介绍
     */

    @TableField(value = "zhuangxiu_content")
    private String zhuangxiuContent;


    /**
     * 是否上架
     */

    @TableField(value = "shangxia_types")
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */

    @TableField(value = "zhuangxiu_delete")
    private Integer zhuangxiuDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 设置：装修编号
	 */
    public String getZhuangxiuUuidNumber() {
        return zhuangxiuUuidNumber;
    }


    /**
	 * 获取：装修编号
	 */

    public void setZhuangxiuUuidNumber(String zhuangxiuUuidNumber) {
        this.zhuangxiuUuidNumber = zhuangxiuUuidNumber;
    }
    /**
	 * 设置：装修团队
	 */
    public Integer getTuanduiId() {
        return tuanduiId;
    }


    /**
	 * 获取：装修团队
	 */

    public void setTuanduiId(Integer tuanduiId) {
        this.tuanduiId = tuanduiId;
    }
    /**
	 * 设置：装修名称
	 */
    public String getZhuangxiuName() {
        return zhuangxiuName;
    }


    /**
	 * 获取：装修名称
	 */

    public void setZhuangxiuName(String zhuangxiuName) {
        this.zhuangxiuName = zhuangxiuName;
    }
    /**
	 * 设置：装修照片
	 */
    public String getZhuangxiuPhoto() {
        return zhuangxiuPhoto;
    }


    /**
	 * 获取：装修照片
	 */

    public void setZhuangxiuPhoto(String zhuangxiuPhoto) {
        this.zhuangxiuPhoto = zhuangxiuPhoto;
    }
    /**
	 * 设置：装修类型
	 */
    public Integer getZhuangxiuTypes() {
        return zhuangxiuTypes;
    }


    /**
	 * 获取：装修类型
	 */

    public void setZhuangxiuTypes(Integer zhuangxiuTypes) {
        this.zhuangxiuTypes = zhuangxiuTypes;
    }
    /**
	 * 设置：装修定金
	 */
    public Double getZhuangxiuNewMoney() {
        return zhuangxiuNewMoney;
    }


    /**
	 * 获取：装修定金
	 */

    public void setZhuangxiuNewMoney(Double zhuangxiuNewMoney) {
        this.zhuangxiuNewMoney = zhuangxiuNewMoney;
    }
    /**
	 * 设置：点击次数
	 */
    public Integer getZhuangxiuClicknum() {
        return zhuangxiuClicknum;
    }


    /**
	 * 获取：点击次数
	 */

    public void setZhuangxiuClicknum(Integer zhuangxiuClicknum) {
        this.zhuangxiuClicknum = zhuangxiuClicknum;
    }
    /**
	 * 设置：装修介绍
	 */
    public String getZhuangxiuContent() {
        return zhuangxiuContent;
    }


    /**
	 * 获取：装修介绍
	 */

    public void setZhuangxiuContent(String zhuangxiuContent) {
        this.zhuangxiuContent = zhuangxiuContent;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getZhuangxiuDelete() {
        return zhuangxiuDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setZhuangxiuDelete(Integer zhuangxiuDelete) {
        this.zhuangxiuDelete = zhuangxiuDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
