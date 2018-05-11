package com.yss.ms.mapper;

import org.springframework.stereotype.Component;
import com.yss.ms.common.mapper.BizMapper;
import com.yss.ms.entity.UserUnit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户与单位编制服务
 *
 * @author HuoZhiQiang
 * @since 2018-05-08 17:42:18
 */
@Component
public interface UserUnitMapper extends BizMapper<UserUnit> {

}
