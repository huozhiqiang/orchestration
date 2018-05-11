package com.yss.ms.common.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yss.ms.common.exception.BaseException;
import com.yss.ms.common.mapper.BizMapper;
import com.yss.ms.common.msg.TableResultResponse;
import com.yss.ms.common.util.EntityUtils;
import com.yss.ms.common.util.Query;
import com.yss.ms.common.util.ReflectionUtils;
import com.yss.ms.common.util.UniqueVerifiableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import tk.mybatis.mapper.entity.Example;
import java.lang.reflect.Field;

import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * TODO
 *
 * @author liugang
 * @since 2017-12-25
 */
public abstract class BusinessBiz<B extends BizMapper<T>, T> {

    @Autowired
    protected B bizMapper;

    public void setMapper(B bizMapper) {
        this.bizMapper = bizMapper;
    }

    public T selectOne(T entity) {
        return bizMapper.selectOne(entity);
    }


    public T selectById(Object id) {
        return bizMapper.selectByPrimaryKey(id);
    }


    public List<T> selectList(T entity) {
        return bizMapper.select(entity);
    }


    public List<T> selectListAll() {
        return bizMapper.selectAll();
    }


    public Long selectCount(T entity) {
        return new Long(bizMapper.selectCount(entity));
    }

    /**
     * 业务查询方法(只查删除状态为0的)
     *
     * @author liugang 2017-12-25 13:54
     * */
    public List<T> bizSelectListAll() throws BaseException{
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("fdeleted",0);
        return bizMapper.selectByExample(example);
    }

