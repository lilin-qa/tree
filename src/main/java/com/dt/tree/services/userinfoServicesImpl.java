package com.dt.tree.services;

import com.dt.tree.dao.userinfoDao;
import com.dt.tree.entity.userinfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userinfoServices")
public class userinfoServicesImpl implements  userinfoServices {
    @Resource
    private userinfoDao ud;

    @Override
    public userinfo Sel(userinfo ui) {
        return ud.Sel(ui);
    }
}
