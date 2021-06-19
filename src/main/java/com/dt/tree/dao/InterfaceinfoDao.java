package com.dt.tree.dao;

import com.dt.tree.entity.Interfaceinfo;
import com.dt.tree.entity.InterfaceinfoProBusi;

import java.util.List;

public interface InterfaceinfoDao {
    int deleteByPrimaryKey(Integer interfaceid);

    int insert(Interfaceinfo record);

    int insertSelective(Interfaceinfo record);

    Interfaceinfo selectByPrimaryKey(Integer interfaceid);

    int updateByPrimaryKeySelective(Interfaceinfo record);

    int updateByPrimaryKey(Interfaceinfo record);

     List<InterfaceinfoProBusi> getinterList(InterfaceinfoProBusi ipb);

     int getCountinterList(InterfaceinfoProBusi ipb);
}