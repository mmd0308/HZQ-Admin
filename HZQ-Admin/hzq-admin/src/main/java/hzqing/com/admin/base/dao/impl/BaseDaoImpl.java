package hzqing.com.admin.base.dao.impl;

import hzqing.com.admin.base.dao.IBaseDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Repository
public class BaseDaoImpl implements IBaseDao {
    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;


    public Object save(String str, Object obj) {
        return sqlSessionTemplate.insert(str, obj);
    }

    public Object update(String str, Object obj)  {
        return sqlSessionTemplate.update(str, obj);
    }

    public Object batchDelete(String str, List<Object> objs ){
        return sqlSessionTemplate.delete(str, objs);
    }

    public Object delete(String str, Object obj)  {
        return sqlSessionTemplate.delete(str, obj);
    }

    public Object findForObject(String str, Object obj)  {
        return sqlSessionTemplate.selectOne(str, obj);
    }

    @Override
    public Object selectById(String str, String id) {
        return sqlSessionTemplate.selectOne(str,id);
    }

    public Object findForList(String str, Object obj)  {
        return sqlSessionTemplate.selectList(str, obj);
    }

    public Object findForMap(String str, Object obj, String key, String value)  {
        return sqlSessionTemplate.selectMap(str, obj, key);
    }

    @Override
    public Object batchSave(String s, List<Map<String, String>> lists) {
        return sqlSessionTemplate.insert(s,lists);
    }


}
