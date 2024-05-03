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
 * 装修队
 *
 * @author 
 * @email
 */
@TableName("tuandui")
public class TuanduiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public TuanduiEntity() {

	}

	public TuanduiEntity(T t) {
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
     * 账户
     */
    @ColumnInfo(comment="账户",type="varchar(200)")
    @TableField(value = "username")

    private String username;


    /**
     * 密码
     */
    @ColumnInfo(comment="密码",type="varchar(200)")
    @TableField(value = "password")

    private String password;


    /**
     * 团队名称
     */
    @ColumnInfo(comment="团队名称",type="varchar(200)")
    @TableField(value = "tuandui_name")

    private String tuanduiName;


    /**
     * 负责人性别
     */
    @ColumnInfo(comment="负责人性别",type="int(11)")
    @TableField(value = "sex_types")

    private Integer sexTypes;


    /**
     * 团队头像
     */
    @ColumnInfo(comment="团队头像",type="varchar(200)")
    @TableField(value = "tuandui_photo")

    private String tuanduiPhoto;


    /**
     * 联系方式
     */
    @ColumnInfo(comment="联系方式",type="varchar(200)")
    @TableField(value = "tuandui_phone")

    private String tuanduiPhone;


    /**
     * 电子邮箱
     */
    @ColumnInfo(comment="电子邮箱",type="varchar(200)")
    @TableField(value = "tuandui_email")

    private String tuanduiEmail;


    /**
     * 团队介绍
     */
    @ColumnInfo(comment="团队介绍",type="text")
    @TableField(value = "tuandui_content")

    private String tuanduiContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "tuandui_delete")

    private Integer tuanduiDelete;


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
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }
    /**
	 * 设置：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }
    /**
	 * 设置：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：团队名称
	 */
    public String getTuanduiName() {
        return tuanduiName;
    }
    /**
	 * 设置：团队名称
	 */

    public void setTuanduiName(String tuanduiName) {
        this.tuanduiName = tuanduiName;
    }
    /**
	 * 获取：负责人性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }
    /**
	 * 设置：负责人性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：团队头像
	 */
    public String getTuanduiPhoto() {
        return tuanduiPhoto;
    }
    /**
	 * 设置：团队头像
	 */

    public void setTuanduiPhoto(String tuanduiPhoto) {
        this.tuanduiPhoto = tuanduiPhoto;
    }
    /**
	 * 获取：联系方式
	 */
    public String getTuanduiPhone() {
        return tuanduiPhone;
    }
    /**
	 * 设置：联系方式
	 */

    public void setTuanduiPhone(String tuanduiPhone) {
        this.tuanduiPhone = tuanduiPhone;
    }
    /**
	 * 获取：电子邮箱
	 */
    public String getTuanduiEmail() {
        return tuanduiEmail;
    }
    /**
	 * 设置：电子邮箱
	 */

    public void setTuanduiEmail(String tuanduiEmail) {
        this.tuanduiEmail = tuanduiEmail;
    }
    /**
	 * 获取：团队介绍
	 */
    public String getTuanduiContent() {
        return tuanduiContent;
    }
    /**
	 * 设置：团队介绍
	 */

    public void setTuanduiContent(String tuanduiContent) {
        this.tuanduiContent = tuanduiContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getTuanduiDelete() {
        return tuanduiDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setTuanduiDelete(Integer tuanduiDelete) {
        this.tuanduiDelete = tuanduiDelete;
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
        return "Tuandui{" +
            ", id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", tuanduiName=" + tuanduiName +
            ", sexTypes=" + sexTypes +
            ", tuanduiPhoto=" + tuanduiPhoto +
            ", tuanduiPhone=" + tuanduiPhone +
            ", tuanduiEmail=" + tuanduiEmail +
            ", tuanduiContent=" + tuanduiContent +
            ", tuanduiDelete=" + tuanduiDelete +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
