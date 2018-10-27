package com.hzqing.system.vo;

import com.hzqing.system.domain.Menu;
import lombok.Data;

import java.util.List;

/**
 * @author hzqing
 * @date 2018/10/27 10:13
 */
@Data
public class MenuVO extends Menu {
    /**
     * 菜单子集
     */
    private List<Menu> children;

}
