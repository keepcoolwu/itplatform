package cn.betatown.itplatform.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.betatown.itplatform.security.entity.UserRoleDTO;
import cn.betatown.itplatform.security.entity.UserRolesVO;
import cn.betatown.itplatform.security.entity.UserSearchDTO;
import cn.betatown.itplatform.security.pojo.User;

@Mapper
public interface UserMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	/**
	 * 分页查询用户数据
	 * 
	 * @return
	 */
	List<UserRoleDTO> getUsers(@Param("userSearch") UserSearchDTO userSearch);

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @param isDel
	 * @return
	 */
	int setDelUser(@Param("id") Integer id, @Param("isDel") Integer isDel);

	/**
	 * 设置用户是否离职
	 * 
	 * @param id
	 * @param isJob
	 * @return
	 */
	int setJobUser(@Param("id") Integer id, @Param("isJob") Integer isJob);

	/**
	 * 查询用户及对应的角色
	 * 
	 * @param id
	 * @return
	 */
	UserRolesVO getUserAndRoles(Integer id);

	/**
	 * 根据用户名和密码查找用户
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	User findUser(@Param("username") String username, @Param("password") String password);

	/**
	 * 根据手机号获取用户数据
	 * 
	 * @param mobile
	 * @return
	 */
	User findUserByMobile(String mobile);

	/**
	 * 根据用户名获取用户数据
	 * 
	 * @param username
	 * @return
	 */
	User findUserByName(String username);

	/**
	 * 修改用户密码
	 * 
	 * @param id
	 * @param password
	 * @return
	 */
	int updatePwd(@Param("id") Integer id, @Param("password") String password);

	/**
	 * 是否锁定用户
	 * 
	 * @param id
	 * @param isLock
	 * @return
	 */
	int setUserLockNum(@Param("id") Integer id, @Param("isLock") int isLock);
}