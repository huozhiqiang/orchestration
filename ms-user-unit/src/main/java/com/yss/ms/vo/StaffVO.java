package com.yss.ms.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author HuoZhiQiang
 * @Description:
 * @Date: Created in 2018/5/9 20:28
 */
@ApiModel(value="员工表参数", description = "员工表参数描述")
public class StaffVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 公共表主键id
     */
    @ApiModelProperty(value="公共表主键id", required = true)
    private Integer id;


    /**
     * 用户表主键
     */
    @ApiModelProperty(value="用户表主键", required = true)
    private Integer userFid;


    /**
     * 单位表主键
     */
    @ApiModelProperty(value="单位表主键", required = true)
    private Integer unitFid;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value="用户姓名", required = true)
    private String userName;


    /**
     * 性别 (0-女|1-男)
     */
    @ApiModelProperty(value="性别 (0-女|1-男)", required = true)
    private Integer sex;


    /**
     * 身份证号
     */
    @ApiModelProperty(value="身份证号", required = true)
    private String idNo;


    /**
     * 年龄
     */
    @ApiModelProperty(value="年龄", required = true)
    private Integer age;


    /**
     * 家庭住址
     */
    @ApiModelProperty(value="家庭住址", required = true)
    private String address;


    /**
     * 联系电话
     */
    @ApiModelProperty(value="联系电话", required = true)
    private String phone;


    /**
     * 单位名称
     */
    @Column(name = "funit_name")
    @ApiModelProperty(value="单位名称", required = true)
    private String unitName;


    /**
     * 单位编码
     */
    @Column(name = "funit_id")
    @ApiModelProperty(value="单位编码", required = true)
    private String unitId;


    /**
     * 单位地址
     */
    @Column(name = "funit_address")
    @ApiModelProperty(value="单位地址", required = true)
    private String unitAddress;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    public Integer getUserFid() {
        return userFid;
    }

    public void setUserFid(Integer userFid) {
        this.userFid = userFid;
    }

    public Integer getUnitFid() {
        return unitFid;
    }

    public void setUnitFid(Integer unitFid) {
        this.unitFid = unitFid;
    }
}
