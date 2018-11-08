package com.hzqing.system.mapper;

import com.hzqing.system.domain.Department;

import java.util.List;
/**
 *
 * @description 部门管理
 * @author hzqing
 */
public interface DepartmentMapper {
    /**
     * 根据条件检索列表
     * @return
     */
    List<Department> selectTableList(Department department);
    /**
     * 新增 Department
     * @param department
     * @return
     */
    int insertDepartment(Department department);

    /**
    * 根据departmentId获取信息
    * @param departmentId
    * @return 返回Department
    */
    Department selectDepartmentById(String departmentId);

    /**
    * 根据departmentId批量删除Department
    * @param departmentId 部门id
    * @return 返回影响行
    */
    int deleteDepartmentById(String departmentId);

    /**
    * 修改Department
    * @param department
    * @return 返回影响行数
    */
    int updateDepartment(Department department);
}
