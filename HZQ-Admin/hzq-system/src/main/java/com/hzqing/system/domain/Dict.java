package com.hzqing.system.domain;

import lombok.Data;
import java.util.*;
/**
 *
 * @description 数据字典管理
 * @author hzqing.com
 */
@Data
public class Dict {

    /**
     * 
     */
    private String dictId;

    /**
     * 字典名称,字典标签
     */
    private String dictName;

    /**
     * 字典数据值  唯一校验
     */
    private String dictCode;

    /**
     * 排序
     */
    private Integer dictSort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 父级编码,默认是0 
     */
    private String parentId;

    /**
     * 创建着id
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新着
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否启用,‘N’ 表示禁用 ’Y’表示启用 默认是: ‘Y’
     */
    private String enabled;

}
