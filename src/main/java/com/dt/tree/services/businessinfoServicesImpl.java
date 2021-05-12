package com.dt.tree.services;

import com.dt.tree.dao.businessinfoDao;
import com.dt.tree.entity.businessinfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("businessinfoServices")
public class businessinfoServicesImpl implements businessinfoServices  {
    @Resource
    private businessinfoDao bidao;
    @Override
    public void saveBusi(businessinfo bi) {
          bidao.saveBusi(bi);
    }

    @Override
    public List<businessinfo> getBusiBy(businessinfo bi) {
        return bidao.getBusiBy(bi);
    }

    @Override
    public Integer getCountBusi() {
        return bidao.getCountBusi();
    }

    @Override
    public businessinfo getBusiById(Integer bid) {
        return bidao.getBusiById(bid);
    }
}
