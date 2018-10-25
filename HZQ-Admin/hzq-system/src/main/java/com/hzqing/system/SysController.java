package com.hzqing.system;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("system")
public class SysController {
    @GetMapping("sys")
    public String system(){
        return "system";
    }
}
