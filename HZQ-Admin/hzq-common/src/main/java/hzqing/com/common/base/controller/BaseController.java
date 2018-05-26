package hzqing.com.common.base.controller;

import com.github.pagehelper.PageInfo;
import hzqing.com.common.base.service.IBaseService;
import hzqing.com.common.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BaseController<M extends IBaseService<T>,T> {
    @Autowired
    protected M baseService;

    /**
     * 新增
     * @param t
     * @return 返回状态码和影响行数目
     */
    @PostMapping("/add")
    public ResponseMessage<Integer> add(@RequestBody T t){
        return  new ResponseMessage<Integer>().success(baseService.save(t));
    }

    /**
     * 根据id获取信息
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public ResponseMessage<T> get(@PathVariable String id){
        T t = baseService.getById(id);
        return new ResponseMessage<T>().success(t);
    }

    /**
     * 檢查code是否唯一
     * @param code
     * @return code不存在true表示可以使用  存在false
     */
    @GetMapping("/check/{code}/code")
    public ResponseMessage checkCode(@PathVariable String code, String id){
        boolean res = baseService.checkCode(code,id);
        return new ResponseMessage<Boolean>().success(res);
    }

    /**
     * 根据id进行修改
     * @param t
     * @return
     */
    @PutMapping("/put/{id}")
    public ResponseMessage<Integer> update(@RequestBody T t){
        int res = baseService.update(t);
        return  new ResponseMessage<>().success(res);
    }

    /**
     * 按照条件查询，默认查询所有 带分页
     * @param t 根据条件获取所有数据,带分页
     * @return
     */
    @GetMapping("/page")
    public ResponseMessage<PageInfo<T>> page(Integer page, Integer pageSize, T t){
        PageInfo<T> res = baseService.queryPage(page,pageSize,t);
        return new ResponseMessage<>().success(res);
    }

    /**
     * 默认获取所有数据
     * @param t 根据条件获取所有数据,没有分页
     * @return
     */
    @GetMapping("/all")
    public ResponseMessage<List<T>> all(T t){
        return new ResponseMessage<List<T>>().success(baseService.findAll(t));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseMessage<Boolean> delete(@PathVariable String id){
        int res = baseService.deletedById(id);
        Boolean bool = false;
        if (res>0){
            bool = true;
        }
        return new ResponseMessage<Boolean>().success(bool);
    }
}
