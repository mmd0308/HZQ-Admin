package com.hzqing.admin.secutiry.controller;

import com.hzqing.common.base.controller.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hzq")
public class LoginController extends BaseController {

    @GetMapping("/login_error")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String,String> loginP(){
        Map<String,String> map  = new HashMap<>();
        map.put("a","error");
        return map;
    }



}
