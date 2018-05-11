
CREATE DATABASE ms_unit DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
Use ms_unit;

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS=0;


-- ----------------------------
-- Table structure for `t_unit`
-- ----------------------------
DROP TABLE IF EXISTS `t_unit`;
CREATE TABLE `t_unit` (
  `fid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `funit_name` varchar(255) NOT NULL DEFAULT '' COMMENT '单位名称',
  `funit_id` varchar(100) NOT NULL DEFAULT '' COMMENT '单位编码',
  `funit_address` varchar(255) NOT NULL DEFAULT '' COMMENT '单位地址',
  `fproperty_code` int(5) NOT NULL DEFAULT '0' COMMENT '单位类型|100-党政机关|210-参照公务员法管理的事业单位|221-公益一类事业单位|222-公益二类事业单位|230-从事生产经营活动的事业单位|300-社会团体|900-其他',
  `fcorporate_rep` varchar(255) NOT NULL DEFAULT '' COMMENT '法定代表人',
  `femail` varchar(255) NOT NULL DEFAULT '' COMMENT '单位电子邮件',
  `fpost_code` varchar(255) NOT NULL DEFAULT '' COMMENT '单位邮编',
  `ftel` varchar(255) NOT NULL DEFAULT '' COMMENT '单位电话',
  `fcontactp` varchar(255) NOT NULL DEFAULT '' COMMENT '单位联系人',
  `fcontactp_tel` varchar(255) NOT NULL DEFAULT '' COMMENT '联系人电话',

  `fcreator_id` int(11) NOT NULL DEFAULT '0' COMMENT '创建人ID',
  `fcreate_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `flast_editor_id` int(11) NOT NULL DEFAULT '0' COMMENT '最后编辑人ID',
  `flast_edit_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '最后编辑时间',
  `fdeletor_id` int(11) NOT NULL DEFAULT '0' COMMENT '删除人ID',
  `fdelete_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '删除时间',
  `fdeleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否逻辑删除  |0未删除|1已删除',
  `fcheck_states` int(1) NOT NULL DEFAULT '0' COMMENT '审核状态 |0待复核|1已复核|2已审核',
  `fchecker_id` int(11) NOT NULL DEFAULT '0' COMMENT '经办人id',
  `fchecker_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '经办时间',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='单位基本信息表';

-- ----------------------------
-- Records of t_unit
-- ----------------------------

