<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.hzqing.${moudleName}.mapper.${className}Mapper">

    <resultMap id="baseResult" type="${classNameLower}">
<#list columns as col>
        <result column="${col.columnName}" property="${col.attrName}"/>
 </#list>
    </resultMap>

    <sql id="select${className}">
        SELECT
<#list columns as col>
            ${col.columnName}<#if  col_index != columns?size-1>,</#if>
</#list>
        FROM
            ${tableName}
    </sql>

    <#--按照条件查询所有-->
    <select id="selectTableList" resultMap="baseResult">
        <include refid="select${className}"/>
        <where>
            1=1
        <#list columns as col>
            <#if  col.columnName == 'del_flag'>AND del_flag = 'N'</#if>
            <if test="${col.attrName} != null and ${col.attrName} != ''">
              AND  ${col.columnName} =  <#noparse>#</#noparse>{${col.attrName}}
            </if>
        </#list>
        </where>
    </select>
    <#--插入数据-->
    <insert id="insert${className}" parameterType="${classNameLower}" >
        INSERT INTO ${tableName} (
        <#list columns as col>
            ${col.columnName}<#if  col_index != columns?size-1>,</#if>
        </#list>
        ) VALUE (
        <#list columns as col>
            <#noparse>#</#noparse>{${col.attrName}}<#if  col_index != columns?size-1>,</#if>
        </#list>
        )
    </insert>

    <select id="select${className}ById" parameterType="string" resultMap="baseResult">
        <include refid="select${className}" />
    </select>

    <delete id="delete${className}ByIds" parameterType="string">
        UPDATE sys_${classNameLower} SET del_flag = 'Y'
    </delete>

    <update id="update${className}" parameterType="${classNameLower}">
        UPDATE ${tableName}
        <set>
      <#list columns as col>
            <if test="${col.attrName} != null and ${col.attrName} != ''">${col.columnName} = <#noparse>#</#noparse>{${col.attrName}},</if>
      </#list>
        </set>
    </update>
</mapper>