package com.hzqing.system.service.impl;

import com.hzqing.common.util.StringUtils;
import com.hzqing.common.util.UUIDUtils;
import com.hzqing.system.domain.Department;
import com.hzqing.system.domain.Dict;
import com.hzqing.system.domain.Menu;
import com.hzqing.system.mapper.DictMapper;
import com.hzqing.system.service.IDictService;
import com.hzqing.system.vo.DepartmentVO;
import com.hzqing.system.vo.DictVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @description 数据字典管理
 * @author hzqing.com
 */
@Service
public class DictServiceImpl implements IDictService {
    @Autowired
    private DictMapper dictMapper;
    /**
     * 根据条件检索列表
     * @param dict
     * @return
     */
    public List<Dict> selectTableList(Dict dict) {
        return dictMapper.selectTableList(dict);
    }

    /**
     * 新增Dict
     * @param dict
     * @return
     */
    public int insertDict(Dict dict) {
        dict.setDictId(UUIDUtils.get32UUID());
        return dictMapper.insertDict(dict);
    }

    /**
    * 根据dictId获取信息
    * @param dictId
    * @return
    */
    public Dict selectDictById(String dictId) {
        return dictMapper.selectDictById(dictId);
    }
    /**
     * 根据dictId批量删除Dict
     * @param dictId Dict Id数组
     * @return 返回影响行
     */
    public int deleteDictById(String dictId) {
        Dict dict = new Dict();
        dict.setParentId(dictId);
        List<Dict> dicts = this.selectTableList(dict);
        if (dicts.size() > 0)
            return -1;
        return dictMapper.deleteDictById(dictId);
    }
    /**
     * 修改Dict
     * @param dict
     * @return 返回影响行数
     */
    public int updateDict(Dict dict) {
        return dictMapper.updateDict(dict);
    }

    /**
     * 数据字典树行结构
     * @return
     */
    @Override
    public List<DictVO> selectDictTree() {
        List<Dict> dicts = this.selectTableList(new Dict());
        List<DictVO> dictVOS = new ArrayList<DictVO>(dicts.size());
        for (Dict dict: dicts) {
            DictVO dictVO = new DictVO();
            BeanUtils.copyProperties(dict,dictVO);
            dictVOS.add(dictVO);
        }
        return getTree(dictVOS);
    }

    /**
     * 编码唯一校验
     * @param dict
     * @return
     */
    @Override
    public boolean checkCode(Dict dict) {
        List<Dict> menus = dictMapper.checkCode(dict);
        if (menus.size() == 0 )
            return true;
        return false;
    }

    /**
     * 将列表信息转换成树行结构
     * @param dictVOS
     * @return
     */
    private List<DictVO> getTree(List<DictVO> dictVOS) {
        for (DictVO dictVO : dictVOS) {
            // 如果不是顶级元素 顶级元父级别id为0
            if (StringUtils.isNotEmpty(dictVO.getParentId()) && !dictVO.getParentId().equals("0")){
                for (DictVO m : dictVOS) {
                    if (dictVO.getParentId().equals(m.getDictId())) {
                        // 找到父级别
                        if (m.getChildren() == null) {
                            m.setChildren(new ArrayList<>());
                        }
                        m.getChildren().add(dictVO);
                    }
                }
            }
        }
        // 删除所有父级别id不是0的
        dictVOS.removeIf(menvo -> menvo.getParentId() != null && !menvo.getParentId().equals("0"));
        return dictVOS;
    }
}
