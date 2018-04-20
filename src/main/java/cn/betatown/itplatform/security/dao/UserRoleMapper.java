package cn.betatown.itplatform.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.betatown.itplatform.security.pojo.UserRoleKey;

@Mapper
public interface UserRoleMapper {

	int deleteByPrimaryKey(UserRoleKey key);

	int insert(UserRoleKey record);

	int insertSelective(UserRoleKey record);

	/**
	 * 根据用户获取用户角色中间表数据
	 * 
	 * @param userId
	 * @return
	 */
	List<UserRoleKey> findByUserId(int userId);
}