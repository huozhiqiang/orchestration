package com.yss.ms.rpc;

import com.yss.ms.biz.UnitBiz;
import com.yss.ms.common.exception.BaseException;
import com.yss.ms.common.msg.ObjectRestResponse;
import com.yss.ms.unit.UnitInfo;
import com.yss.ms.vo.UnitVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author HuoZhiQiang
 * @Description:
 * @Date: Created in 2018/5/7 19:57
 */
@RestController
@RequestMapping("api")
@Slf4j
public class UnitRest {

    @Autowired
    private UnitBiz unitBiz;

    /**
     * 查询(根据主键)
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     * */
    @ApiOperation(value="查询(根据主键)", notes="")
    @GetMapping(path = "/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public UnitInfo findById(@RequestParam int id) throws BaseException {
        return unitBiz.infoById(id);
    }

    /**
     * 查询(根据主键集合)
     *
     * @author HuoZhiQiang 2018-05-08 13:27:44
     * */
    @ApiOperation(value="查询(根据主键集合)", notes="")
    @PostMapping(path = "listId", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UnitInfo> findByListId(@RequestBody List<Integer> idList) throws BaseException {
        return unitBiz.infoByListId(idList);
    }

    /**
     * 条件查询
     *
     * @author HuoZhiQiang 2018-05-08 15:51:37
     * */
    @ApiOperation(value = "条件查询")
    @GetMapping(path = "",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> selectByCondition(@RequestParam Map<String, Object> params) throws BaseException{
        Map<String, Object> map = unitBiz.selectByQuery(params);
        if( map == null ){
            return null;
        }
        List<UnitInfo> infoList = unitBiz.listInfoByListVo((List<UnitVO>)map.get("voList"));
        map.remove("voList");
        map.put("infoList", infoList);
        return map;
    }

}
