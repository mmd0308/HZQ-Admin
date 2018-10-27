package com.hzqing.system.domain;

import lombok.Data;
/**
 *
 * @description 用户角色关系表
 * @author hzqing.com
 */
@Data
public class UserRole {

    /**
     * 主键
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;

}
