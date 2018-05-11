package com.yss.ms.rpc;

import com.yss.ms.biz.UserBiz;
import com.yss.ms.common.exception.BaseException;
import com.yss.ms.user.UserInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HuoZhiQiang
 * @Description:
 * @Date: Created in 2018/5/7 19:57
 */
@RestController
@RequestMapping("api")
@Slf4j
public class UserRest {

    @Autowired
    private UserBiz userBiz;

    /**
     * 查询(根据主键)
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     * */
    @ApiOperation(value="查询(根据主键)", notes="")
    @GetMapping(path = "/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserInfo findById(@RequestParam int id) throws BaseException {
        return userBiz.infoById(id);
    }

    /**
     * 更新
     *
     * @author HuoZhiQiang 2018-05-08 14:39:45
     * */
    @ApiOperation(value="更新")
    @PutMapping(value = "")
    public void updateInfo(@RequestBody UserInfo userInfo) throws BaseException{
        userBiz.updateInfo(userInfo);
    }

    /**
     * 查询(根据主键集合)
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     * */
    @ApiOperation(value="查询(根据主键集合)", notes="")
    @PostMapping(path = "/listId", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserInfo> findByListId(@RequestBody List<Integer> idList) throws BaseException {
        return userBiz.infoByListId(idList);
    }


}
