package com.yss.ms.feign;

import com.yss.ms.common.exception.BaseException;
import com.yss.ms.user.UserInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HuoZhiQiang
 * @Description:
 * @Date: Created in 2018/5/8 15:26
 */
@FeignClient(value = "ms-user")
public interface FeignUserService {

    /**
     * 删除
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     * */
    @ApiOperation(value="删除")
    @DeleteMapping(path = "user/id",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void deleteById(@RequestParam("id") int id) throws BaseException;

    /**
     * 审核/复核
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     * */
    @ApiOperation(value="审核/复核")
    @PutMapping(value = "user/status")
    @ResponseBody
    void updateChecked(@RequestBody List<Integer> list, @RequestParam("status") Integer status)throws BaseException;

    /**
     * 查询(根据主键)
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     * */
    @ApiOperation(value="查询(根据主键)", notes="")
    @GetMapping(path = "api/id", produces = MediaType.APPLICATION_JSON_VALUE)
    UserInfo findById(@RequestParam("id") int id) throws BaseException;

    /**
     * 更新
     *
     * @author HuoZhiQiang 2018-05-08 14:39:45
     */
    @ApiOperation(value = "更新")
    @PutMapping(value = "api")
    void updateInfo(@RequestBody UserInfo userInfo) throws BaseException;

    /**
     * 查询(根据主键集合)
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     */
    @ApiOperation(value = "查询(根据主键集合)", notes = "")
    @PostMapping(path = "api/listId", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserInfo> findByListId(@RequestBody List<Integer> userIdList) throws BaseException;
}

