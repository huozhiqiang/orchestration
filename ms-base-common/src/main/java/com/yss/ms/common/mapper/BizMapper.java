package com.yss.ms.common.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

/**
 * TODO
 *
 * @author liugang
 * @since 2017-12-25
 */
public interface BizMapper<T> extends Mapper<T> {

    /**
     * 插入(用于插入后返回fid,因源码写死的是id字段,业务服务调用需要重写该方法)
     * @param record
     * @return int
     * */
    @Override
    @Options(useGeneratedKeys = true, keyProperty = "fid")
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    int insertSelective(T record);
}
