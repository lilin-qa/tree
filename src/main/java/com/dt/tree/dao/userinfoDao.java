package com.dt.tree.dao;

import com.dt.tree.entity.userinfo;

public interface userinfoDao {


    //根据用户名密码查找用户
    userinfo Sel(userinfo ui);
}
