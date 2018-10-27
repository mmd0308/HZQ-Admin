package com.hzqing.admin.secutiry.controller;

import com.hzqing.common.base.controller.BaseController;
import com.hzqing.common.response.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController extends BaseController {

    @PostMapping("/login")
    public ResponseMessage login(String username, String password){
        return new ResponseMessage().success("token");
    }

}
