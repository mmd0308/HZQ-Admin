package com.hzqing.system.controller;

import com.hzqing.common.base.controller.BaseController;
import com.hzqing.common.response.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hzq/system/login")
public class LoginController extends BaseController {

    @PostMapping("/login")
    public ResponseMessage login(String username, String password){
        System.out.println(username + " ------ " + password);
        return new ResponseMessage().success("token");
    }

}
