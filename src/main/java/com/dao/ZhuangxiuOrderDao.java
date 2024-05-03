package com.dao;

import com.entity.ZhuangxiuOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhuangxiuOrderView;

/**
 * 装修订单 Dao 接口
 *
 * @author 
 */
public interface ZhuangxiuOrderDao extends BaseMapper<ZhuangxiuOrderEntity> {

   List<ZhuangxiuOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
