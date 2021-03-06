package com.dt.tree.services;

import com.dt.tree.dao.projectinfoDao;
import com.dt.tree.entity.projectinfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("projectinfoServices")
public class projectinfoServicesImpl implements projectinfoServices {
    @Resource
     private projectinfoDao pidao;

    @Override
    public void savePro(projectinfo pi) {
       pidao.savePro(pi);
    }

    @Override
    public List<projectinfo> getProList() {
        return pidao.getProList();

    }

    /**
     * 根据id查询项目
     * @param id
     * @return
     */
    @Override
    public projectinfo getProById(int id) {
        return pidao.getProById(id);
    }


    @Override
    public int delProById(int id){
        return pidao.delProById(id);
    }

    @Override
    public List<projectinfo> getProBy(projectinfo pi) {
        return pidao.getProBy(pi);
    }

    @Override
    public Integer getCountPro() {
        return pidao.getCountPro();
    }

    @Override
    public void editPro(projectinfo pi) {
        pidao.editPro(pi);
    }
}
