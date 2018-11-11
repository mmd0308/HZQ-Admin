package com.hzqing.system.vo;

import com.hzqing.system.domain.Dict;
import lombok.Data;

import java.util.List;

/**
 * @author hzqing
 * @date 2018/11/11 09:34
 */
@Data
public class DictVO extends Dict{

    private List<DictVO> children;
}
