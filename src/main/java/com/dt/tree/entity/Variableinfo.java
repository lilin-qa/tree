package com.dt.tree.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * variableinfo
 * @author 
 */
@Data
public class Variableinfo implements Serializable {
    private Integer varid;

    private String varname;

    private String varTest;

    private String varQa;

    private String varOnline;

    private String varGray;

    /**
     * 0:未删除1：已删除
     */
    private Integer isdelete;

    private Date createTime;

    private Date updateTime;

    /**
     * 拓展字段
     */
    private String expand;

    private String remark;

    private static final long serialVersionUID = 1L;

    private int page;

//    public int getPage() {
//        return page;
//    }
//
//    public void setPage(int page) {
//        this.page = page;
//    }
//
//    public int getLimit() {
//        return limit;
//    }
//
//    public void setLimit(int limit) {
//        this.limit = limit;
//    }

    private int limit;
}