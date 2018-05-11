package com.yss.ms.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.Date;



/**
 * 实体类相关工具类 
 * 解决问题： 1、快速对实体的常驻字段，如：crtUser、crtHost、updUser等值快速注入
 * 
 * @author com.yss.ms
 * @version 1.0
 */
public class EntityUtils {
	/**
	 * 快速将bean的crtUser、crtHost、crtTime、updUser、updHost、updTime附上相关值
	 * 
	 * @param entity 实体bean 
	 * @author 王浩彬
	 */
	public static <T> void setCreatAndUpdatInfo(T entity) {
		setCreateInfo(entity);
		setUpdatedInfo(entity);
	}
	
	/**
	 * 快速将bean的crtUser、crtHost、crtTime附上相关值
	 * 
	 * @param entity 实体bean
	 * @author 王浩彬
	 */
	public static <T> void setCreateInfo(T entity){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String hostIp = "";
		String name = "";
		String id = "";
		if(request!=null) {
			hostIp = String.valueOf(request.getHeader("userHost"));
			name = String.valueOf(request.getHeader("userName"));
			name = URLDecoder.decode(name);
			id = String.valueOf(request.getHeader("userId"));
		}
		// 默认属性
		String[] fields = {"crtName","crtUser","crtHost","crtTime"};
		Field field = ReflectionUtils.getAccessibleField(entity, "crtTime");
		// 默认值
		Object [] value = null;
		if(field!=null&&field.getType().equals(Date.class)){
			value = new Object []{name,id,hostIp,new Date()};
		}
		// 填充默认属性值
		setDefaultValues(entity, fields, value);
	}

	/**
	 * 快速将bean的updUser、updHost、updTime附上相关值
	 * 
	 * @param entity 实体bean
	 * @author 王浩彬
	 */
	public static <T> void setUpdatedInfo(T entity){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String hostIp = "";
		String name = "";
		String id = "";
		if(request!=null) {
			hostIp = String.valueOf(request.getHeader("userHost"));
			name = String.valueOf(request.getHeader("userName"));
			name = URLDecoder.decode(name);
			id = String.valueOf(request.getHeader("userId"));
		}
		// 默认属性
		String[] fields = {"updName","updUser","updHost","updTime"};
		Field field = ReflectionUtils.getAccessibleField(entity, "updTime");
		Object [] value = null;
		if(field!=null&&field.getType().equals(Date.class)){
			value = new Object []{name,id,hostIp,new Date()};
		}
		// 填充默认属性值
		setDefaultValues(entity, fields, value);
	}
	/**
	 * 依据对象的属性数组和值数组对对象的属性进行赋值
	 * 
	 * @param entity 对象
	 * @param fields 属性数组
	 * @param value 值数组
	 * @author 王浩彬
	 */
	public static <T> void setDefaultValues(T entity, String[] fields, Object[] value) {
		for(int i=0;i<fields.length;i++){
			String field = fields[i];
			if(ReflectionUtils.hasField(entity, field)){
				ReflectionUtils.invokeSetter(entity, field, value[i]);
			}
		}
	}
	/**
	 * 根据主键属性，判断主键是否值为空
	 * 
	 * @param entity
	 * @param field
	 * @return 主键为空，则返回false；主键有值，返回true
	 * @author 王浩彬
	 * @date 2016年4月28日
	 */
	public static <T> boolean isPKNotNull(T entity,String field){
		if(!ReflectionUtils.hasField(entity, field)) {
			return false;
		}
		Object value = ReflectionUtils.getFieldValue(entity, field);
		return value!=null&&!"".equals(value);
	}

	/**
	 * 快速将bean的fcreateTime、flastEditTime、fcreatorId、flastEditorId附上相关值
	 *
	 * @author liugang 2017-12-23 16:36
	 * */
	public static <T> void setBizCreatAndUpdatInfo(T entity) {
		setBizCreateInfo(entity);
		setBizUpdatedInfo(entity);
	}

