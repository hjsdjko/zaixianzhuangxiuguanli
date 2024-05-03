package com.dao;

import com.entity.ZhuangxiuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhuangxiuView;

/**
 * 装修 Dao 接口
 *
 * @author 
 */
public interface ZhuangxiuDao extends BaseMapper<ZhuangxiuEntity> {

   List<ZhuangxiuView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
