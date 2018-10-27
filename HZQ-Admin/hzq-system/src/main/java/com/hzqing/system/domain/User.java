package com.hzqing.system.domain;

import com.hzqing.common.base.domain.BaseEntity;
import lombok.Data;

@Data
public class User extends BaseEntity{

    private String userId;
    private String userName;
    private String loginName;
    private String password;
    private String avatar;
    private String phone;
    private String delFlag;


    public boolean isAdmin() {
        return isAdmin(this.userId);
    }

    /**
     * 如果返回 True 表示修改的是admin账号，admin不允许修改
     * @param userId 用户Id
     * @return
     */
    public static boolean isAdmin(String userId) {
        return  userId != null && userId.equals("admin");
    }
}
