package com.dt.tree.controller;


import com.dt.tree.entity.Variableinfo;
import com.dt.tree.services.VariableinfoServicesImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/vari")
public class VariableinfoController {
    @Resource
    VariableinfoServicesImpl viServicesImpl;

    @RequestMapping("/variableContent")
    public String variableContent(HttpServletRequest request, HttpServletResponse response) {
        return "variableContent";
    }


    @RequestMapping("getvariList")
    @ResponseBody
    public String getvariList(Variableinfo vari,HttpServletRequest  request,HttpServletResponse response){
        vari.setPage((vari.getPage()-1)*vari.getLimit());
        String code="";
        String msg="";
        JSONArray jsonArray=new JSONArray();
        List<Variableinfo> variList=new ArrayList<Variableinfo>();
       int count=viServicesImpl.getCountvariList(vari);
       System.out.println(variList);
        try{

            variList=viServicesImpl.getvariList(vari);
            for (int i=0;i<variList.size();i++){
                JSONObject jo=new JSONObject();
                jo.put("varid",variList.get(i).getVarid());
                jo.put("varname",variList.get(i).getVarname());
                jo.put("remark",variList.get(i).getRemark());
                jsonArray.add(jo);
            }
            code="0";
            msg="success";
        }catch (Exception ex){
           ex.printStackTrace();
            code="500";
            msg="error";
        }

        String  str="{\n" +
                "  \"code\": "+code+"\n" +
                "  ,\"msg\": \" "+msg+"\"\n" +
                "  ,\"count\": "+count+"\n" +
                "  ,\"data\":   "+jsonArray.toString()+"\n" +"}";
        return str;
    }



    @RequestMapping("/addVari")
    public String addVari(Integer varid,HttpServletRequest request, HttpServletResponse response) {
         Variableinfo vi =viServicesImpl.selectByVarid(varid);

        request.setAttribute("variable",vi);
        return "variableAdd";
    }

    @RequestMapping("/saveVar")
    public String saveVar(Variableinfo vi ,HttpServletRequest request, HttpServletResponse response) {

        if (vi.getVarid() != null) { //修改
            viServicesImpl.updateByVarid(vi);
        } else {
             viServicesImpl.insertVar(vi);
        }

        return  "variableContent";
    }



    @RequestMapping("/delVari")
    public String delVari(Integer varid ,HttpServletRequest request, HttpServletResponse response) {


            viServicesImpl.deleteByVarid(varid);


        return  "variableContent";
    }
}
