package com.dt.tree.services;

import com.dt.tree.dao.BusinessinfoDao;
import com.dt.tree.entity.Businessinfo;
import com.dt.tree.entity.businessinfoPro;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("businessinfoServices")
public class businessinfoServicesImpl implements businessinfoServices  {
    @Resource
    private BusinessinfoDao bidao;

    @Override
    public void saveBusi(Businessinfo bi) {
       bidao.insert(bi);
    }

    @Override
    public void editBusi(Businessinfo bi) {
      bidao.updateByPrimaryKey(bi);
    }

    @Override
    public List<Businessinfo> getBusiBy(Businessinfo bi) {
        return null;
    }

    @Override
    public Integer getCountBusi( ) {
        return bidao.getCountBusi();
    }

    @Override
    public Businessinfo getBusiById(Integer bid) {
        return bidao.selectByPrimaryKey(bid);
    }

    @Override
    public void delBusiById(Integer bid) {
         bidao.deleteByPrimaryKey(bid);
    }

    @Override
    public List<businessinfoPro> selectbusAndPro(businessinfoPro busPro) {
        return bidao.selectbusAndPro(busPro);
    }
}
