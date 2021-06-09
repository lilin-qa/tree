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

    private String varOnline;

    private Integer isdelete;

    private Date createTime;

    private Date updateTime;

    /**
     * 拓展字段
     */
    private String expand;

    private static final long serialVersionUID = 1L;
}