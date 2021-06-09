package com.dt.tree.entity;

import lombok.Data;

import java.util.Date;

@Data
public class businessinfoPro extends  Businessinfo {
    private  int id;
    private  String projectname;
    private int page;
    private int limit;




}
