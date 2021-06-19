package com.dt.tree.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * interfaceinfo
 * @author 
 */
@Data
public class Interfaceinfo implements Serializable {
    private Integer interfaceid;

    private String interfacename;

    /**
     * 0:get;1:post
     */
    private Integer method;

    private Integer varid;

    private String interurl;

    private String paramformat;

    /**
     * 0未启用；1已启用
     */
    private Integer status;

    private String remark;

    private String interfaceHearder;

    private String interfaceParam;

    private Integer interfaceToProid;

    private Integer interfaceToBusid;



    private Date createTime;

    private Date updateTime;

    private String expland;

    /**
     * 0未删除1已删除
     */
    private Integer isdelete;

    private static final long serialVersionUID = 1L;
}