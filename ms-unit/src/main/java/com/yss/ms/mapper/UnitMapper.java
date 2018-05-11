package com.yss.ms.mapper;

import com.yss.ms.common.mapper.BizMapper;
import com.yss.ms.entity.Unit;
import org.springframework.stereotype.Component;

/**
 * 单位基本信息表
 *
 * @author HuoZhiQiang
 * @since 2018-05-08 13:27:44
 */
@Component
public interface UnitMapper extends BizMapper<Unit> {

    void test();

}
