package com.dt.tree.controller;

import com.dt.tree.entity.businessinfo;
import com.dt.tree.services.businessinfoServicesImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/busi")
public class businessinfoController {

    @Resource
    private businessinfoServicesImpl biImpl;




    @RequestMapping(value="/getBusinessBySearch")
    @ResponseBody
    public String  getBusinessBySearch(businessinfo bi, HttpServletRequest request, HttpServletResponse response) throws  Exception{
        System.out.println("getBusinessBySearch");
        bi.setPage((bi.getPage()-1)*bi.getLimit());
        JSONArray jsonArray=new JSONArray();
        Integer count=biImpl.getCountBusi();


            if (count>0){
                List<businessinfo> busiList=biImpl.getBusiBy(bi);

                for (int i=0;i<busiList.size();i++){
                    JSONObject jo=new JSONObject();
                    jo.put("busid",busiList.get(i).getBusid());
                    jo.put("busname",busiList.get(i).getBusname());
                    jo.put("isuse",busiList.get(i).getIsuse());
                    jsonArray.add(jo);
                }

            }
            System.out.println(jsonArray);



        String  str="{\n" +
                "  \"code\": 0\n" +
                "  ,\"msg\": \"\"\n" +
                "  ,\"count\": "+count+"\n" +
                "  ,\"data\":   "+jsonArray.toString()+"\n" +"}";
        return str;
    }

    @RequestMapping(value="/addBusi")
    public String addBusi( Integer id,HttpServletRequest request, HttpServletResponse response)throws Exception{
        if (id!=null){
            businessinfo busi=  biImpl.getBusiById(id);
            System.out.println(busi);
            request.setAttribute("business",busi);
        }

        return "businessAdd";
    }
}
