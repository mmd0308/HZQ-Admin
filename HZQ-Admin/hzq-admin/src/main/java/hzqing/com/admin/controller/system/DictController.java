package hzqing.com.admin.controller.system;


import hzqing.com.admin.base.controller.BaseController;
import hzqing.com.admin.entity.system.Dict;
import hzqing.com.admin.service.system.IDictService;
import hzqing.com.admin.utils.ResponseMessage;
import hzqing.com.admin.vo.system.DictVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dict")
public class DictController extends BaseController<Dict,IDictService> {
    /**
     * 获取数据字典
     * @param id
     * @return
     */
    @GetMapping("/tree/{id}")
    public ResponseMessage<List<DictVO>> getTree(@PathVariable String id){
        List<DictVO> trees = baseService.getTree(id);
        return new ResponseMessage<>().success(trees);
    }

    /**
     * 根据父级别的编码，获取其下面的字典
     * @param code
     * @return
     */
    @GetMapping("{code}/code")
    public ResponseMessage getDictByCode(@PathVariable String code){
        List<Dict> dicts = baseService.getDictByPCode(code);
        return new ResponseMessage().success(dicts);
    }
}
