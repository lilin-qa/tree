package com.dt.tree.controller;

import com.dt.tree.entity.projectinfo;
import com.dt.tree.services.projectinfoServices;
import com.dt.tree.services.projectinfoServicesImpl;

import com.dt.tree.util.getTime;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.reflect.annotation.ExceptionProxy;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@Controller
@RequestMapping(value = "/pro")
public class projectinfoController {
    @Resource
    private projectinfoServicesImpl piImpl;


    @RequestMapping(value="/getList")
    @ResponseBody
    public String  getList(projectinfo pi,HttpServletRequest request, HttpServletResponse response) throws  Exception{
       System.out.println("ssss");
       pi.setPage((pi.getPage()-1)*pi.getLimit());
       List<projectinfo> proList=piImpl.getProBy(pi);
       //总数
        Integer count=piImpl.getCountPro();
       JSONArray jsonArray=new JSONArray();
       for (int i=0;i<proList.size();i++){
           JSONObject jo=new JSONObject();
           jo.put("id",proList.get(i).getId());
           jo.put("projectname",proList.get(i).getProjectname());
           jo.put("isuse",proList.get(i).getIsuse());
           jo.put("createTime",getTime.getNowDateString(proList.get(i).getCreate_time()));
           jsonArray.add(jo);
       }
       System.out.println(jsonArray);
        String  str="";

           str="{\n" +
                  "  \"code\": 0\n" +
                  "  ,\"msg\": \"success\"\n" +
                  "  ,\"count\": "+count+"\n" +
                  "  ,\"data\":   "+jsonArray.toString()+"\n" +"}";


       return str;
    }


    @RequestMapping(value="/addPro")
    public String addPro( Integer id,HttpServletRequest request, HttpServletResponse response)throws Exception{
        if (id!=null){
            projectinfo pi=  piImpl.getProById(id);
            System.out.println(pi);
             request.setAttribute("projectinfo",pi);
        }

        return "projectAdd";
    }

    @RequestMapping(value="/savePro")
    public String savePro(projectinfo pi ,HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (pi.getIsuse()==null){
            pi.setIsuse("off");
        }
        if (pi.getId()==null){

            pi.setCreate_time(getTime.getNowDate(new Date()));
            System.out.println(getTime.getNowDate(new Date()));
            pi.setUpdate_time(getTime.getNowDate(new Date()));
            piImpl.savePro(pi);
        }else {
            pi.setUpdate_time(new Date());
            piImpl.editPro(pi);
        }

        return "content";
    }

    @RequestMapping(value="/delPro")
    @ResponseBody
    public String delPro(int id,HttpServletRequest request, HttpServletResponse response) throws Exception {

        int num = piImpl.delProById(id);

        return "content";
    }



//    public static  void main(String[] args){
//        /**
//         * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
//         * 有效字符串需满足：
//         * 左括号必须用相同类型的右括号闭合。
//         * 左括号必须以正确的顺序闭合。
//         */
//           projectinfoController pc=new projectinfoController();
////        List<Integer> intList = new ArrayList<Integer>();
////        intList.add(11);
////        intList.add(25);
////        intList.add(10);
////        intList.add(25);
////        intList.add(70);
////           System.out.println(pc.reverList(intList));
//
//        /**
//         * 给定一个整数数组 nums 和一个整数目标值 target，请
//         *你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
//         * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
//         *
//         * 思路：目标值如果是50，数组中有12，25，25，45，10
//         * 其中25，25和等于50，返回这2个下标值
//         * 然后该数组中得25不能再次使用
//         *  System.out.println(pc.reverList(intList));
//         */
//
////        int a=Integer.parseInt("123");
////       System.out.println( pc.get());
//
//     Scanner in =new Scanner(System.in);
//        System.out.print("请输入文字：");
//     String a1=in.next();
//
//     System.out.print(a1);
//
//
//
//    }
//
//    public Set get(){
//        List<Integer> intList = new ArrayList<Integer>();
//        intList.add(-10);
//        intList.add(1);
//        intList.add(10);
//        intList.add(20);
//        intList.add(0);
//
//        int[] a=new int[3];
//        Set set=new HashSet<>();
//
//        for(int i=0;i<intList.size();i++){
//             for (int j=i+1;j<intList.size();j++){
//                 for (int k=j+1;k<intList.size();k++){
//                     System.out.println(intList.get(i));
//                     if(intList.get(i)+intList.get(j)+intList.get(k)==0){
//                         a[0]=intList.get(i);
//                         a[1]=intList.get(j);
//                         a[2]=intList.get(k);
//                         set.add(a);
//                     }
//                 }
//             }
//        }
//return set;
//    }
//
//    public boolean g(String a) {
//        //假如长度是1或者不为偶数
//        if (a.length() < 2 || a.length() % 2 != 0) {
//            return false;
//        }
//        //循环替换，最大次数为s.length()/2
//        int count = 0;
//        while (count < a.length() / 2) {
//
//            a= a.replace("()", "");
//            System.out.println(a);
//             a  =   a .replace("[]", "");
//            System.out.println(a);
//            a  =   a .replace("{}", "");
//            System.out.println(a);
//            count++;
//        }
//
//        //假如循环替换完，还有值，那么肯定就不是对称结构
//        if (a.length() > 0) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    public String getIndex(int a) {
//        List<Integer> intList = new ArrayList<Integer>();
//        intList.add(11);
//        intList.add(25);
//        intList.add(10);
//        intList.add(25);
//        intList.add(70);
//        String s="";
//
//        for (int i = 0, l = intList.size(); i < l; i++) {
//            for (int j = i+1, l1 = intList.size(); j < l1; j++) {
//                if (intList.get(i) + intList.get(j)== a) {
//                   s="坐标为：" + i + "和" + (j);
//                    return s;
//                }else{
//                    continue;
//                }
//            }
//
//        }
//        return  s;
//    }
//
//
//
//    public List<Integer> reverList( List<Integer>  r){
//
//        List<Integer> a=new ArrayList<>();
//        for(int i=0;i<=r.size();i++){
//            int ri= r.size()-i;
//            if (ri!=0){
//                a.add(r.get(ri-1));
//            }
//
//        }
//
//        return  a;
//    }
//


}

