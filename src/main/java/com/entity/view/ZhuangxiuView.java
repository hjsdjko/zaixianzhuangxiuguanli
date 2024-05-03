package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ZhuangxiuEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 装修
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("zhuangxiu")
public class ZhuangxiuView extends ZhuangxiuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 装修类型的值
	*/
	@ColumnInfo(comment="装修类型的字典表值",type="varchar(200)")
	private String zhuangxiuValue;
	/**
	* 是否上架的值
	*/
	@ColumnInfo(comment="是否上架的字典表值",type="varchar(200)")
	private String shangxiaValue;

	//级联表 装修队
		/**
		* 团队名称
		*/

		@ColumnInfo(comment="团队名称",type="varchar(200)")
		private String tuanduiName;
		/**
		* 团队头像
		*/

		@ColumnInfo(comment="团队头像",type="varchar(200)")
		private String tuanduiPhoto;
		/**
		* 联系方式
		*/

		@ColumnInfo(comment="联系方式",type="varchar(200)")
		private String tuanduiPhone;
		/**
		* 电子邮箱
		*/

		@ColumnInfo(comment="电子邮箱",type="varchar(200)")
		private String tuanduiEmail;
		/**
		* 团队介绍
		*/

		@ColumnInfo(comment="团队介绍",type="text")
		private String tuanduiContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer tuanduiDelete;



	public ZhuangxiuView() {

	}

	public ZhuangxiuView(ZhuangxiuEntity zhuangxiuEntity) {
		try {
			BeanUtils.copyProperties(this, zhuangxiuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 装修类型的值
	*/
	public String getZhuangxiuValue() {
		return zhuangxiuValue;
	}
	/**
	* 设置： 装修类型的值
	*/
	public void setZhuangxiuValue(String zhuangxiuValue) {
		this.zhuangxiuValue = zhuangxiuValue;
	}
	//当前表的
	/**
	* 获取： 是否上架的值
	*/
	public String getShangxiaValue() {
		return shangxiaValue;
	}
	/**
	* 设置： 是否上架的值
	*/
	public void setShangxiaValue(String shangxiaValue) {
		this.shangxiaValue = shangxiaValue;
	}


	//级联表的get和set 装修队

		/**
		* 获取： 团队名称
		*/
		public String getTuanduiName() {
			return tuanduiName;
		}
		/**
		* 设置： 团队名称
		*/
		public void setTuanduiName(String tuanduiName) {
			this.tuanduiName = tuanduiName;
		}

		/**
		* 获取： 团队头像
		*/
		public String getTuanduiPhoto() {
			return tuanduiPhoto;
		}
		/**
		* 设置： 团队头像
		*/
		public void setTuanduiPhoto(String tuanduiPhoto) {
			this.tuanduiPhoto = tuanduiPhoto;
		}

		/**
		* 获取： 联系方式
		*/
		public String getTuanduiPhone() {
			return tuanduiPhone;
		}
		/**
		* 设置： 联系方式
		*/
		public void setTuanduiPhone(String tuanduiPhone) {
			this.tuanduiPhone = tuanduiPhone;
		}

		/**
		* 获取： 电子邮箱
		*/
		public String getTuanduiEmail() {
			return tuanduiEmail;
		}
		/**
		* 设置： 电子邮箱
		*/
		public void setTuanduiEmail(String tuanduiEmail) {
			this.tuanduiEmail = tuanduiEmail;
		}

		/**
		* 获取： 团队介绍
		*/
		public String getTuanduiContent() {
			return tuanduiContent;
		}
		/**
		* 设置： 团队介绍
		*/
		public void setTuanduiContent(String tuanduiContent) {
			this.tuanduiContent = tuanduiContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getTuanduiDelete() {
			return tuanduiDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setTuanduiDelete(Integer tuanduiDelete) {
			this.tuanduiDelete = tuanduiDelete;
		}


	@Override
	public String toString() {
		return "ZhuangxiuView{" +
			", zhuangxiuValue=" + zhuangxiuValue +
			", shangxiaValue=" + shangxiaValue +
			", tuanduiName=" + tuanduiName +
			", tuanduiPhoto=" + tuanduiPhoto +
			", tuanduiPhone=" + tuanduiPhone +
			", tuanduiEmail=" + tuanduiEmail +
			", tuanduiContent=" + tuanduiContent +
			", tuanduiDelete=" + tuanduiDelete +
			"} " + super.toString();
	}
}
