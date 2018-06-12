package hzqing.com.admin.service.system.impl;

import hzqing.com.admin.entity.system.Dict;
import hzqing.com.admin.mapper.system.DictMapper;
import hzqing.com.admin.service.system.IDictService;
import hzqing.com.admin.vo.system.DictVO;
import hzqing.com.common.base.service.impl.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DictServiceImpl extends BaseServiceImpl<DictMapper,Dict> implements IDictService {



    @Override
    public int deletedById(String id) {
        Dict dict = new Dict();
        dict.setParentId(id);
        List<Dict> all = this.findAll(dict);
        if (all.size() == 0 ) {
            return super.deletedById(id);
        }
        return 0;
    }


    @Override
    public List<DictVO> getTree(String id) {
        Dict dict = new Dict();
        dict.setEnabled("1");
        List<Dict> dicts = this.findAll(dict);
        List<DictVO> dictVOS = this.listByPId(dicts, id);
        return listToTree(dicts,dictVOS);
    }

    @Override
    public List<Dict> getDictByPCode(String code) {
         return mapper.getDictByPCode(code);
    }

    @Override
    public Dict getIdByCode(String code) {
        Dict dict = new Dict();
        dict.setCode(code);
        List<Dict> dicts = this.findAll(dict);
        return dicts.size() > 0 ? dicts.get(0) : null;
    }

    /**
     * 将list页面转成树行结构
     * @param dicts
     * @param dictVOS
     * @return
     */
    private List<DictVO> listToTree(List<Dict> dicts,List<DictVO> dictVOS) {
        for (DictVO vo:dictVOS) {
            List<DictVO> vos = this.listByPId(dicts, vo.getId());
            if (vos.size() > 0 ) {
                vo.setChildren(vos);
                this.listToTree(dicts,vos);
            }
        }
        return dictVOS;
    }

    /**
     * 根据父级别id，获取父级别列表
     * @param dicts
     * @param parentId
     * @return
     */
    private List<DictVO> listByPId(List<Dict> dicts,String parentId) {
        List<DictVO> dictVOS = new ArrayList<>();
        for (Dict dict : dicts) {
            if (dict.getParentId().equals(parentId)) {
                DictVO dictVO = new DictVO();
                BeanUtils.copyProperties(dict,dictVO);
                dictVOS.add(dictVO);
            }
        }
        return dictVOS;
    }
}
