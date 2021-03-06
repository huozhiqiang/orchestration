package com.yss.ms.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


/**
 * 用户与单位编制服务
 *
 * @author HuoZhiQiang
 * @since 2018-05-10 19:42:00
 */
@ApiModel(value="用户与单位编制服务参数", description = "用户与单位编制服务参数描述")
public class UserUnitVO implements Serializable{
	private static final long serialVersionUID = 1L;


	/**
	 * 主键id
	 */
	@Id
	@ApiModelProperty(value="主键id", required = true)
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
	 * 用户性别
	 */
	@ApiModelProperty(value="用户性别", required = true)
	private Integer userSex;


	/**
	 * 单位类型|100-党政机关|210-参照公务员法管理的事业单位|221-公益一类事业单位|222-公益二类事业单位|230-从事生产经营活动的事业单位|300-社会团体|900-其他
	 */
	@ApiModelProperty(value="单位类型|100-党政机关|210-参照公务员法管理的事业单位|221-公益一类事业单位|222-公益二类事业单位|230-从事生产经营活动的事业单位|300-社会团体|900-其他", required = true)
	private Integer unitPropertyCode;


	/**
	 * 审核状态 |0待复核|1已复核|2已审核
	 */
	@ApiModelProperty(value="审核状态 |0待复核|1已复核|2已审核", required = true)
	private Integer userCheckStates;


	/**
	 * 审核状态 |0待复核|1已复核|2已审核
	 */
	@ApiModelProperty(value="审核状态 |0待复核|1已复核|2已审核", required = true)
	private Integer unitCheckStates;


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
	 * 设置：用户表主键
	 */
	public void setUserFid(Integer userFid) {
		this.userFid = userFid;
	}

	/**
	 * 获取：用户表主键
	 */
	public Integer getUserFid() {
		return userFid;
	}

	/**
	 * 设置：单位表主键
	 */
	public void setUnitFid(Integer unitFid) {
		this.unitFid = unitFid;
	}

	/**
	 * 获取：单位表主键
	 */
	public Integer getUnitFid() {
		return unitFid;
	}

	/**
	 * 设置：用户性别
	 */
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	/**
	 * 获取：用户性别
	 */
	public Integer getUserSex() {
		return userSex;
	}

	/**
	 * 设置：单位类型|100-党政机关|210-参照公务员法管理的事业单位|221-公益一类事业单位|222-公益二类事业单位|230-从事生产经营活动的事业单位|300-社会团体|900-其他
	 */
	public void setUnitPropertyCode(Integer unitPropertyCode) {
		this.unitPropertyCode = unitPropertyCode;
	}

	/**
	 * 获取：单位类型|100-党政机关|210-参照公务员法管理的事业单位|221-公益一类事业单位|222-公益二类事业单位|230-从事生产经营活动的事业单位|300-社会团体|900-其他
	 */
	public Integer getUnitPropertyCode() {
		return unitPropertyCode;
	}

	/**
	 * 设置：审核状态 |0待复核|1已复核|2已审核
	 */
	public void setUserCheckStates(Integer userCheckStates) {
		this.userCheckStates = userCheckStates;
	}

	/**
	 * 获取：审核状态 |0待复核|1已复核|2已审核
	 */
	public Integer getUserCheckStates() {
		return userCheckStates;
	}

	/**
	 * 设置：审核状态 |0待复核|1已复核|2已审核
	 */
	public void setUnitCheckStates(Integer unitCheckStates) {
		this.unitCheckStates = unitCheckStates;
	}

	/**
	 * 获取：审核状态 |0待复核|1已复核|2已审核
	 */
	public Integer getUnitCheckStates() {
		return unitCheckStates;
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
