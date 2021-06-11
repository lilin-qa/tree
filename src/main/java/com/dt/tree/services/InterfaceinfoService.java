package com.dt.tree.services;

import com.dt.tree.entity.InterfaceinfoProBusi;

import java.util.List;

public interface InterfaceinfoService {


    List<InterfaceinfoProBusi> getinterList(InterfaceinfoProBusi ipb);

    int getCountinterList(InterfaceinfoProBusi ipb);
}
