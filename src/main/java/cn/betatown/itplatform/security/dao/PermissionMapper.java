package cn.betatown.itplatform.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.betatown.itplatform.security.entity.PermissionVO;
import cn.betatown.itplatform.security.pojo.Permission;

@Mapper
public interface PermissionMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Permission record);

	int insertSelective(Permission record);

	Permission selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Permission record);

	int updateByPrimaryKey(Permission record);

	/**
	 * 查找所有权限数据
	 * 
	 * @return
	 */
	List<Permission> findAll();

	/**
	 * 查找所有子节点
	 * 
	 * @param pid
	 * @return
	 */
	List<Permission> findChildPerm(int pid);

	/**
	 * 查询权限树列表
	 * 
	 * @return
	 */
	List<PermissionVO> findPerms();

	/**
	 * 根据角色id获取权限数据
	 * 
	 * @param roleId
	 * @return
	 */
	List<Permission> findPermsByRole(Integer roleId);

	List<PermissionVO> getUserPerms(Integer userId);
}