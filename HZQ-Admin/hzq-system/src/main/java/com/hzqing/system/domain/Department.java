package com.hzqing.system.domain;

import lombok.Data;
import java.util.*;
/**
 *
 * @description 部门管理
 * @author hzqing
 */
@Data
public class Department {

    /**
     * 
     */
    private String departId;

    /**
     * 部门名称
     */
    private String departName;

    /**
     * 排序
     */
    private Integer departSort;

    /**
     * 部门负责人
     */
    private String leader;

    /**
     * 部门电话
     */
    private String phone;

    /**
     * 是否启用 Y 启动 N 禁用 默认Y
     */
    private String enabled;

    /**
     * 删除标记, 默认’N’ ‘Y’ 表示已经删除 ’N’没有删除
     */
    private String delFlag;

    /**
     * 创建人id
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人id
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 父级id
     */
    private String parentId;

    /**
     * 备注说明
     */
    private String remark;

}
