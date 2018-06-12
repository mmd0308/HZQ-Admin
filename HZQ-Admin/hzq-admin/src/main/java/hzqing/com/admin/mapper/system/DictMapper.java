package hzqing.com.admin.mapper.system;

import hzqing.com.admin.entity.system.Dict;
import hzqing.com.common.base.mapper.BaseMapper;

import java.util.List;

public interface DictMapper extends BaseMapper<Dict> {
    List<Dict> getDictByPCode(String code);
}