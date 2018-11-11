package com.hzqing.system.service;

import com.hzqing.system.domain.Dict;
import com.hzqing.system.vo.DictVO;

import java.util.List;
/**
 *
 * @description 数据字典管理
 * @author hzqing.com
 */
public interface IDictService {
    /**
     * 根据条件检索列表
     * @param dict
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
     * 数据字典树
     * @return
     */
    List<DictVO> selectDictTree();

    /**
     * 编码唯一校验
     * @param dict
     * @return
     */
    boolean checkCode(Dict dict);
}
