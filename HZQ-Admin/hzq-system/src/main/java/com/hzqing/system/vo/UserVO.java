package com.hzqing.system.vo;

import com.hzqing.system.domain.User;
import lombok.Data;

/**
 * @author hzqing
 * @date 2018/11/10 18:01
 */
@Data
public class UserVO extends User{

    private String [] roleIds;
}
