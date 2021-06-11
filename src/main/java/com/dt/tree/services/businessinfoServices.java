package com.dt.tree.services;




import com.dt.tree.entity.Businessinfo;
import com.dt.tree.entity.businessinfoPro;

import java.util.List;

public interface businessinfoServices {


   public  void saveBusi(Businessinfo bi);

   public void editBusi(Businessinfo bi);

   public List<Businessinfo> getBusiBy(Businessinfo bi);

   /**
    * 查询当前有多少数据
    * @return
    */
   public Integer getCountBusi();

   public Businessinfo getBusiById(Integer bid);
   public void delBusiById(Integer bid);

    public List<businessinfoPro> selectbusAndPro(businessinfoPro busPro);

   List<Businessinfo> getbusiByproid(Integer proid);
}
