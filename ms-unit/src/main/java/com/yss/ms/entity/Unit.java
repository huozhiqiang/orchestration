package com.yss.ms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yss.ms.common.util.UniqueVerifiableVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 单位基本信息表
 *
 * @author HuoZhiQiang
 * @since 2018-05-08 13:27:44
 */
@Table(name = "t_unit")
@ApiModel(value="单位基本信息表参数", description = "单位基本信息表参数描述")
public class Unit implements Serializable, UniqueVerifiableVO {
	private static final long serialVersionUID = 1L;
	
	
  	/**
     * 主键id
     */
	@Id
	@Column(name = "fid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "主键ID", required = true)
    private Integer id;
	
	
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
	
	
    /**
     * 单位类型|100-党政机关|210-参照公务员法管理的事业单位|221-公益一类事业单位|222-公益二类事业单位|230-从事生产经营活动的事业单位|300-社会团体|900-其他
     */
    @Column(name = "fproperty_code")
    @ApiModelProperty(value="单位类型|100-党政机关|210-参照公务员法管理的事业单位|221-公益一类事业单位|222-公益二类事业单位|230-从事生产经营活动的事业单位|300-社会团体|900-其他", required = true)
    private Integer propertyCode;
	
	
    /**
     * 法定代表人
     */
    @Column(name = "fcorporate_rep")
    @ApiModelProperty(value="法定代表人", required = true)
    private String corporateRep;
	
	
    /**
     * 单位电子邮件
     */
    @Column(name = "femail")
    @ApiModelProperty(value="单位电子邮件", required = true)
    private String email;
	
	
    /**
     * 单位邮编
     */
    @Column(name = "fpost_code")
    @ApiModelProperty(value="单位邮编", required = true)
    private String postCode;
	
	
    /**
     * 单位电话
     */
    @Column(name = "ftel")
    @ApiModelProperty(value="单位电话", required = true)
    private String tel;
	
	
    /**
     * 单位联系人
     */
    @Column(name = "fcontactp")
    @ApiModelProperty(value="单位联系人", required = true)
    private String contactp;
	
	
    /**
     * 联系人电话
     */
    @Column(name = "fcontactp_tel")
    @ApiModelProperty(value="联系人电话", required = true)
    private String contactpTel;
	
	
    /**
     * 创建人ID
     */
    @Column(name = "fcreator_id")
    @ApiModelProperty(value="创建人ID", required = true)
    private Integer creatorId;
	
	
    /**
     * 创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fcreate_time")
    @ApiModelProperty(value="创建时间", required = true)
    private Date createTime;
	
	
    /**
     * 最后编辑人ID
     */
    @Column(name = "flast_editor_id")
    @ApiModelProperty(value="最后编辑人ID", required = true)
    private Integer lastEditorId;
	
	
    /**
     * 最后编辑时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "flast_edit_time")
    @ApiModelProperty(value="最后编辑时间", required = true)
    private Date lastEditTime;
	
	
    /**
     * 删除人ID
     */
    @Column(name = "fdeletor_id")
    @ApiModelProperty(value="删除人ID", required = true)
    private Integer deletorId;
	
	
    /**
     * 删除时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fdelete_time")
    @ApiModelProperty(value="删除时间", required = true)
    private Date deleteTime;
	
	
    /**
     * 是否逻辑删除  |0未删除|1已删除
     */
    @Column(name = "fdeleted")
    @ApiModelProperty(value="是否逻辑删除  |0未删除|1已删除", required = true)
    private Integer deleted;
	
	
    /**
     * 审核状态 |0待复核|1已复核|2已审核
     */
    @Column(name = "fcheck_states")
    @ApiModelProperty(value="审核状态 |0待复核|1已复核|2已审核", required = true)
    private Integer checkStates;
	
	
    /**
     * 经办人id
     */
    @Column(name = "fchecker_id")
    @ApiModelProperty(value="经办人id", required = true)
    private Integer checkerId;
	
	
    /**
     * 经办时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fchecker_time")
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
	 * 设置：单位名称
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * 获取：单位名称
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 设置：单位编码
	 */
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	/**
	 * 获取：单位编码
	 */
	public String getUnitId() {
		return unitId;
	}

	/**
	 * 设置：单位地址
	 */
	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}

	/**
	 * 获取：单位地址
	 */
	public String getUnitAddress() {
		return unitAddress;
	}

	/**
	 * 设置：单位类型|100-党政机关|210-参照公务员法管理的事业单位|221-公益一类事业单位|222-公益二类事业单位|230-从事生产经营活动的事业单位|300-社会团体|900-其他
	 */
	public void setPropertyCode(Integer propertyCode) {
		this.propertyCode = propertyCode;
	}

	/**
	 * 获取：单位类型|100-党政机关|210-参照公务员法管理的事业单位|221-公益一类事业单位|222-公益二类事业单位|230-从事生产经营活动的事业单位|300-社会团体|900-其他
	 */
	public Integer getPropertyCode() {
		return propertyCode;
	}

	/**
	 * 设置：法定代表人
	 */
	public void setCorporateRep(String corporateRep) {
		this.corporateRep = corporateRep;
	}

	/**
	 * 获取：法定代表人
	 */
	public String getCorporateRep() {
		return corporateRep;
	}

	/**
	 * 设置：单位电子邮件
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取：单位电子邮件
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置：单位邮编
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * 获取：单位邮编
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * 设置：单位电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * 获取：单位电话
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * 设置：单位联系人
	 */
	public void setContactp(String contactp) {
		this.contactp = contactp;
	}

	/**
	 * 获取：单位联系人
	 */
	public String getContactp() {
		return contactp;
	}

	/**
	 * 设置：联系人电话
	 */
	public void setContactpTel(String contactpTel) {
		this.contactpTel = contactpTel;
	}

	/**
	 * 获取：联系人电话
	 */
	public String getContactpTel() {
		return contactpTel;
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

	/**
	 * 业务主键
	 *
	 * @author HuoZhiQiang 2018-05-08 13:27:44
	 */
	@Override
	public String fetchUniqueProperty() {
        return null;
    }
}
