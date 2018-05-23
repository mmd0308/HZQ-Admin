package hzqing.com.admin.vo.system;

import hzqing.com.admin.entity.system.Dict;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("dictvo")
public class DictVO extends Dict {
    private List<DictVO> children;

    public List<DictVO> getChildren() {
        return children;
    }

    public void setChildren(List<DictVO> children) {
        this.children = children;
    }
}
