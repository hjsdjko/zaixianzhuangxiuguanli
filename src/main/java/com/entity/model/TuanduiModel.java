package com.entity.model;

import com.entity.TuanduiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 装修队
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class TuanduiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 团队名称
     */
    private String tuanduiName;


    /**
     * 负责人性别
     */
    private Integer sexTypes;


    /**
     * 团队头像
     */
    private String tuanduiPhoto;


    /**
     * 联系方式
     */
    private String tuanduiPhone;


    /**
     * 电子邮箱
     */
    private String tuanduiEmail;


    /**
     * 团队介绍
     */
    private String tuanduiContent;


    /**
     * 逻辑删除
     */
    private Integer tuanduiDelete;


    /**
     * 创建时间
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

    }
