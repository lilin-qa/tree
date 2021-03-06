package com.dt.tree.services;

import com.dt.tree.entity.projectinfo;

import java.util.List;

public interface projectinfoServices {

    public void savePro(projectinfo pi);


    public List<projectinfo> getProList();


    /**
     * 根据id查询项目
     * @param id
     * @return
     */
    public projectinfo getProById(int id);



    public int delProById(int id);

    public  List<projectinfo> getProBy(projectinfo pi);

    /**
     *
     * @return返回数量总数
     */
    public Integer getCountPro();

    /**
     * 根据Id修改信息
     * @param pi
     */
    public void editPro(projectinfo pi);
}
