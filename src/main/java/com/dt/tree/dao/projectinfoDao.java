package com.dt.tree.dao;

import com.dt.tree.entity.projectinfo;

import java.util.List;

public interface projectinfoDao {

    public void savePro(projectinfo pi);

    /**
     * 查询所有list
     * @return
     */
    public List<projectinfo> getProList();


    /**
     * 根据id查询项目
     * @param id
     * @return
     */
    public projectinfo getProById(int id);

    /**
     * 根据Id删除该条数据
     * @param id
     */
    public int delProById(int id);

    /**
     *
     * @param pi projectinfo 实体
     * @return  projectinfo数组
     */
    public  List<projectinfo> getProBy(projectinfo pi);

    /**
     *
     * @return返回数量总数
     */
    public Integer getCountPro();

    /**
     * 根据Id修改昵称
     * @param pi
     */
    public void  editPro(projectinfo pi);
}
