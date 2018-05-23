package hzqing.com.admin.service.system;


import hzqing.com.admin.base.service.IBaseService;
import hzqing.com.admin.entity.system.Dict;
import hzqing.com.admin.vo.system.DictVO;

import java.util.List;

public interface IDictService extends IBaseService<Dict> {
    List<DictVO> getTree(String id);

    List<Dict> getDictByPCode(String code);

    Dict getIdByCode(String auth);
}
