package com.yss.ms.controller;

import com.yss.ms.common.exception.BaseException;
import com.yss.ms.common.msg.ObjectRestResponse;
import com.yss.ms.common.msg.TableResultResponse;
import com.yss.ms.entity.UserUnit;
import com.yss.ms.service.StaffService;
import com.yss.ms.vo.StaffVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author HuoZhiQiang
 * @Description:
 * @Date: Created in 2018/5/9 15:19
 */
@RestController
@RequestMapping("staff")
@Api(description="单位人员表接口")
@Slf4j
public class StaffController {

    @Autowired
    private StaffService staffService;

    /**
     * 新增
     *
     * @author HuoZhiQiang 2018-05-08 14:39:45
     */
    @ApiOperation(value = "新增")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ObjectRestResponse<UserUnit> add(@RequestParam Integer unitId, @RequestParam Integer userId) throws BaseException {
        staffService.add(unitId,userId);
        return new ObjectRestResponse<UserUnit>().rel(true);
    }

    /**
     * 删除
     *
     * @author HuoZhiQiang 2018-05-08 14:39:45
     * */
    @ApiOperation(value="删除")
    @DeleteMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ObjectRestResponse deleteById(@PathVariable int id) throws BaseException {
        staffService.deleteById(id);
        return new ObjectRestResponse().rel(true);
    }

    /**
     * 更新
     *
     * @author HuoZhiQiang 2018-05-08 14:39:45
     * */
    @ApiOperation(value="更新")
    @PutMapping(value = "")
    public ObjectRestResponse update(@RequestBody StaffVO staffVO) throws BaseException{
        staffService.update(staffVO);
        return new ObjectRestResponse().rel(true);
    }

    /**
     * 查询(根据主键)
     *
     * @author HuoZhiQiang 2018-05-08 14:39:45
     * */
    @ApiOperation(value="查询(根据主键)", notes="")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectRestResponse findById(@PathVariable int id) throws BaseException {
        return new ObjectRestResponse().rel(true).data(staffService.findById(id));
    }

    /**
     * 条件查询
     *
     * @author HuoZhiQiang 2018-05-08 15:51:37
     * */
    @ApiOperation(value = "条件查询")
    @GetMapping(path = "",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TableResultResponse selectByCondition(@RequestParam Map<String, Object> params) throws BaseException{
        Map<String, Object> map = staffService.selectByQuery(params);
        if(map == null){
            return new TableResultResponse(0, null);
        }
        return new TableResultResponse(Long.parseLong(map.get("total").toString()), (List<StaffVO>)map.get("voList"));
    }

}
