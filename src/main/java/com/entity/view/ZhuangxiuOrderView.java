package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ZhuangxiuOrderEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 装修订单
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("zhuangxiu_order")
public class ZhuangxiuOrderView extends ZhuangxiuOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 订单类型的值
	*/
	@ColumnInfo(comment="订单类型的字典表值",type="varchar(200)")
	private String zhuangxiuOrderValue;
	/**
	* 支付类型的值
	*/
	@ColumnInfo(comment="支付类型的字典表值",type="varchar(200)")
	private String zhuangxiuOrderPaymentValue;

	//级联表 用户
		/**
		* 用户姓名
		*/

		@ColumnInfo(comment="用户姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 用户头像
		*/

		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 身份证号
		*/

		@ColumnInfo(comment="身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 联系方式
		*/

		@ColumnInfo(comment="联系方式",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 电子邮箱
		*/

		@ColumnInfo(comment="电子邮箱",type="varchar(200)")
		private String yonghuEmail;
		/**
		* 余额
		*/
		@ColumnInfo(comment="余额",type="decimal(10,2)")
		private Double newMoney;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer yonghuDelete;
	//级联表 装修
		/**
		* 装修编号
		*/

		@ColumnInfo(comment="装修编号",type="varchar(200)")
		private String zhuangxiuUuidNumber;
					 
		/**
		* 装修 的 装修团队
		*/
		@ColumnInfo(comment="装修团队",type="int(11)")
		private Integer zhuangxiuTuanduiId;
		/**
		* 装修名称
		*/

		@ColumnInfo(comment="装修名称",type="varchar(200)")
		private String zhuangxiuName;
		/**
		* 装修照片
		*/

		@ColumnInfo(comment="装修照片",type="varchar(200)")
		private String zhuangxiuPhoto;
		/**
		* 装修类型
		*/
		@ColumnInfo(comment="装修类型",type="int(11)")
		private Integer zhuangxiuTypes;
			/**
			* 装修类型的值
			*/
			@ColumnInfo(comment="装修类型的字典表值",type="varchar(200)")
			private String zhuangxiuValue;
		/**
		* 装修定金
		*/
		@ColumnInfo(comment="装修定金",type="decimal(10,2)")
		private Double zhuangxiuNewMoney;
		/**
		* 点击次数
		*/

		@ColumnInfo(comment="点击次数",type="int(11)")
		private Integer zhuangxiuClicknum;
		/**
		* 装修介绍
		*/

		@ColumnInfo(comment="装修介绍",type="text")
		private String zhuangxiuContent;
		/**
		* 是否上架
		*/
		@ColumnInfo(comment="是否上架",type="int(11)")
		private Integer shangxiaTypes;
			/**
			* 是否上架的值
			*/
			@ColumnInfo(comment="是否上架的字典表值",type="varchar(200)")
			private String shangxiaValue;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer zhuangxiuDelete;



	public ZhuangxiuOrderView() {

	}

	public ZhuangxiuOrderView(ZhuangxiuOrderEntity zhuangxiuOrderEntity) {
		try {
			BeanUtils.copyProperties(this, zhuangxiuOrderEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 订单类型的值
	*/
	public String getZhuangxiuOrderValue() {
		return zhuangxiuOrderValue;
	}
	/**
	* 设置： 订单类型的值
	*/
	public void setZhuangxiuOrderValue(String zhuangxiuOrderValue) {
		this.zhuangxiuOrderValue = zhuangxiuOrderValue;
	}
	//当前表的
	/**
	* 获取： 支付类型的值
	*/
	public String getZhuangxiuOrderPaymentValue() {
		return zhuangxiuOrderPaymentValue;
	}
	/**
	* 设置： 支付类型的值
	*/
	public void setZhuangxiuOrderPaymentValue(String zhuangxiuOrderPaymentValue) {
		this.zhuangxiuOrderPaymentValue = zhuangxiuOrderPaymentValue;
	}


	//级联表的get和set 用户

		/**
		* 获取： 用户姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 用户头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 用户头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 联系方式
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 联系方式
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 电子邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 电子邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}

		/**
		* 获取： 余额
		*/
		public Double getNewMoney() {
			return newMoney;
		}
		/**
		* 设置： 余额
		*/
		public void setNewMoney(Double newMoney) {
			this.newMoney = newMoney;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getYonghuDelete() {
			return yonghuDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setYonghuDelete(Integer yonghuDelete) {
			this.yonghuDelete = yonghuDelete;
		}
	//级联表的get和set 装修

		/**
		* 获取： 装修编号
		*/
		public String getZhuangxiuUuidNumber() {
			return zhuangxiuUuidNumber;
		}
		/**
		* 设置： 装修编号
		*/
		public void setZhuangxiuUuidNumber(String zhuangxiuUuidNumber) {
			this.zhuangxiuUuidNumber = zhuangxiuUuidNumber;
		}
		/**
		* 获取：装修 的 装修团队
		*/
		public Integer getZhuangxiuTuanduiId() {
			return zhuangxiuTuanduiId;
		}
		/**
		* 设置：装修 的 装修团队
		*/
		public void setZhuangxiuTuanduiId(Integer zhuangxiuTuanduiId) {
			this.zhuangxiuTuanduiId = zhuangxiuTuanduiId;
		}

		/**
		* 获取： 装修名称
		*/
		public String getZhuangxiuName() {
			return zhuangxiuName;
		}
		/**
		* 设置： 装修名称
		*/
		public void setZhuangxiuName(String zhuangxiuName) {
			this.zhuangxiuName = zhuangxiuName;
		}

		/**
		* 获取： 装修照片
		*/
		public String getZhuangxiuPhoto() {
			return zhuangxiuPhoto;
		}
		/**
		* 设置： 装修照片
		*/
		public void setZhuangxiuPhoto(String zhuangxiuPhoto) {
			this.zhuangxiuPhoto = zhuangxiuPhoto;
		}
		/**
		* 获取： 装修类型
		*/
		public Integer getZhuangxiuTypes() {
			return zhuangxiuTypes;
		}
		/**
		* 设置： 装修类型
		*/
		public void setZhuangxiuTypes(Integer zhuangxiuTypes) {
			this.zhuangxiuTypes = zhuangxiuTypes;
		}


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

		/**
		* 获取： 装修定金
		*/
		public Double getZhuangxiuNewMoney() {
			return zhuangxiuNewMoney;
		}
		/**
		* 设置： 装修定金
		*/
		public void setZhuangxiuNewMoney(Double zhuangxiuNewMoney) {
			this.zhuangxiuNewMoney = zhuangxiuNewMoney;
		}

		/**
		* 获取： 点击次数
		*/
		public Integer getZhuangxiuClicknum() {
			return zhuangxiuClicknum;
		}
		/**
		* 设置： 点击次数
		*/
		public void setZhuangxiuClicknum(Integer zhuangxiuClicknum) {
			this.zhuangxiuClicknum = zhuangxiuClicknum;
		}

		/**
		* 获取： 装修介绍
		*/
		public String getZhuangxiuContent() {
			return zhuangxiuContent;
		}
		/**
		* 设置： 装修介绍
		*/
		public void setZhuangxiuContent(String zhuangxiuContent) {
			this.zhuangxiuContent = zhuangxiuContent;
		}
		/**
		* 获取： 是否上架
		*/
		public Integer getShangxiaTypes() {
			return shangxiaTypes;
		}
		/**
		* 设置： 是否上架
		*/
		public void setShangxiaTypes(Integer shangxiaTypes) {
			this.shangxiaTypes = shangxiaTypes;
		}


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

		/**
		* 获取： 逻辑删除
		*/
		public Integer getZhuangxiuDelete() {
			return zhuangxiuDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setZhuangxiuDelete(Integer zhuangxiuDelete) {
			this.zhuangxiuDelete = zhuangxiuDelete;
		}


	@Override
	public String toString() {
		return "ZhuangxiuOrderView{" +
			", zhuangxiuOrderValue=" + zhuangxiuOrderValue +
			", zhuangxiuOrderPaymentValue=" + zhuangxiuOrderPaymentValue +
			", zhuangxiuUuidNumber=" + zhuangxiuUuidNumber +
			", zhuangxiuName=" + zhuangxiuName +
			", zhuangxiuPhoto=" + zhuangxiuPhoto +
			", zhuangxiuNewMoney=" + zhuangxiuNewMoney +
			", zhuangxiuClicknum=" + zhuangxiuClicknum +
			", zhuangxiuContent=" + zhuangxiuContent +
			", zhuangxiuDelete=" + zhuangxiuDelete +
			", yonghuName=" + yonghuName +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhone=" + yonghuPhone +
			", yonghuEmail=" + yonghuEmail +
			", newMoney=" + newMoney +
			", yonghuDelete=" + yonghuDelete +
			"} " + super.toString();
	}
}
