package com.dt.tree.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/vari")
public class VariableinfoController {

    @RequestMapping("/variableContent")
    public String variableContent(HttpServletRequest request, HttpServletResponse response) {
        return "variableContent";
    }
    @RequestMapping("/addVari")
    public String addVari(HttpServletRequest request, HttpServletResponse response) {
        return "variableAdd";
    }

}
