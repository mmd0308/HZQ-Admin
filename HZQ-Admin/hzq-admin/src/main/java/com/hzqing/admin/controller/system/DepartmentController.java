package com.hzqing.admin.controller.system;

import com.github.pagehelper.PageInfo;
import com.hzqing.common.base.controller.BaseController;
import com.hzqing.common.response.ResponseMessage;
import com.hzqing.system.domain.Department;
import com.hzqing.system.service.IDepartmentService;
import com.hzqing.system.vo.DepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @description 部门管理
 * @author hzqing
 */
@RestController
@RequestMapping("/hzq/system/department")
public class DepartmentController extends BaseController{
    @Autowired
    private IDepartmentService departmentService;

    /**
     * 获取部门管理的列表信息，带分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ResponseMessage<PageInfo<Department>> list(int pageNum,int pageSize,Department department){
        startPage(pageNum,pageSize);
        List<Department> departments = departmentService.selectTableList(department);
        return successPage(departments);
    }

    /**
     * 获取菜单树行数据
     * @return
     */
    @GetMapping("/tree")
    public ResponseMessage<List<DepartmentVO>> getTree(){
        List<DepartmentVO> departmentVOS = departmentService.selectDepartmentTree();
        return success(departmentVOS);
    }

    /**
    * 新增部门管理信息
    * @param department
    * @return
    */
    @PostMapping("/add")
    public ResponseMessage add(@RequestBody  Department department) {
        int res = departmentService.insertDepartment(department);
        return success(res);
    }

    /**
    * 根据Department Id修改部门管理
    * @param departmentId
    * @return
    */
    @GetMapping("/edit/{departmentId}")
    public ResponseMessage<Department> edit(@PathVariable String departmentId){
        Department department = departmentService.selectDepartmentById(departmentId);
        return success(department);
    }

    /**
    * 修改部门管理
    * @param department
    * @return
    */
    @PutMapping("/edit")
    public ResponseMessage editSave(@RequestBody Department department) {
        int res = departmentService.updateDepartment(department);
        return success(res);
    }


    /**
    * 根据id删除部门管理
    * @param departmentId
    * @return
    */
    @DeleteMapping("/remove")
    public ResponseMessage remove(String departmentId) {
        int res = departmentService.deleteDepartmentById(departmentId);
        if (res == -1)
            return error("部门中包含子集部门，不能删除！");
        return success();
    }

}
