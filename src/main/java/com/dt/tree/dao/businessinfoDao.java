package com.dt.tree.dao;

import com.dt.tree.entity.businessinfo;

import java.util.List;

public interface businessinfoDao {

    public void saveBusi(businessinfo bi);

    public List<businessinfo>  getBusiBy(businessinfo bi);

    public  businessinfo getBusiById(Integer bid);

    public Integer getCountBusi();

}
