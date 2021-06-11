package com.dt.tree.services;

import com.dt.tree.dao.ParameterformatDao;
import com.dt.tree.entity.Parameterformat;

public class ParameterformatServicesImpl implements  ParameterformatServices {

    ParameterformatDao pfDao;

    @Override
    public Parameterformat getParaList(Integer id) {
        return pfDao.getParaList();
    }
}
