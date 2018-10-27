package com.hzqing.system.controller;

import com.github.pagehelper.PageInfo;
import com.hzqing.common.base.controller.BaseController;
import com.hzqing.common.response.ResponseMessage;
import com.hzqing.system.domain.Button;
import com.hzqing.system.service.IButtonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @description 按钮表
 * @author hzqing
 */
@RestController
@RequestMapping("/hzq/system/button")
public class ButtonController extends BaseController{
    @Autowired
    private IButtonService buttonService;

    /**
     * 获取按钮表的列表信息，带分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ResponseMessage<PageInfo<Button>> list(int pageNum,int pageSize,Button button){
        startPage(pageNum,pageSize);
        List<Button> buttons = buttonService.selectTableList(button);
        return successPage(buttons);
    }

    /**
    * 新增按钮表信息
    * @param button
    * @return
    */
    @PostMapping("/add")
    public ResponseMessage add(@RequestBody  Button button) {
        int res = buttonService.insertButton(button);
        return new ResponseMessage().success();
    }

    /**
    * 根据Button Id修改按钮表
    * @param buttonId
    * @return
    */
    @GetMapping("/edit/{buttonId}")
    public ResponseMessage<Button> edit(@PathVariable String buttonId){
        Button button = buttonService.selectButtonById(buttonId);
        return new ResponseMessage<Button>().success(button);
    }

    /**
    * 修改按钮表
    * @param button
    * @return
    */
    @PutMapping("/edit")
    public ResponseMessage editSave(@RequestBody Button button) {
        int res = buttonService.updateButton(button);
        return new ResponseMessage().success(res);
    }


    /**
    * 删除按钮表
    * @param buttonIds 多个ID,使用'，'分割
    * @return
    */
    @DeleteMapping("/remove")
    public ResponseMessage remove(String buttonIds) {
        int res = buttonService.deleteButtonByIds(buttonIds);
        return new ResponseMessage().success();
    }

}
