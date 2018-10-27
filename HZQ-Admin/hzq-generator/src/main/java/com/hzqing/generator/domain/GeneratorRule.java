package com.hzqing.generator.domain;

import lombok.Data;

import java.util.List;

/**
 * 代码生成器规则实体类
 * @author hzqing
 * @date 2018/10/26 08:59
 */
@Data
public class GeneratorRule {
    /**
     * 生成表的名称
     */
    private List<String> tables;

    /**
     * 实体类生成规则
     */
    private String entityRule;
    /**
     * 包名称
     */
    private String packageName;
    /**
     * 模块名称
     */
    private String moudleName;
    /**
     * 生成作者
     */
    private String author;
}
