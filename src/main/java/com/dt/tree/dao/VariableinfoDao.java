package com.dt.tree.dao;

import com.dt.tree.entity.Variableinfo;

import java.util.List;

public interface VariableinfoDao {


    int insertVar(Variableinfo record);

    List<Variableinfo> getvariList(Variableinfo record);

     int getCountvariList(Variableinfo record);

    Variableinfo selectByVarid(Integer varid);

    int updateByVarid(Variableinfo record);

    int updateByPrimaryKey(Variableinfo record);
    int deleteByVarid(Integer varid);
}