package com.dt.tree.dao;

import com.dt.tree.entity.businessinfo;

import java.util.List;

public interface businessinfoDao {

    public void savePro(businessinfo bi);

    public List<businessinfo>  getBusiBy(businessinfo bi);

    public Integer getCountBusi();

}
