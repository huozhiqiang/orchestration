package com.yss.ms.service;

import com.yss.ms.biz.UserUnitBiz;
import com.yss.ms.common.exception.BaseException;
import com.yss.ms.entity.UserUnit;
import com.yss.ms.feign.FeignUnitService;
import com.yss.ms.unit.UnitInfo;
import com.yss.ms.vo.UnitVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 单位基本信息表Service
 *
 * @author HuoZhiQiang
 * @since 2018-05-08 17:01:19
 */
@Service
public class UnitService {

    @Autowired
    private UserUnitBiz userUnitBiz;

    @Autowired
    private FeignUnitService feignUnitService;


    /**
     * 删除
     *
     * @author HuoZhiQiang 2018-05-08 17:01:19
     */
    @Transactional(rollbackFor = BaseException.class)
    public void deleteById(int id)throws BaseException {
        //逻辑删除单位表中的数据
        feignUnitService.deleteById(id);
        //根据单位表主键查询出公共表数据
        List<UserUnit> userUnitList = userUnitBiz.listByUnitId(id);
        if (userUnitList != null && userUnitList.size() > 0) {
            for (UserUnit userUnit : userUnitList) {
                //逻辑删除公共表中的数据
                userUnitBiz.deleteById(userUnit.getId());
            }
        }
    }

    /**
     * 审核/复核
     *
     * @author HuoZhiQiang 2018-05-08 17:01:19
     */
    @Transactional(rollbackFor = BaseException.class)
    public void updateChecked(List<Integer> list, Integer status)throws BaseException{
        //修改单位表中的审核状态
        feignUnitService.updateChecked(list, status);
        for (Integer id : list) {
            //根据单位表主键查询出公共表数据
            List<UserUnit> userUnitList = userUnitBiz.listByUnitId(id);
            if (userUnitList != null && userUnitList.size() > 0) {
                for (UserUnit userUnit : userUnitList) {
                    //修改公共表中代表单位审核状态的字段
                    userUnit.setUnitCheckStates(status);
                    userUnitBiz.bizUpdateSelectiveById(userUnit);
                }
            }
        }
    }

    /**
     * 根据主键查询
     *
     * @author HuoZhiQiang 2018-05-08 17:01:19
     */
    public UnitVO findById(Integer id)throws BaseException {
        //根据单位主键跨服务查询单位信息
        UnitInfo unitInfo = feignUnitService.findById(id);
        UnitVO unitVO = new UnitVO();
        BeanUtils.copyProperties(unitInfo,unitVO);
        //根据单位表主键去公共表中查询出该单位下所有的员工
        List<UserUnit> userUnitList = userUnitBiz.listByUnitId(id);
        unitVO.setHeadCount(userUnitList == null ? 0 : userUnitList.size());
        return unitVO;
    }

    /**
     * 条件查询
     * @Author: HuoZhiQiang
     * @Date: 2018/5/10 15:22
     */
    public Map<String,Object> selectByQuery(Map<String, Object> params) {
        //先去单位服务进行分页和条件查询
        Map<String, Object> map = feignUnitService.selectByCondition(params);
        if( map == null ){
            return null;
        }
        //分页和条件查询筛选后的单位信息
        List<Map<String,Object>> infoList = (List<Map<String,Object>>) map.get("infoList");
        List<UnitVO> voList = new ArrayList<>();
        for (Map infoMap : infoList) {
            UnitVO unitVO = new UnitVO();
            unitVO.setId(Integer.valueOf(infoMap.get("id").toString()));
            unitVO.setUnitName(infoMap.get("unitName").toString());
            unitVO.setUnitId(infoMap.get("unitId").toString());
            unitVO.setUnitAddress(infoMap.get("unitAddress").toString());
            unitVO.setPropertyCode(Integer.valueOf(infoMap.get("propertyCode").toString()));
            unitVO.setCheckStates(Integer.valueOf(infoMap.get("checkStates").toString()));
            unitVO.setCorporateRep(infoMap.get("corporateRep").toString());
            //根据单位表主键去公共表中查询出该单位下所有的员工
            List<UserUnit> userUnitList = userUnitBiz.listByUnitId(unitVO.getId());
            //set每个单位的员工人数
            unitVO.setHeadCount(userUnitList == null ? 0 : userUnitList.size());
            voList.add(unitVO);
        }
        map.remove("infoList");
        map.put("voList", voList);
        return map;
    }
}