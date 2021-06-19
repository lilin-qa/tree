package com.dt.tree.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * businessinfo
 * @author 
 */
@Data
public class Businessinfo implements Serializable {
    private Integer busid;

    private String busname;

    private String isuse;

    private String remarks;

    private Integer proid;

    /**
     * 是否删除 0：未删除1已删除
     */
    private Integer isdelete;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}