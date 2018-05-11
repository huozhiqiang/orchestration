package com.yss.ms.service;

import com.yss.ms.biz.UserUnitBiz;
import com.yss.ms.common.exception.BaseException;
import com.yss.ms.entity.UserUnit;
import com.yss.ms.feign.FeignUnitService;
import com.yss.ms.feign.FeignUserService;
import com.yss.ms.unit.UnitInfo;
import com.yss.ms.user.UserInfo;
import com.yss.ms.vo.StaffVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author HuoZhiQiang
 * @Description:
 * @Date: Created in 2018/5/9 17:28
 */
@Service
public class StaffService {

    @Autowired
    private FeignUnitService feignUnitService;

    @Autowired
    private FeignUserService feignUserService;

    @Autowired
    private UserUnitBiz userUnitBiz;

    /**
     * 新增职工
     * @Author: HuoZhiQiang
     * @Date: 2018/5/9 20:25
     */
    @Transactional(rollbackFor = BaseException.class)
    public void add(Integer unitId, Integer userId) {
        UnitInfo unitInfo = feignUnitService.findById(unitId);
        UserInfo userInfo = feignUserService.findById(userId);
        UserUnit userUnit = new UserUnit();
        userUnit.setUserFid(userId);
        userUnit.setUnitFid(unitId);
        userUnit.setUserSex(userInfo.getSex());
        userUnit.setUnitPropertyCode(unitInfo.getPropertyCode());
        userUnit.setUnitCheckStates(unitInfo.getCheckStates());
        userUnit.setUserCheckStates(userInfo.getCheckStates());
        userUnitBiz.bizInsertSelective(userUnit);
    }

    /**
     * 删除
     * @Author: HuoZhiQiang
     * @Date: 2018/5/9 20:26
     */
    @Transactional(rollbackFor = BaseException.class)
    public void deleteById(int id) {
        userUnitBiz.bizLogicDeleteById(id);
    }

    /**
     * 更新
     * @Author: HuoZhiQiang
     * @Date: 2018/5/9 20:35
     */
    @Transactional(rollbackFor = BaseException.class)
    public void update(StaffVO staffVO) {
        //若该员工换一个分公司，则修改公共表中存的单位信息
        UserUnit userUnit = new UserUnit();
        userUnit.setId(staffVO.getId());
        userUnit.setUnitFid(staffVO.getUnitFid());
        //根据单位表主键跨服务查询单位表信息
        UnitInfo unitInfo = feignUnitService.findById(staffVO.getUnitFid());
        userUnit.setUnitPropertyCode(unitInfo.getPropertyCode());
        userUnit.setUnitCheckStates(unitInfo.getCheckStates());
        userUnit.setUserSex(staffVO.getSex());
        userUnitBiz.bizUpdateSelectiveById(userUnit);
        //员工修改基本信息
        UserInfo userInfo = new UserInfo();
        userInfo.setId(staffVO.getUserFid());
        userInfo.setUserName(staffVO.getUserName());
        userInfo.setSex(staffVO.getSex());
        userInfo.setIdNo(staffVO.getIdNo());
        userInfo.setAge(staffVO.getAge());
        userInfo.setAddress(staffVO.getAddress());
        userInfo.setPhone(staffVO.getPhone());
        feignUserService.updateInfo(userInfo);
    }

    /**
     * 根据主键查询
     *
     * @author HuoZhiQiang 2018-05-08 14:39:45
     */
    public StaffVO findById(int id) {
        UserUnit userUnit = userUnitBiz.bizSelectById(id);
        //根据公共表中用户的主键查询出用户信息
        UserInfo userInfo = feignUserService.findById(userUnit.getUserFid());
        //根据公共表中单位的主键查询出单位信息
        UnitInfo unitInfo = feignUnitService.findById(userUnit.getUnitFid());
        StaffVO staffVO = new StaffVO();
        staffVO.setId(id);
        staffVO.setUserFid(userInfo.getId());
        staffVO.setUnitFid(userInfo.getId());
        staffVO.setUserName(userInfo.getUserName());
        staffVO.setSex(userInfo.getSex());
        staffVO.setIdNo(userInfo.getIdNo());
        staffVO.setAge(userInfo.getAge());
        staffVO.setAddress(userInfo.getAddress());
        staffVO.setPhone(userInfo.getPhone());

        staffVO.setUnitName(unitInfo.getUnitName());
        staffVO.setUnitId(unitInfo.getUnitId());
        staffVO.setUnitAddress(unitInfo.getUnitAddress());
        return staffVO;
    }

    /**
     * 条件查询
     * @Author: HuoZhiQiang
     * @Date: 2018/5/10 13:49
     */
    public Map<String,Object> selectByQuery(Map<String, Object> params) {
        Map<String,Object> map = userUnitBiz.selectByQuery(params);
        if (map == null) {
            return null;
        }
        //分页和条件筛选后的公共表信息
        List<UserUnit> userUnitList = (List<UserUnit>) map.get("userUnitList");
        List<Integer> unitIdList = (List<Integer>) map.get("unitIdList");
        List<Integer> userIdList = (List<Integer>) map.get("userIdList");
        List<StaffVO> voList = new ArrayList<>();
        List<UnitInfo> unitInfoList = new ArrayList<>();
        List<UserInfo> userInfoList = new ArrayList<>();
        if(unitIdList != null && unitIdList.size() > 0){
            unitInfoList = feignUnitService.findByListId(unitIdList);
        }
        if(userIdList != null && userIdList.size() > 0){
            userInfoList = feignUserService.findByListId(userIdList);
        }
        for (int i = 0; i < userUnitList.size(); i++) {
            StaffVO staffVO = new StaffVO();
            //从公共表中取出员工页面需要的信息
            staffVO.setId(userUnitList.get(i).getId());
            staffVO.setUserFid(userUnitList.get(i).getUserFid());
            staffVO.setUnitFid(userUnitList.get(i).getUnitFid());
            //从用户表中取出员工页面需要的信息
            staffVO.setUserName(userInfoList.get(i).getUserName());
            staffVO.setSex(userInfoList.get(i).getSex());
            staffVO.setIdNo(userInfoList.get(i).getIdNo());
            staffVO.setAge(userInfoList.get(i).getAge());
            staffVO.setAddress(userInfoList.get(i).getAddress());
            staffVO.setPhone(userInfoList.get(i).getPhone());
            //从单位表中取出员工页面需要的信息
            staffVO.setUnitName(unitInfoList.get(i).getUnitName());
            staffVO.setUnitId(unitInfoList.get(i).getUnitId());
            staffVO.setUnitAddress(unitInfoList.get(i).getUnitAddress());
            voList.add(staffVO);
        }
        map.put("voList", voList);
        return map;
    }
}
