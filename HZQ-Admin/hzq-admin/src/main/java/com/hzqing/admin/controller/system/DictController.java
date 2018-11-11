package com.hzqing.admin.controller.system;

import com.github.pagehelper.PageInfo;
import com.hzqing.common.base.controller.BaseController;
import com.hzqing.common.response.ResponseMessage;
import com.hzqing.system.domain.Dict;
import com.hzqing.system.service.IDictService;
import com.hzqing.system.vo.DictVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @description 数据字典管理
 * @author hzqing.com
 */
@RestController
@RequestMapping("/hzq/system/dict")
public class DictController extends BaseController{
    @Autowired
    private IDictService dictService;

    /**
     * 获取数据字典管理的列表信息，带分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ResponseMessage<PageInfo<Dict>> list(int pageNum,int pageSize,Dict dict){
        startPage(pageNum,pageSize);
        List<Dict> dicts = dictService.selectTableList(dict);
        return successPage(dicts);
    }

    /**
     * 获取菜单树行数据
     * @return
     */
    @GetMapping("/tree")
    public ResponseMessage<List<DictVO>> getTree(){
        List<DictVO> dictVOS = dictService.selectDictTree();
        return success(dictVOS);
    }

    /**
    * 新增数据字典管理信息
    * @param dict
    * @return
    */
    @PostMapping("/add")
    public ResponseMessage add(@RequestBody  Dict dict) {
        int res = dictService.insertDict(dict);
        return new ResponseMessage().success();
    }

    /**
    * 根据Dict Id修改数据字典管理
    * @param dictId
    * @return
    */
    @GetMapping("/edit/{dictId}")
    public ResponseMessage<Dict> edit(@PathVariable String dictId){
        Dict dict = dictService.selectDictById(dictId);
        return new ResponseMessage<Dict>().success(dict);
    }

    /**
    * 修改数据字典管理
    * @param dict
    * @return
    */
    @PutMapping("/edit")
    public ResponseMessage editSave(@RequestBody Dict dict) {
        int res = dictService.updateDict(dict);
        return new ResponseMessage().success(res);
    }


    /**
    * 删除数据字典管理
    * @param dictId 数据字典id
    * @return
    */
    @DeleteMapping("/remove")
    public ResponseMessage remove(String dictId) {
        int res = dictService.deleteDictById(dictId);
        return new ResponseMessage().success();
    }

}
