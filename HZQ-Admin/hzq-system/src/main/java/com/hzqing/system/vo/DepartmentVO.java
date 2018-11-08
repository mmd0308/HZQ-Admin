package com.hzqing.system.vo;

import com.hzqing.system.domain.Department;
import lombok.Data;

import java.util.List;

/**
 * @author hzqing
 * @date 2018/11/8 09:12
 */
@Data
public class DepartmentVO extends Department{
    /**
     * 获取下层部门
     */
    public List<Department> children;
}
