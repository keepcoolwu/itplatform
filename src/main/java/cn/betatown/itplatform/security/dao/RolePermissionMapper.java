package cn.betatown.itplatform.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.betatown.itplatform.security.pojo.RolePermissionKey;

@Mapper
public interface RolePermissionMapper {

	int deleteByPrimaryKey(RolePermissionKey key);

	int insert(RolePermissionKey record);

	int insertSelective(RolePermissionKey record);

	List<RolePermissionKey> findByRole(int roleId);
}