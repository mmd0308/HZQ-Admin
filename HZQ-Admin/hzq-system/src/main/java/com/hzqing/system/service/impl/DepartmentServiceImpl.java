package com.hzqing.system.service.impl;

import com.hzqing.common.util.StringUtils;
import com.hzqing.common.util.UUIDUtils;
import com.hzqing.system.domain.Department;
import com.hzqing.system.domain.Menu;
import com.hzqing.system.mapper.DepartmentMapper;
import com.hzqing.system.service.IDepartmentService;
import com.hzqing.system.vo.DepartmentVO;
import com.hzqing.system.vo.MenuVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @description 部门管理
 * @author hzqing
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    /**
     * 根据条件检索列表
     * @param department
     * @return
     */
    public List<Department> selectTableList(Department department) {
        return departmentMapper.selectTableList(department);
    }

    /**
     * 新增Department
     * @param department
     * @return
     */
    public int insertDepartment(Department department) {
        department.setDepartId(UUIDUtils.get32UUID());
        department.setCreateTime(new Date());
        department.setUpdateTime(new Date());
        return departmentMapper.insertDepartment(department);
    }

    /**
    * 根据departmentId获取信息
    * @param departmentId
    * @return
    */
    public Department selectDepartmentById(String departmentId) {
        return departmentMapper.selectDepartmentById(departmentId);
    }
    /**
     * 根据departmentId批量删除Department
     * @param departmentId Department Id数组
     * @return 返回影响行
     */
    public int deleteDepartmentById(String departmentId) {
        Department department = new Department();
        department.setParentId(departmentId);
        List<Department> departments = this.selectTableList(department);
        if (departments.size() > 0)
            return -1;
        return departmentMapper.deleteDepartmentById(departmentId);
    }
    /**
     * 修改Department
     * @param department
     * @return 返回影响行数
     */
    public int updateDepartment(Department department) {
        department.setUpdateTime(new Date());
        return departmentMapper.updateDepartment(department);
    }

    /**
     * 获取部门树行结构
     * @return
     */
    @Override
    public List<DepartmentVO> selectDepartmentTree() {
        List<Department> departments = this.selectTableList(new Department());
        List<DepartmentVO> departmentVOS = new ArrayList<DepartmentVO>(departments.size());
        for (Department department: departments) {
            DepartmentVO departmentVO = new DepartmentVO();
            BeanUtils.copyProperties(department,departmentVO);
            departmentVOS.add(departmentVO);
        }
        return getTree(departmentVOS);
    }

    /**
     * 将列表信息转换成树行结构
     * @param departmentVOS
     * @return
     */
    private List<DepartmentVO> getTree(List<DepartmentVO> departmentVOS) {
        for (DepartmentVO departmentVO : departmentVOS) {
            // 如果不是顶级元素 顶级元父级别id为0
            if (StringUtils.isNotEmpty(departmentVO.getParentId()) && !departmentVO.getParentId().equals("0")){
                for (DepartmentVO m : departmentVOS) {
                    if (departmentVO.getParentId().equals(m.getDepartId())) {
                        // 找到父级别
                        if (m.getChildren() == null) {
                            m.setChildren(new ArrayList<>());
                        }
                        m.getChildren().add(departmentVO);
                    }
                }
            }
        }
        // 删除所有父级别id不是0的
        departmentVOS.removeIf(menvo -> menvo.getParentId() != null && !menvo.getParentId().equals("0"));
        return departmentVOS;
    }
}
