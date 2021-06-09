package com.dt.tree.services;

import com.dt.tree.entity.Variableinfo;

import java.util.List;

public interface VariableinfoServices {


    int deleteByPrimaryKey(Integer varid);

    int insertVar(Variableinfo record);

    int insertSelective(Variableinfo record);

    Variableinfo selectByVarid(Integer varid);

    int updateByVarid(Variableinfo record);

    int updateByPrimaryKey(Variableinfo record);

    List<Variableinfo> getvariList(Variableinfo record);

    int getCountvariList(Variableinfo record);
    int deleteByVarid(Integer varid);

}
