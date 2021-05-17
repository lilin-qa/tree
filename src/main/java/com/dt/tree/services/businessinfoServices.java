package com.dt.tree.services;

import com.dt.tree.dao.businessinfoDao;
import com.dt.tree.entity.businessinfo;

import java.util.List;

public interface businessinfoServices {


   public  void saveBusi(businessinfo bi);

   public void editBusi(businessinfo bi);

   public List<businessinfo> getBusiBy(businessinfo bi);
   public Integer getCountBusi();

   public businessinfo getBusiById(Integer bid);
   public void delBusiById(Integer bid);
}
