package com.hzqing.system.domain;

import lombok.Data;
import java.util.*;
/**
 *
 * @description 按钮表
 * @author hzqing
 */
@Data
public class Button {

    /**
     * 
     */
    private String butId;

    /**
     * 菜单id
     */
    private String menuId;

    /**
     * 按钮名称
     */
    private String butName;

    /**
     * 权限标示,唯一
     */
    private String permission;

    /**
     * 是否启用,‘N’ 表示禁用 ’Y’表示启用 默认是: ‘Y’
     */
    private String enabled;

    /**
     * 
     */
    private String createId;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private String updateId;

    /**
     * 
     */
    private Date updateTime;

}
