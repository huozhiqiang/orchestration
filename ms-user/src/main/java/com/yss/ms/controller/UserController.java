package com.yss.ms.controller;

import com.yss.ms.biz.UserBiz;
import com.yss.ms.common.exception.BaseException;
import com.yss.ms.common.msg.ObjectRestResponse;
import com.yss.ms.common.msg.TableResultResponse;
import com.yss.ms.entity.User;
import com.yss.ms.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 用户表
 *
 * @author HuoZhiQiang
 * @since 2018-05-08 14:39:45
 */

@RestController
@RequestMapping("user")
@Api(description="用户表接口")
@Slf4j
public class UserController {

    @Autowired
    private UserBiz userBiz;

    /**
     * 新增
     *
     * @author HuoZhiQiang 2018-05-08 14:39:45
     * */
    @ApiOperation(value="新增")
    @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public ObjectRestResponse<User> add(@RequestBody User user) throws BaseException{
        userBiz.add(user);
        return new ObjectRestResponse<User>().rel(true);
    }


    /**
     * 删除
     *
     * @author HuoZhiQiang 2018-05-08 14:39:45
     * */
    @ApiOperation(value="删除")
    @DeleteMapping(path = "/id",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ObjectRestResponse<User> deleteById(@RequestParam int id) throws BaseException {
        userBiz.deleteById(id);
        return new ObjectRestResponse<User>().rel(true);
    }

    /**
     * 更新
     *
     * @author HuoZhiQiang 2018-05-08 14:39:45
     * */
    @ApiOperation(value="更新")
    @PutMapping(value = "")
    public ObjectRestResponse<User> update(@RequestBody User user) throws BaseException{
        userBiz.update(user);
        return new ObjectRestResponse<User>().rel(true);
    }

    /**
     * 审核/复核
     *
     * @author HuoZhiQiang 2018-05-08 14:39:45
     * */
    @ApiOperation(value="审核/复核")
    @PutMapping(value = "/status")
    @ResponseBody
    public ObjectRestResponse<User> updateChecked(@RequestBody List<Integer> list, @RequestParam Integer status)throws BaseException{
        userBiz.updateCheck(list, status);
        return new ObjectRestResponse<User>().rel(true);
    }


    /**
     * 查询(根据主键)
     *
     * @author HuoZhiQiang 2018-05-08 14:39:45
     * */
    @ApiOperation(value="查询(根据主键)", notes="")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectRestResponse<UserVO> findById(@PathVariable int id) throws BaseException {
        return new ObjectRestResponse<UserVO>().rel(true).data(userBiz.findById(id));
    }

    /**
     * 条件查询
     *
     * @author HuoZhiQiang 2018-05-08 15:51:37
     * */
    @ApiOperation(value = "条件查询")
    @GetMapping(path = "",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TableResultResponse<UserVO> selectByCondition(@RequestParam Map<String, Object> params) throws BaseException{
        Map<String, Object> map = userBiz.selectByQuery(params);
        if(map == null){
            return new TableResultResponse(0, null);
        }
        return new TableResultResponse(Long.parseLong(map.get("total").toString()), (List<UserVO>)map.get("voList"));
    }


}