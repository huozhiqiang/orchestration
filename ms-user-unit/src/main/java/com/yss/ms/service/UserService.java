package com.yss.ms.service;

import com.yss.ms.biz.UserUnitBiz;
import com.yss.ms.common.exception.BaseException;
import com.yss.ms.entity.UserUnit;
import com.yss.ms.feign.FeignUnitService;
import com.yss.ms.feign.FeignUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户表Service
 *
 * @author HuoZhiQiang
 * @since 2018-05-08 17:01:19
 */
@Service
public class UserService {

    @Autowired
    private UserUnitBiz userUnitBiz;

    @Autowired
    private FeignUserService feignUserService;

    @Autowired
    private FeignUnitService feignUnitService;


    /**
     * 删除
     *
     * @author HuoZhiQiang 2018-05-08 17:01:19
     */
    @Transactional(rollbackFor = BaseException.class)
    public void deleteById(int id)throws BaseException {
        //删除用户数据时要先去公共表中查询
        List<UserUnit> userUnitLsit = userUnitBiz.listByUserId(id);
        if (userUnitLsit != null && userUnitLsit.size() > 0) {
            throw new BaseException("因单位人员表中存在此用户信息，故不能删除~");
        }
        //若公共表中不存在该职工，则删除该用户
        feignUserService.deleteById(id);
    }


    /**
     * 审核/复核
     *
     * @author HuoZhiQiang 2018-05-08 17:01:19
     */
    @Transactional(rollbackFor = BaseException.class)
    public void updateChecked(List<Integer> list, Integer status)throws BaseException{
        //修改用户中的审核状态
        feignUserService.updateChecked(list, status);
        for (Integer id : list) {
            //根据用户表主键查询出公共表数据
            List<UserUnit> userUnitList = userUnitBiz.listByUserId(id);
            if (userUnitList != null && userUnitList.size() > 0) {
                for (UserUnit userUnit : userUnitList) {
                    //修改公共表中代表用户审核状态的字段
                    userUnit.setUserCheckStates(status);
                    userUnitBiz.bizUpdateSelectiveById(userUnit);
                }
            }
        }
    }

}