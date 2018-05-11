package com.yss.ms.controller;

import com.yss.ms.common.exception.BaseException;
import com.yss.ms.common.msg.ObjectRestResponse;
import com.yss.ms.common.msg.TableResultResponse;
import com.yss.ms.service.UserService;
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
 * @since 2018-05-08 17:01:19
 */

@RestController
@RequestMapping("user")
@Api(description="用户表接口")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 删除
     *
     * @author HuoZhiQiang 2018-05-08 17:01:19
     * */
    @ApiOperation(value="删除")
    @DeleteMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ObjectRestResponse deleteById(@PathVariable int id) throws BaseException {
        userService.deleteById(id);
        return new ObjectRestResponse().rel(true);
    }


    /**
     * 审核/复核
     *
     * @author HuoZhiQiang 2018-05-08 17:01:19
     * */
    @ApiOperation(value="审核/复核")
    @PutMapping(value = "/status")
    @ResponseBody
    public ObjectRestResponse updateChecked(@RequestBody List<Integer> list, @RequestParam Integer status)throws BaseException{
        userService.updateChecked(list, status);
        return new ObjectRestResponse().rel(true);
    }


}