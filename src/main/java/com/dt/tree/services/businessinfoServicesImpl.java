package com.dt.tree.services;

import com.dt.tree.dao.businessinfoDao;
import com.dt.tree.entity.businessinfo;

import java.util.List;

public class businessinfoServicesImpl implements businessinfoServices  {

    private businessinfoDao bidao;
    @Override
    public void savePro(businessinfo bi) {
          bidao.savePro(bi);
    }

    @Override
    public List<businessinfo> getBusiBy(businessinfo bi) {
        return bidao.getBusiBy(bi);
    }

    @Override
    public Integer getCountBusi() {
        return bidao.getCountBusi();
    }
}
