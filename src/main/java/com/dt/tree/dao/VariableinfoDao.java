package com.dt.tree.dao;

import com.dt.tree.entity.Variableinfo;

public interface VariableinfoDao {

    int deleteByPrimaryKey(Integer varid);

    int insert(Variableinfo record);

    int insertSelective(Variableinfo record);

    Variableinfo selectByPrimaryKey(Integer varid);

    int updateByPrimaryKeySelective(Variableinfo record);

    int updateByPrimaryKey(Variableinfo record);
}