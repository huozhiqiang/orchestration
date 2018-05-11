package com.yss.ms.common.util;

/**
 * VO唯一性验证接口
 * 
 * @author chengxiangzhao
 * @version 1.0, 2017-3-5
 * @since 1.0, 2017-3-10
 */
public interface UniqueVerifiableVO {

	/**
     * 返回唯一性约束的属性名，如: code <br>
	 * 组合属性验证，用 <b>&</b> 连接，例如：code&name <br>
	 * 多个属性验证，用 <b>,或者|</b> 连接，例如：code|name
	 * @return
	 */
	String fetchUniqueProperty();
}