package com.dt.tree.dao;

import com.dt.tree.entity.projectinfo;

import java.util.List;

public interface projectinfoDao {

    public void savePro(projectinfo pi);


    public List<projectinfo> getProList();


    /**
     * 根据id查询项目
     * @param id
     * @return
     */
    public projectinfo getProById(int id);


    public void delProById(int id);
}