    /**
     * 业务根据主键查询方法(只查删除状态为0的)
     *
     * @author liugang 2017-12-25 13:55
     * */
    public T bizSelectById(Object id) throws BaseException{
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        T entity = bizMapper.selectByPrimaryKey(id);
        if (entity == null) {
           throw new BaseException("该对象不存在");
        }
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();
        if(ReflectionUtils.hasField(entity, "fid")){
            criteria.andEqualTo("fid",id);
        }else if(ReflectionUtils.hasField(entity, "id")){
            criteria.andEqualTo("id",id);
        }
        if(ReflectionUtils.hasField(entity, "fdeleted")){
            criteria.andEqualTo("fdeleted",0);
        }else if(ReflectionUtils.hasField(entity, "deleted")){
            criteria.andEqualTo("deleted","0");
        }

        List<T> list = bizMapper.selectByExample(example);
        if(list.size()!=0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 根据主键id查询方法(只查删除状态为0的)
     * 二期开发 实体类中去掉f
     * @Author: HuoZhiQiang
     * @Date: 2018/3/19 11:04
     */
    public T bizQueryById(Integer id) throws BaseException{
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted",0);
        criteria.andEqualTo("id",id);
        List<T> list = bizMapper.selectByExample(example);
        if(list.size()!=0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 业务插入方法(null的属性也会保存，不会使用数据库默认值)
     *
     * @author liugang 2017-12-23 16:22
     * */
    public void bizInsert(T entity) throws BaseException{
        validateUnique(entity,false);
        EntityUtils.setBizCreatAndUpdatInfo(entity);
        bizMapper.insert(entity);
    }

    /**
     * 业务插入方法(null的属性不会保存，会使用数据库默认值)
     *
     * @author liugang 2017-12-23 16:23
     * */
    public void bizInsertSelective(T entity) throws BaseException{
        validateUnique(entity,false);
        EntityUtils.setBizCreateInfo(entity);
        bizMapper.insertSelective(entity);
    }

    /**
     * 业务更新方法(更新实体全部字段，null值会被更新)
     *
     * @author liugang 2017-12-23 16:26
     * */
    public void bizUpdateById(T entity) throws BaseException{
        validateUnique(entity,true);
        EntityUtils.setBizUpdatedInfo(entity);
        bizMapper.updateByPrimaryKey(entity);
    }

    /**
     * 业务更新方法(更新属性不为null的值)
     *
     * @author liugang 2017-12-23 16:27
     * */
    public void bizUpdateSelectiveById(T entity) throws BaseException{
        //因为需求定的不修改业务主键，所以更新就不需要判断业务主键唯一性啦啦啦啦啦
        //validateUnique(entity,true);
        EntityUtils.setBizUpdatedInfo(entity);
        //不更新业务主键
        entity = setNaturalkey(entity);
        bizMapper.updateByPrimaryKeySelective(entity);

    }

    /**
     * 业务逻辑删除(更新属性不为null的值)
     *
     * @author liugang 2017-12-23 16:30
     * */
    public void bizLogicDeleteById(Object id) throws BaseException{
        T entity = bizMapper.selectByPrimaryKey(id);
        if(entity != null){
            if(ReflectionUtils.hasField(entity, "fdeleted")){
                ReflectionUtils.invokeSetter(entity, "fdeleted", 1);
            }else if(ReflectionUtils.hasField(entity, "deleted")){
                ReflectionUtils.invokeSetter(entity, "deleted", 1);
            }
            EntityUtils.setBizLogicDeleteInfo(entity);
            bizMapper.updateByPrimaryKeySelective(entity);
        }
    }

    public TableResultResponse<T> selectByQuery(Query query) throws BaseException{
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("fdeleted","0");
        for (Map.Entry<String, Object> entry : query.entrySet()) {
            criteria.andLike(entry.getKey(), "%" + entry.getValue().toString() + "%");
        }
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<T> list = bizMapper.selectByExample(example);
        return new TableResultResponse<T>(result.getTotal(), list);
    }
    
    /**
     * 保存更新时唯一性约束验证
     * 
     * @author liugang 2017-12-29 17:47
     * */
    private void validateUnique(T entity,boolean isUpdate) throws BaseException{
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        //查询虚拟业务主键是否存在，存在则进行先查后插
        String property = ((UniqueVerifiableVO)entity).fetchUniqueProperty();
        if(property != null){
            String[] uniques = property.split("\\|");
            for(String unique : uniques){
                String[] keys = unique.split("\\&|\\,");
                Example example = new Example(clazz);
                Example.Criteria criteria = example.createCriteria();
                //判断是更新还是插入，是更新则需要在查询的结果集中排除自身的fid
                if(ReflectionUtils.hasField(entity, "fdeleted")){
                    criteria.andEqualTo("fdeleted","0");
                }else if(ReflectionUtils.hasField(entity, "deleted")){
                    criteria.andEqualTo("deleted","0");
                }
                if(isUpdate){
                    if(ReflectionUtils.hasField(entity, "fid")){
                        criteria.andNotEqualTo("fid",ReflectionUtils.getFieldValue(entity,"fid"));
                    }else if(ReflectionUtils.hasField(entity, "id")){
                        criteria.andNotEqualTo("id",ReflectionUtils.getFieldValue(entity,"id"));
                    }

                }
                //添加where条件
                for(String key:keys){
                    criteria.andEqualTo(key,ReflectionUtils.getFieldValue(entity,key));
                }
                List<T> list = bizMapper.selectByExample(example);
                if(list.size()!=0){
                    throw new BaseException("该记录已存在！", HttpStatus.CONFLICT.value());
                }
            }
        }
    }

    /**
     * 将业务主键置为null,禁止修改业务主键
     *
     * @author liugang 2018-02-01 15:57
     * */
    private T setNaturalkey(T entity){
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        //查询虚拟业务主键是否存在，存在则进行先查后插
        String property = ((UniqueVerifiableVO)entity).fetchUniqueProperty();
        String[] fields = {""};
        Object [] value = null;
        if(property != null){
            property = property.replace("|",",");
            fields = property.split(",");
            //老夫猜测最多也就10个业务主键吧.....多了就会空指针~~~~
            value = new Object []{null,null,null,null,null,null,null,null,null,null};
            // 业务主键通通置null,无论分组还是单组
            EntityUtils.setDefaultValues(entity, fields, value);
        }
        return entity;
    }

    /**
     * 审核/复核
     * @Author: HuoZhiQiang
     * @Date: 2018/1/9 10:23
     */
    public void updateChecked(List<Integer> list ,Integer status) {
        for (Integer id : list){
            T entity = bizMapper.selectByPrimaryKey(id);
            if(ReflectionUtils.hasField(entity, "fcheckStates")){
                ReflectionUtils.invokeSetter(entity, "fcheckStates", status);
            }else if(ReflectionUtils.hasField(entity, "checkStates")){
                ReflectionUtils.invokeSetter(entity, "checkStates", status);
            }
            EntityUtils.setBizUpdateCheckedInfo(entity);
            bizMapper.updateByPrimaryKeySelective(entity);
        }
    }

    /**
     * 生成流水号
     * @Author: HuoZhiQiang
     * @Date: 2018/3/9 10:43
     */
    public String createSerialNo(String type){
        String number = "";
        String id = "00000";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
        Random random = new Random();
        String time = sdf.format(new Date());
        Integer randomNo = random.nextInt(100);
        if(randomNo < 10){
            number = "0" + randomNo;
        }else{
            number = randomNo.toString();
        }
        return type + time + id + number;

    }

}
