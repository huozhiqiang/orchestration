package com.yss.ms.controller;

import com.yss.ms.common.exception.BaseException;
import com.yss.ms.common.msg.ObjectRestResponse;
import com.yss.ms.common.msg.TableResultResponse;
import com.yss.ms.service.UnitService;
import com.yss.ms.vo.UnitVO;
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
 * 单位基本信息表
 *
 * @author HuoZhiQiang
 * @since 2018-05-08 17:01:19
 */

@RestController
@RequestMapping("unit")
@Api(description="单位基本信息表接口")
@Slf4j
public class UnitController {

    @Autowired
    private UnitService unitService;

     /**
     * 删除
     *
     * @author HuoZhiQiang 2018-05-08 17:01:19
     * */
    @ApiOperation(value="删除")
    @DeleteMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ObjectRestResponse deleteById(@PathVariable int id) throws BaseException {
        unitService.deleteById(id);
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
        unitService.updateChecked(list, status);
        return new ObjectRestResponse().rel(true);
    }

    /**
     * 查询(根据主键)
     *
     * @author HuoZhiQiang 2018-05-08 17:01:19
     * */
    @ApiOperation(value="查询(根据主键)", notes="")
    @GetMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectRestResponse findById(@PathVariable Integer id) throws BaseException {
        return new ObjectRestResponse().rel(true).data(unitService.findById(id));
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
        Map<String, Object> map = unitService.selectByQuery(params);
        if(map == null){
            return new TableResultResponse(0, null);
        }
        return new TableResultResponse(Long.parseLong(map.get("total").toString()), (List<UserVO>)map.get("voList"));
    }

}