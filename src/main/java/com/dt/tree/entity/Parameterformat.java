package com.dt.tree.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * parameterformat
 * @author 
 */
@Data
public class Parameterformat implements Serializable {
    private Integer id;

    /**
     * 接口中传递的数据格式
     */
    private String text;

    private Date createTime;

    private Date updateTime;

    private String expand;

    private String remarks;

    private static final long serialVersionUID = 1L;
}