package com.hzqing.generator.controller;

import com.hzqing.common.base.controller.BaseController;
import com.hzqing.common.response.ResponseMessage;
import com.hzqing.generator.domain.GeneratorRule;
import com.hzqing.generator.domain.TableInfo;
import com.hzqing.generator.service.IGeneratorService;
import com.hzqing.generator.util.GeneratorUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/hzq/tools/generator")
public class GeneratorController extends BaseController{
    @Autowired
    private IGeneratorService generatorService;

    @GetMapping("/list")
    @ResponseBody
    public ResponseMessage<List<TableInfo>> list(TableInfo tableInfo,int pageNum,int pageSize){
        startPage(pageNum,pageSize);
        List<TableInfo> list = generatorService.selectTableList(tableInfo);
        return successPage(list);
    }

    /**
     * 根据生成规则生成代码
     * @param response
     * @param generatorRule
     * @throws IOException
     */
    @GetMapping("/generatorCode")
    public void generatorCode(HttpServletResponse response,  GeneratorRule generatorRule) throws IOException {
        // 如果用户没有设置规则，设置默认规则
        generatorRule = GeneratorUtils.setGeneratorDefault(generatorRule);
        byte[] data = generatorService.generatorCode(generatorRule);
        response.setHeader("Content-Disposition", "attachment; filename=\"hzqing.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}
