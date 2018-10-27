package com.hzqing.system.domain;

import lombok.Data;
import java.util.*;
/**
 *
 * @description 角色管理
 * @author hzqing.com
 */
@Data
public class Role {

    /**
     * 
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 排序
     */
    private Integer roleSort;

    /**
     * 是否启用,‘N’ 表示禁用 ’Y’表示启用 默认是: ‘Y’
     */
    private String enabled;

    /**
     * 是否被删除 N 表示没有。Y表示被删除 默认是N
     */
    private String delFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建着id
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
     * 权限编码，唯一校验
     */
    private String permCode;

}
