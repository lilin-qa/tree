package com.dt.tree.dao;

import com.dt.tree.entity.Businessinfo;
import com.dt.tree.entity.businessinfoPro;

import java.util.List;

public interface BusinessinfoDao {
    int deleteByPrimaryKey(Integer busid);

    int insert(Businessinfo record);

    int insertSelective(Businessinfo record);

    Businessinfo selectByPrimaryKey(Integer busid);

    int updateByPrimaryKeySelective(Businessinfo record);

    int updateByPrimaryKey(Businessinfo record);

     List<businessinfoPro> selectbusAndPro(businessinfoPro busPro);
    int getCountBusi();

    /**
     * 根据proid查询业务线名称
     * @param proid
     * @return
     */
    List<Businessinfo> selectByProid(Integer proid);
}