-- # CREATE DATABASE ms_demo DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
-- #
-- # Use ms_demo;
-- #
-- # SET NAMES utf8;
-- # SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for `t_user_unit`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_unit`;
CREATE TABLE `t_user_unit` (
  `fid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `fuser_fid` int(11) NOT NULL DEFAULT '0' COMMENT '用户表主键',
  `funit_fid` int(11) NOT NULL DEFAULT '0' COMMENT '单位表主键',
  `fuser_sex` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户性别',
  `funit_property_code` int(5) NOT NULL DEFAULT '0' COMMENT '单位类型|100-党政机关|210-参照公务员法管理的事业单位|221-公益一类事业单位|222-公益二类事业单位|230-从事生产经营活动的事业单位|300-社会团体|900-其他',
  `fuser_check_states` int(1) NOT NULL DEFAULT '0' COMMENT '审核状态 |0待复核|1已复核|2已审核',
  `funit_check_states` int(1) NOT NULL DEFAULT '0' COMMENT '审核状态 |0待复核|1已复核|2已审核',

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与单位编制服务';

-- ----------------------------
-- Records of t_user
-- ----------------------------

