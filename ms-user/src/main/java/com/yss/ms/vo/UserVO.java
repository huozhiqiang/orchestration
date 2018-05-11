package com.yss.ms.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


/**
 * 用户表
 *
 * @author HuoZhiQiang
 * @since 2018-05-08 14:39:45
 */
@ApiModel(value="用户表参数", description = "用户表参数描述")
public class UserVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
  	/**
     * 主键id
     */
    @Id
    @ApiModelProperty(value="主键id", required = true)
    private Integer id;
	
	
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
     * 创建人ID
     */
    @ApiModelProperty(value="创建人ID", required = true)
    private Integer creatorId;
	
	
    /**
     * 创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="创建时间", required = true)
    private Date createTime;
	
	
    /**
     * 最后编辑人ID
     */
    @ApiModelProperty(value="最后编辑人ID", required = true)
    private Integer lastEditorId;
	
	
    /**
     * 最后编辑时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="最后编辑时间", required = true)
    private Date lastEditTime;
	
	
    /**
     * 删除人ID
     */
    @ApiModelProperty(value="删除人ID", required = true)
    private Integer deletorId;
	
	
    /**
     * 删除时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="删除时间", required = true)
    private Date deleteTime;
	
	
    /**
     * 是否逻辑删除  |0未删除|1已删除
     */
    @ApiModelProperty(value="是否逻辑删除  |0未删除|1已删除", required = true)
    private Integer deleted;
	
	
    /**
     * 审核状态 |0待复核|1已复核|2已审核
     */
    @ApiModelProperty(value="审核状态 |0待复核|1已复核|2已审核", required = true)
    private Integer checkStates;
	
	
    /**
     * 经办人id
     */
    @ApiModelProperty(value="经办人id", required = true)
    private Integer checkerId;
	
	
    /**
     * 经办时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="经办时间", required = true)
    private Date checkerTime;
	


	/**
	 * 设置：主键id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取：主键id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置：用户姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 获取：用户姓名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置：性别 (0-女|1-男)
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * 获取：性别 (0-女|1-男)
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * 设置：身份证号
	 */
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	/**
	 * 获取：身份证号
	 */
	public String getIdNo() {
		return idNo;
	}

	/**
	 * 设置：年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * 获取：年龄
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * 设置：家庭住址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取：家庭住址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置：联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取：联系电话
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置：创建人ID
	 */
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	/**
	 * 获取：创建人ID
	 */
	public Integer getCreatorId() {
		return creatorId;
	}

	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置：最后编辑人ID
	 */
	public void setLastEditorId(Integer lastEditorId) {
		this.lastEditorId = lastEditorId;
	}

	/**
	 * 获取：最后编辑人ID
	 */
	public Integer getLastEditorId() {
		return lastEditorId;
	}

	/**
	 * 设置：最后编辑时间
	 */
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	/**
	 * 获取：最后编辑时间
	 */
	public Date getLastEditTime() {
		return lastEditTime;
	}

	/**
	 * 设置：删除人ID
	 */
	public void setDeletorId(Integer deletorId) {
		this.deletorId = deletorId;
	}

	/**
	 * 获取：删除人ID
	 */
	public Integer getDeletorId() {
		return deletorId;
	}

	/**
	 * 设置：删除时间
	 */
	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	/**
	 * 获取：删除时间
	 */
	public Date getDeleteTime() {
		return deleteTime;
	}

	/**
	 * 设置：是否逻辑删除  |0未删除|1已删除
	 */
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	/**
	 * 获取：是否逻辑删除  |0未删除|1已删除
	 */
	public Integer getDeleted() {
		return deleted;
	}

	/**
	 * 设置：审核状态 |0待复核|1已复核|2已审核
	 */
	public void setCheckStates(Integer checkStates) {
		this.checkStates = checkStates;
	}

	/**
	 * 获取：审核状态 |0待复核|1已复核|2已审核
	 */
	public Integer getCheckStates() {
		return checkStates;
	}

	/**
	 * 设置：经办人id
	 */
	public void setCheckerId(Integer checkerId) {
		this.checkerId = checkerId;
	}

	/**
	 * 获取：经办人id
	 */
	public Integer getCheckerId() {
		return checkerId;
	}

	/**
	 * 设置：经办时间
	 */
	public void setCheckerTime(Date checkerTime) {
		this.checkerTime = checkerTime;
	}

	/**
	 * 获取：经办时间
	 */
	public Date getCheckerTime() {
		return checkerTime;
	}


}
