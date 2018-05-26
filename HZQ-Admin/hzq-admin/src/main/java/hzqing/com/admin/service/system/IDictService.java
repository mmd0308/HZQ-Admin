package hzqing.com.admin.service.system;


import hzqing.com.admin.entity.system.Dict;
import hzqing.com.admin.vo.system.DictVO;
import hzqing.com.common.base.service.IBaseService;

import java.util.List;

public interface IDictService extends IBaseService<Dict> {
    List<DictVO> getTree(String id);

    List<Dict> getDictByPCode(String code);

    Dict getIdByCode(String auth);
}
