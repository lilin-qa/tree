package com.dt.tree.services;

import com.dt.tree.dao.InterfaceinfoDao;
import com.dt.tree.entity.InterfaceinfoProBusi;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InterfaceinfoService")
public class InterfaceinfoServiceImpl implements  InterfaceinfoService {


    @Resource
    InterfaceinfoDao interfaceinfoDao;


    @Override
    public List<InterfaceinfoProBusi> getinterList(InterfaceinfoProBusi ipb) {
        return interfaceinfoDao.getinterList( ipb);
    }

    @Override
    public int getCountinterList(InterfaceinfoProBusi ipb) {
        return interfaceinfoDao.getCountinterList(ipb);
    }


}
