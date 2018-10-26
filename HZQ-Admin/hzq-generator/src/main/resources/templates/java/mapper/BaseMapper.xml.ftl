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
        ${col.columnName}
</#list>
        FROM
        ${tableName}
    </sql>

    <select id="selectTableList" resultMap="baseResult">
        <include refid="select${className}"/>
        WHERE
        del_flag = 'N'
    </select>

    <insert id="insert${className}" parameterType="${classNameLower}" >

    </insert>

    <select id="select${className}ById" parameterType="string" resultMap="baseResult">
        <include refid="select${className}" />
        WHERE
    </select>

    <delete id="delete${className}ByIds" parameterType="string">
        UPDATE sys_${classNameLower} SET del_flag = 'Y' WHERE  ${classNameLower}_id IN

    </delete>

    <update id="update${className}" parameterType="${classNameLower}">
        UPDATE sys_${classNameLower}
    </update>
</mapper>