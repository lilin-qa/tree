package com.dt.tree.controller;

import com.dt.tree.entity.projectinfo;
import com.dt.tree.entity.userinfo;
import com.dt.tree.services.projectinfoServicesImpl;
import com.dt.tree.services.userinfoServices;
import com.dt.tree.services.userinfoServicesImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.util.ArrayList;
import java.util.List;

@Controller
/**
 * @Controller：表明该类内的所有方法默认返回页面路径，加 @ResponseBody的方法可返回数据
 *
 * @ RestController ：是 @ResponseBody 和 @Controller 的组合注解，返回 json 数据，；原先返回 json 数据需要 @ResponseBody 和 @Controller 配合
 *
 * 作者：Ada54
 * 链接：https://www.jianshu.com/p/4136281b378e
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
@RequestMapping(value = "/ind")
public class userinfoController {








    @Resource
    private userinfoServicesImpl uiServicesImpl;
    @Resource
    private projectinfoServicesImpl piServicesImpl;

    @RequestMapping(value="/sel")
    public String sel(HttpServletRequest request, HttpServletResponse response, userinfo ui){
//        userinfo u=new userinfo();
//        try{
//            ui.setPassword("123456");
//            ui.setUsername("admin");
//             u=uiServicesImpl.Sel(ui);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//        request.setAttribute("userinfo",ui);
    System.out.println(" = ==============");
        return  "index1";
    }

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/business")
    public String  business(HttpServletRequest request, HttpServletResponse response){
         return  "business";
    }


    @RequestMapping(value="/variable")
    public String  variable(HttpServletRequest request, HttpServletResponse response){
        return  "variable";
    }
    @RequestMapping(value="/interfacepage")
    public String  interfacepage(HttpServletRequest request, HttpServletResponse response){
        return  "interfacepage";
    }

    /**
     * 去新增项目页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/goAddPro")
    public String goAddPro(HttpServletRequest request, HttpServletResponse response){

        return "project";
    }
    @RequestMapping("/index")
    public String index() {
        return "content";
    }
    @RequestMapping("/businessContent")
    public String businessContent(HttpServletRequest request, HttpServletResponse response) {
      List<projectinfo> proList= piServicesImpl.getProList();
      request.setAttribute("prolist",proList);

        return "businessContent";
    }



}
