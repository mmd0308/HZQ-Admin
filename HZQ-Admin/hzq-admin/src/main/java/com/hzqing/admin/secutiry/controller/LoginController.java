package com.hzqing.admin.secutiry.controller;

import com.hzqing.common.base.controller.BaseController;
import com.hzqing.common.response.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hzq")
public class LoginController extends BaseController {

    @PostMapping("/login")
    public Map<String,String> login(){
        Map<String,String> map  = new HashMap<>();
        map.put("a","fjkfj");
        return map;
    }
    @PostMapping("/login_p")
    public Map<String,String> loginP(){
        Map<String,String> map  = new HashMap<>();
        map.put("a","error");
        return map;
    }
    @PostMapping("/index")
    public ResponseMessage index(String username, String password){
        return new ResponseMessage().success("index");
    }



}
