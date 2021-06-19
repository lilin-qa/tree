package com.dt.tree.services;

import com.dt.tree.dao.VariableinfoDao;
import com.dt.tree.entity.Variableinfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("VariableinfoServices")
public class VariableinfoServicesImpl implements  VariableinfoServices {
    @Resource
    VariableinfoDao viDao;

    @Override
    public int deleteByPrimaryKey(Integer varid) {
        return 0;
    }

    @Override
    public int insertVar(Variableinfo record) {
        return viDao.insertVar(record);
    }


    @Override
    public int insertSelective(Variableinfo record) {
        return 0;
    }

    @Override
    public Variableinfo selectByVarid(Integer varid) {
        return viDao.selectByVarid(varid);
    }

    @Override
    public int updateByVarid(Variableinfo record) {
        return viDao.updateByVarid(record);
    }

    @Override
    public int updateByPrimaryKey(Variableinfo record) {
        return 0;
    }

    @Override
    public List<Variableinfo> getvariList(Variableinfo record) {
        return viDao.getvariList(record);
    }

    @Override
    public int getCountvariList(Variableinfo record) {
        return viDao.getCountvariList(record);
    }

    @Override
    public int deleteByVarid(Integer varid) {
        return viDao.deleteByVarid(varid);
    }
}