	/**
	 * 快速将bean的fcreateTime、fcreatorId附上相关值
	 *
	 * @author liugang 2017-12-23 16:38
	 * */
	public static <T> void setBizCreateInfo(T entity){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		int id = 0;
		if(request!=null&&request.getHeader("userId")!=null) {
			id = Integer.valueOf(request.getHeader("userId"));
		}
		// 默认属性
		String[] fields = new String[2];
		Field field = null;
		if(ReflectionUtils.hasField(entity, "fcreatorId") && ReflectionUtils.hasField(entity, "fcreateTime")){
			fields[0] = "fcreatorId";
			fields[1] = "fcreateTime";
			field = ReflectionUtils.getAccessibleField(entity, "fcreateTime");
		}else if(ReflectionUtils.hasField(entity, "creatorId") && ReflectionUtils.hasField(entity, "createTime")){
			fields[0] = "creatorId";
			fields[1] = "createTime";
			field = ReflectionUtils.getAccessibleField(entity, "createTime");
		}
		// 默认值
		Object [] value = null;
		if(field!=null&&field.getType().equals(Date.class)){
			value = new Object []{id,new Date()};
		}
		// 填充默认属性值
		setDefaultValues(entity, fields, value);
	}

	/**
	 * 快速将bean的flastEditTime、flastEditorId附上相关值
	 *
	 * @author liugang 2017-12-23 16:46
	 * */
	public static <T> void setBizUpdatedInfo(T entity){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		int id = 0;
		if(request!=null&&request.getHeader("userId")!=null) {
			id = Integer.valueOf(request.getHeader("userId"));
		}
		// 默认属性
		String[] fields = new String[2];
		Field field = null;
		if(ReflectionUtils.hasField(entity, "flastEditorId") && ReflectionUtils.hasField(entity, "flastEditTime")){
			fields[0] = "flastEditorId";
			fields[1] = "flastEditTime";
			field = ReflectionUtils.getAccessibleField(entity, "flastEditTime");
		}else if(ReflectionUtils.hasField(entity, "lastEditorId") && ReflectionUtils.hasField(entity, "lastEditTime")){
			fields[0] = "lastEditorId";
			fields[1] = "lastEditTime";
			field = ReflectionUtils.getAccessibleField(entity, "lastEditTime");
		}
		// 默认值
		Object [] value = null;
		if(field!=null&&field.getType().equals(Date.class)){
			value = new Object []{id,new Date()};
		}
		// 填充默认属性值
		setDefaultValues(entity, fields, value);
	}

	/**
	 * 快速将bean的fdeleteTime、fdeletorId附上相关值
	 *
	 * @author liugang 2017-12-23 16:46
	 * */
	public static <T> void setBizLogicDeleteInfo(T entity){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		int id = 0;
		if(request!=null&&request.getHeader("userId")!=null) {
			id = Integer.valueOf(request.getHeader("userId"));
		}
		// 默认属性
		String[] fields = new String[2];
		Field field = null;
		if(ReflectionUtils.hasField(entity, "fdeletorId") && ReflectionUtils.hasField(entity, "fdeleteTime")){
			fields[0] = "fdeletorId";
			fields[1] = "fdeleteTime";
			field = ReflectionUtils.getAccessibleField(entity, "fdeleteTime");
		}else if(ReflectionUtils.hasField(entity, "deletorId") && ReflectionUtils.hasField(entity, "deleteTime")){
			fields[0] = "deletorId";
			fields[1] = "deleteTime";
			field = ReflectionUtils.getAccessibleField(entity, "deleteTime");
		}
		// 默认值
		Object [] value = null;
		if(field!=null&&field.getType().equals(Date.class)){
			value = new Object []{id,new Date()};
		}
		// 填充默认属性值
		setDefaultValues(entity, fields, value);
	}

	/**
	 * 快速将bean的fcheckerId、fcheckerTime附上相关值
	 * @Author: HuoZhiQiang
	 * @Date: 2018/1/9 14:32
	 */
	public static <T> void setBizUpdateCheckedInfo(T entity){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		int id = 0;
		if(request != null && request.getHeader("userId") != null) {
			id = Integer.valueOf(request.getHeader("userId"));
		}
		// 默认属性
		String[] fields = new String[2];
		Field field = null;
		if(ReflectionUtils.hasField(entity, "fcheckerId") && ReflectionUtils.hasField(entity, "fcheckerTime")){
			fields[0] = "fcheckerId";
			fields[1] = "fcheckerTime";
			field = ReflectionUtils.getAccessibleField(entity, "fcheckerTime");
		}else if(ReflectionUtils.hasField(entity, "checkerId") && ReflectionUtils.hasField(entity, "checkerTime")){
			fields[0] = "checkerId";
			fields[1] = "checkerTime";
			field = ReflectionUtils.getAccessibleField(entity, "checkerTime");
		}
		// 默认值
		Object [] value = null;
		if(field!=null&&field.getType().equals(Date.class)){
			value = new Object []{id,new Date()};
		}
		// 填充默认属性值
		setDefaultValues(entity, fields, value);
	}

}
