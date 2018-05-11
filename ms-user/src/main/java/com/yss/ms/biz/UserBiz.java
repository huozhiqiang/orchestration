package com.yss.ms.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.yss.ms.common.biz.BusinessBiz;
import com.yss.ms.common.exception.BaseException;
import com.yss.ms.entity.User;
import com.yss.ms.mapper.UserMapper;
import com.yss.ms.user.UserInfo;
import com.yss.ms.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户表Biz
 *
 * @author HuoZhiQiang
 * @since 2018-05-08 14:39:45
 */
@Service
public class UserBiz extends BusinessBiz<UserMapper,User> {

    /**
     * 新增
     *
     * @author HuoZhiQiang 2018-05-08 14:39:45
     */
    public void add(User user)throws BaseException {
        bizInsertSelective(user);
    }

    /**
     * 删除
     *
     * @author HuoZhiQiang 2018-05-08 14:39:45
     */
    public void deleteById(int id)throws BaseException {
        bizLogicDeleteById(id);
    }

    /**
     * 更新
     *
     * @author HuoZhiQiang 2018-05-08 14:39:45
     */
    public void update(User user)throws BaseException{
        bizUpdateSelectiveById(user);
    }

    /**
     * 审核/复核
     *
     * @author HuoZhiQiang 2018-05-08 14:39:45
     */
    public void updateCheck(List<Integer> list, Integer status)throws BaseException{
        updateChecked(list,status);
    }

    /**
     * 根据主键查询
     *
     * @author HuoZhiQiang 2018-05-08 14:39:45
     */
    public UserVO findById(Integer id)throws BaseException {
        User user = bizSelectById(id);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return userVO;
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
        Example example = new Example(User.class);
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
        List<User> list = bizMapper.selectByExample(example);
        if(list == null || list.size() == 0) {
            return null;
        }
        List<UserVO> voList = new ArrayList<>();
        for (User user : list){
            UserVO vo = new UserVO();
            BeanUtils.copyProperties(user, vo);
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
    public UserInfo infoById(int id)throws BaseException {
        User user = bizSelectById(id);
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user,userInfo);
        return userInfo;
    }

    /**
     * 根据Info修改实体
     * @Author: HuoZhiQiang
     * @Date: 2018/5/10 11:21
     */
    public void updateInfo(UserInfo userInfo)throws BaseException {
        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        update(user);
    }

    /**
     * 根据主键集合查询Info
     * @Author: HuoZhiQiang
     * @Date: 2018/5/10 14:50
     */
    public List<UserInfo> infoByListId(List<Integer> idList) {
        List<UserInfo> infoList = new ArrayList<>();
        for (Integer id : idList) {
            UserInfo userInfo = infoById(id);
            infoList.add(userInfo);
        }
        return infoList;
    }


}