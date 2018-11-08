package com.hzqing.system.service;

import com.hzqing.system.domain.Department;
import com.hzqing.system.vo.DepartmentVO;

import java.util.List;
/**
 *
 * @description 部门管理
 * @author hzqing
 */
public interface IDepartmentService {
    /**
     * 根据条件检索列表
     * @param department
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
     * 根据departmentId获取用户信息
     * @param departmentId
     * @return 返回Department
     */
    Department selectDepartmentById(String departmentId);
    /**
     * 根据departmentId批量删除Department
     * @param departmentIds 用户Id数组
     * @return 返回影响行
     */
    int deleteDepartmentById(String departmentIds);
    /**
     * 修改Department
     * @param department
     * @return 返回影响行数
     */
    int updateDepartment(Department department);

    /**
     * 获取部门树行结构
     * @return
     */
    List<DepartmentVO> selectDepartmentTree();

}
