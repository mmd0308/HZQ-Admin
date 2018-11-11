package com.hzqing.system.mapper;

import com.hzqing.system.domain.Dict;

import java.util.List;
/**
 *
 * @description 数据字典管理
 * @author hzqing.com
 */
public interface DictMapper {
    /**
     * 根据条件检索列表
     * @return
     */
    List<Dict> selectTableList(Dict dict);
    /**
     * 新增 Dict
     * @param dict
     * @return
     */
    int insertDict(Dict dict);

    /**
    * 根据dictId获取用户信息
    * @param dictId
    * @return 返回Dict
    */
    Dict selectDictById(String dictId);

    /**
    * 根据dictId删除Dict
    * @param dictId
    * @return 返回影响行
    */
    int deleteDictById(String dictId);

    /**
    * 修改Dict
    * @param dict
    * @return 返回影响行数
    */
    int updateDict(Dict dict);

    /**
     * 唯一编码校验
     * @param dict
     * @return
     */
    List<Dict> checkCode(Dict dict);
}
