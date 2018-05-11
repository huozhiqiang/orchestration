package com.yss.ms.feign;

import com.yss.ms.common.exception.BaseException;
import com.yss.ms.unit.UnitInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author HuoZhiQiang
 * @Description:
 * @Date: Created in 2018/5/7 20:02
 */
@FeignClient(value = "ms-unit",configuration = {})
public interface FeignUnitService {


    /**
     * 删除
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     * */
    @ApiOperation(value="删除")
    @DeleteMapping(path = "unit/id",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void deleteById(@RequestParam("id") int id) throws BaseException;

    /**
     * 审核/复核
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     * */
    @ApiOperation(value="审核/复核")
    @PutMapping(value = "unit/status")
    @ResponseBody
    void updateChecked(@RequestBody List<Integer> list, @RequestParam("status") Integer status)throws BaseException;

    /**
     * 查询(根据主键)
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     * */
    @ApiOperation(value="查询(根据主键)", notes="")
    @GetMapping(path = "api/id", produces = MediaType.APPLICATION_JSON_VALUE)
    UnitInfo findById(@RequestParam("id") int id) throws BaseException;


    /**
     * 查询(根据主键集合)
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     */
    @ApiOperation(value = "查询(根据主键集合)", notes = "")
    @PostMapping(path = "api/listId", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UnitInfo> findByListId(@RequestBody List<Integer> unitIdList) throws BaseException;


    /**
     * 条件查询
     *
     * @author HuoZhiQiang 2018-05-08 15:51:37
     */
    @ApiOperation(value = "条件查询")
    @GetMapping(path = "api", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Map<String, Object> selectByCondition(@RequestParam Map<String, Object> params) throws BaseException;

}
