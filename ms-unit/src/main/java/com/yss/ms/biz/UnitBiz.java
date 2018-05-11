package com.yss.ms.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.yss.ms.common.biz.BusinessBiz;
import com.yss.ms.common.exception.BaseException;
import com.yss.ms.entity.Unit;
import com.yss.ms.mapper.UnitMapper;
import com.yss.ms.unit.UnitInfo;
import com.yss.ms.vo.UnitVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 单位基本信息表Biz
 *
 * @author HuoZhiQiang
 * @since 2018-05-08 13:27:44
 */
@Service
public class UnitBiz extends BusinessBiz<UnitMapper,Unit> {

    /**
     * 新增
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     */
    public void add(Unit unit)throws BaseException {
        bizInsertSelective(unit);
    }

    /**
     * 删除
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     */
    public void deleteById(int id)throws BaseException {
        bizLogicDeleteById(id);
    }

    /**
     * 更新
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     */
    public void update(Unit unit)throws BaseException{
        bizUpdateSelectiveById(unit);
    }

    /**
     * 审核/复核
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     */
    public void updateCheck(List<Integer> list, Integer status)throws BaseException{
        updateChecked(list,status);
    }

    /**
     * 根据主键查询
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     */
    public UnitVO findById(Integer id)throws BaseException {
        Unit unit = bizSelectById(id);
        UnitVO unitVO = new UnitVO();
        BeanUtils.copyProperties(unit,unitVO);
        return unitVO;
    }

    /**
     * 条件查询
     *
     * @author HuoZhiQiang 2018-05-08 15:51:37
     */
    public Map<String,Object> selectByQuery(Map<String, Object> params) {
        Map<String, Object> map = new HashMap<>(100);
        map.put("page", params.get("page"));
        map.put("limit", params.get("limit"));
        Example example = new Example(Unit.class);
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
        List<Unit> list = bizMapper.selectByExample(example);
        if(list == null || list.size() == 0) {
            return null;
        }
        List<UnitVO> voList = new ArrayList<>();
        for (Unit unit : list){
            UnitVO vo = new UnitVO();
            BeanUtils.copyProperties(unit, vo);
            voList.add(vo);
        }
        map.clear();
        map.put("voList", voList);
        map.put("total", result.getTotal());
        return map;
    }

    /**
     * 根据主键查询返回 info 对象
     * @Author: HuoZhiQiang
     * @Date: 2018/5/8 20:01
     */
    public UnitInfo infoById(int id) {
        Unit unit = bizSelectById(id);
        UnitInfo unitInfo = new UnitInfo();
        BeanUtils.copyProperties(unit,unitInfo);
        return unitInfo;
    }

    /**
     * 根据主键集合查询
     *
     * @Author: HuoZhiQiang
     * @Date: 2018/5/10 14:24
     */
    public List<UnitInfo> infoByListId(List<Integer> idList) {
        List<UnitInfo> infoList = new ArrayList<>();
        for (Integer id : idList) {
            UnitInfo unitInfo = infoById(id);
            infoList.add(unitInfo);
        }
        return infoList;
    }

    /**
     * 把VO集合转换成Info集合
     * @Author: HuoZhiQiang
     * @Date: 2018/5/10 15:34
     */
    public List<UnitInfo> listInfoByListVo(List<UnitVO> voList) {

        List<UnitInfo> infoList = new ArrayList<>();
        for (UnitVO unitVO : voList) {
            UnitInfo unitInfo = new UnitInfo();
            BeanUtils.copyProperties(unitVO, unitInfo);
            infoList.add(unitInfo);
        }
        return infoList;
    }
}