package com.hzqing.system.domain;

import lombok.Data;
import java.util.*;
/**
 *
 * @description 菜单表
 * @author hzqing
 */
@Data
public class Menu {

    /**
     * 菜单id
     */
    private String menuId;

    /**
     * 名称
     */
    private String menuName;

    /**
     * 上级id
     */
    private String parentId;

    /**
     * 排序
     */
    private Integer menuSort;

    /**
     * 类型 M 菜单 C目录
     */
    private String menuType;

    /**
     * 是否启用,‘N’ 表示禁用 ’Y’表示启用 默认是: ‘Y’
     */
    private String enabled;

    /**
     * 图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;

    /**
     * 权限标示 唯一
     */
    private String permission;

    /**
     * 创建者id
     */
    private String createBy;

    /**
     *  创建时间
     */
    private Date createTime;

    /**
     * 更新者id
     */
    private String updateBy;

    /**
     * 创建时间
     */
    private Date updateTime;

}
