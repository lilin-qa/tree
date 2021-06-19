package com.dt.tree.controller;


import com.dt.tree.entity.Businessinfo;
import com.dt.tree.entity.InterfaceinfoProBusi;
import com.dt.tree.entity.businessinfoPro;
import com.dt.tree.entity.projectinfo;
import com.dt.tree.services.InterfaceinfoServiceImpl;
import com.dt.tree.services.businessinfoServicesImpl;
import com.dt.tree.services.projectinfoServicesImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/inte")
public class InterfaceinfoController {



    @Resource
    projectinfoServicesImpl piServicesImpl;
    @Resource
    businessinfoServicesImpl biServicesImpl;

    @Resource
    InterfaceinfoServiceImpl  inteServicesImpl;


    @RequestMapping("/interfacepageContent")
    public String interfacepageContent(HttpServletRequest request, HttpServletResponse response) {
        List<projectinfo> proList= piServicesImpl.getProList();
        request.setAttribute("prolist",proList);


        return "interfacepageContent";
    }



    @RequestMapping("/getinteList")
    @ResponseBody
    public String getinteList(InterfaceinfoProBusi ipb, HttpServletRequest request, HttpServletResponse response) {

         List<InterfaceinfoProBusi> getbusiByproid=inteServicesImpl.getinterList(ipb);
         JSONArray jsonArray=new JSONArray();
         int count=inteServicesImpl.getCountinterList(ipb);

        for (int i=0;i<getbusiByproid.size();i++){
            JSONObject jo=new JSONObject();
            jo.put("interfaceid",getbusiByproid.get(i).getInterfaceid());
            jo.put("interfacename",getbusiByproid.get(i).getInterfacename());
            jo.put("proname",getbusiByproid.get(i).getProname());
            jo.put("businame",getbusiByproid.get(i).getBusiname());
            jo.put("interurl",getbusiByproid.get(i).getInterurl());
            jo.put("paramformat",getbusiByproid.get(i).getParamformat());
            jo.put("status",getbusiByproid.get(i).getStatus());
            jo.put("varname",getbusiByproid.get(i).getVarname());
            jsonArray.add(jo);
        }


        String  str="{\n" +
                "  \"code\": "+0+"\n" +
                "  ,\"msg\": \" "+200+"\"\n" +
                "  ,\"count\": "+count+"\n" +
                "  ,\"data\":   "+jsonArray.toString()+"\n" +"}";
        return str;

    }




    @GetMapping("/getbusiByproid")
    @ResponseBody
    public JSONArray getbusiByproid(Integer proid, HttpServletRequest request, HttpServletResponse response) {

        List<Businessinfo> getbusiByproid=biServicesImpl.getbusiByproid(proid);
        JSONArray jsonArray=new JSONArray();

        for (int i=0;i<getbusiByproid.size();i++){
            JSONObject jo=new JSONObject();
            jo.put("busid",getbusiByproid.get(i).getBusid());
            jo.put("busname",getbusiByproid.get(i).getBusname());

            jsonArray.add(jo);
        }


System.out.println(jsonArray);
        return jsonArray;

    }



    @RequestMapping("/addInte")
    public String addInte( Integer varid,HttpServletRequest request, HttpServletResponse response) {
        if (varid!=null){
            InterfaceinfoProBusi ipb=new InterfaceinfoProBusi();
            ipb.setInterfaceid(varid);
            //根据ID查询该接口信息
            List<InterfaceinfoProBusi> getbusiByproid=inteServicesImpl.getinterList(ipb);
           request.setAttribute("getbusiByproid",getbusiByproid);
        }

              //查询服务list
             List<projectinfo> proList= piServicesImpl.getProList();



        return "interfacepageAdd";
    }

}
