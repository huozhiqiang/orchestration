package com.yss.ms.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.yss.ms.common.biz.BusinessBiz;
import com.yss.ms.common.exception.BaseException;
import com.yss.ms.entity.UserUnit;
import com.yss.ms.mapper.UserUnitMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HuoZhiQiang
 * @Description:
 * @Date: Created in 2018/5/8 17:41
 */
@Service
public class UserUnitBiz extends BusinessBiz<UserUnitMapper,UserUnit> {

    /**
     * 根据单位主键查询服务编制表
     * @Author: HuoZhiQiang
     * @Date: 2018/5/8 17:52
     */
    public List<UserUnit> listByUnitId(int id) {
        Example example = new Example(UserUnit.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted",0);
        criteria.andEqualTo("unitFid",id);
        return bizMapper.selectByExample(example);
    }

    /**
     * 根据主键删除
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     */
    public void deleteById(int id)throws BaseException {
        bizLogicDeleteById(id);
    }

    /**
     * 根据用户表主键查询服务编制表
     * @Author: HuoZhiQiang
     * @Date: 2018/5/8 17:52
     */
    public List<UserUnit> listByUserId(int id) {
        Example example = new Example(UserUnit.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted",0);
        criteria.andEqualTo("userFid",id);
        return bizMapper.selectByExample(example);
    }

    /**
     * 条件查询
     * @Author: HuoZhiQiang
     * @Date: 2018/5/10 13:52
     */
    public Map<String, Object> selectByQuery(Map<String, Object> params) {
        Map<String, Object> map = new HashMap<>(100);
        map.put("page", params.get("page"));
        map.put("limit", params.get("limit"));
        Example example = new Example(UserUnit.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted",0);
        params.remove("page");
        params.remove("limit");
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if(entry.getValue() != null && StringUtil.isNotEmpty(entry.getValue().toString())) {
                criteria.andLike(entry.getKey(), "%" + entry.getValue().toString() + "%");
            }
        }
        int page = Integer.parseInt(map.get("page").toString());
        int limit = Integer.parseInt(map.get("limit").toString());
        Page<Object> result = PageHelper.startPage(page,limit);
        //根据条件和分页查询出公共表的信息
        List<UserUnit> userUnitList = bizMapper.selectByExample(example);
        if(userUnitList == null || userUnitList.size() == 0) {
            return null;
        }
        map.clear();
        //单位表主键
        List<Integer> unitIdList = new ArrayList<>();
        //用户表主键
        List<Integer> userIdList = new ArrayList<>();
        for (UserUnit userUnit : userUnitList) {
            unitIdList.add(userUnit.getUnitFid());
            userIdList.add(userUnit.getUserFid());
        }
        map.put("unitIdList", unitIdList);
        map.put("userIdList", userIdList);
        map.put("userUnitList", userUnitList);
        map.put("total", result.getTotal());
        return map;
    }
}
