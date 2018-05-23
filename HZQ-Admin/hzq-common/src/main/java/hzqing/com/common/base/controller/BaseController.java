package hzqing.com.common.base.controller;

import hzqing.com.common.base.service.IBaseService;
import hzqing.com.common.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class BaseController<M extends IBaseService<T>,T> {
    @Autowired
    protected M baseService;
    @GetMapping("/find")
    public ResponseMessage<String> find(){
        String res = baseService.find();
        return new ResponseMessage<>().success(res);
    }

}
