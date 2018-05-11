package com.yss.ms.controller;

import com.yss.ms.biz.UnitBiz;
import com.yss.ms.common.exception.BaseException;
import com.yss.ms.common.msg.ObjectRestResponse;
import com.yss.ms.common.msg.TableResultResponse;
import com.yss.ms.entity.Unit;
import com.yss.ms.vo.UnitVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 单位基本信息表
 *
 * @author HuoZhiQiang
 * @since 2018-05-08 13:27:44
 */

@RestController
@RequestMapping("unit")
@Api(description="单位基本信息表接口")
@Slf4j
public class UnitController {

    @Autowired
    private UnitBiz unitBiz;

    /**
     * 新增
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     * */
    @ApiOperation(value="新增")
    @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public ObjectRestResponse<Unit> add(@RequestBody Unit unit) throws BaseException{
        unitBiz.add(unit);
        return new ObjectRestResponse<Unit>().rel(true);
    }


    /**
     * 删除
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     * */
    @ApiOperation(value="删除")
    @DeleteMapping(path = "id",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ObjectRestResponse<Unit> deleteById(@RequestParam int id) throws BaseException {
        unitBiz.deleteById(id);
        return new ObjectRestResponse<Unit>().rel(true);
    }

    /**
     * 更新
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     * */
    @ApiOperation(value="更新")
    @PutMapping(value = "")
    public ObjectRestResponse<Unit> update(@RequestBody @Validated Unit unit) throws BaseException{
        unitBiz.update(unit);
        return new ObjectRestResponse<Unit>().rel(true);
    }

    /**
     * 审核/复核
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     * */
    @ApiOperation(value="审核/复核")
    @PutMapping(value = "/status")
    @ResponseBody
    public ObjectRestResponse<Unit> updateChecked(@RequestBody List<Integer> list,@RequestParam Integer status)throws BaseException{
        unitBiz.updateCheck(list, status);
        return new ObjectRestResponse<Unit>().rel(true);
    }


    /**
     * 查询(根据主键)
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     * */
    @ApiOperation(value="查询(根据主键)", notes="")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectRestResponse<UnitVO> findById(@PathVariable int id) throws BaseException {
        return new ObjectRestResponse<UnitVO>().rel(true).data(unitBiz.findById(id));
    }

    /**
     * 条件查询
     *
     * @author HuoZhiQiang 2018-05-08 15:51:37
     * */
    @ApiOperation(value = "条件查询")
    @GetMapping(path = "",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TableResultResponse<UnitVO> selectByCondition(@RequestParam Map<String, Object> params) throws BaseException{
        Map<String, Object> map = unitBiz.selectByQuery(params);
        if(map == null){
            return new TableResultResponse(0, null);
        }
        return new TableResultResponse(Long.parseLong(map.get("total").toString()), (List<UnitVO>)map.get("voList"));
    }

}