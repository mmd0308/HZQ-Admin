package com.hzqing.generator.controller;

import com.hzqing.common.base.controller.BaseController;
import com.hzqing.generator.domain.TableInfo;
import com.hzqing.generator.service.IGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/hzq/tools/generator")
public class GeneratorController extends BaseController{
    @Autowired
    private IGeneratorService generatorService;

    @GetMapping("/list")
    public List<TableInfo> list(TableInfo tableInfo){
        List<TableInfo> list = generatorService.selectTableList(tableInfo);
        return list;
    }

    @GetMapping("/batchGenCode")
    public void generatorCode(){
        HashMap map = new HashMap();
    }
}
