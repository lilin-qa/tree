package com.dt.tree.dao;

import com.dt.tree.entity.Parameterformat;

public interface ParameterformatDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Parameterformat record);

    int insertSelective(Parameterformat record);

    Parameterformat getParaList();

    int updateByPrimaryKeySelective(Parameterformat record);

    int updateByPrimaryKey(Parameterformat record);
}