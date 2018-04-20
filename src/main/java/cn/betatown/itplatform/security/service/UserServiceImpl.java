package cn.betatown.itplatform.security.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.betatown.itplatform.common.utils.DateUtil;
import cn.betatown.itplatform.security.dao.RoleMapper;
import cn.betatown.itplatform.security.dao.UserMapper;
import cn.betatown.itplatform.security.dao.UserRoleMapper;
import cn.betatown.itplatform.security.entity.UserDTO;
import cn.betatown.itplatform.security.entity.UserRoleDTO;
import cn.betatown.itplatform.security.entity.UserRolesVO;
import cn.betatown.itplatform.security.entity.UserSearchDTO;
import cn.betatown.itplatform.security.pojo.Role;
import cn.betatown.itplatform.security.pojo.User;
import cn.betatown.itplatform.security.pojo.UserRoleKey;
import cn.betatown.itplatform.security.utils.PageDataResult;
import cn.betatown.itplatform.security.utils.SendMsgServer;

/**
 * @项目名称：wyait-manage
 * @包名：com.wyait.manage.service @类描述：
 * @创建人：wyait @创建时间：2017-12-20 15:53 @version：V1.0
 */
@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public PageDataResult getUsers(UserSearchDTO userSearch, int page, int limit) {
		// 时间处理
		if (null != userSearch) {
			if (StringUtils.isNotEmpty(userSearch.getInsertTimeStart())
					&& StringUtils.isEmpty(userSearch.getInsertTimeEnd())) {
				userSearch.setInsertTimeEnd(DateUtil.format(new Date()));
			} else if (StringUtils.isEmpty(userSearch.getInsertTimeStart())
					&& StringUtils.isNotEmpty(userSearch.getInsertTimeEnd())) {
				userSearch.setInsertTimeStart(DateUtil.format(new Date()));
			}
			if (StringUtils.isNotEmpty(userSearch.getInsertTimeStart())
					&& StringUtils.isNotEmpty(userSearch.getInsertTimeEnd())) {
				if (userSearch.getInsertTimeEnd().compareTo(userSearch.getInsertTimeStart()) < 0) {
					String temp = userSearch.getInsertTimeStart();
					userSearch.setInsertTimeStart(userSearch.getInsertTimeEnd());
					userSearch.setInsertTimeEnd(temp);
				}
			}
		}
		PageDataResult pdr = new PageDataResult();
		PageHelper.startPage(page, limit);
		List<UserRoleDTO> urList = userMapper.getUsers(userSearch);
		// 获取分页查询后的数据
		PageInfo<UserRoleDTO> pageInfo = new PageInfo<>(urList);
		// 设置获取到的总记录数total：
		pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
		// 将角色名称提取到对应的字段中
		if (null != urList && urList.size() > 0) {
			for (UserRoleDTO ur : urList) {
				List<Role> roles = roleMapper.getRoleByUserId(ur.getId());
				if (null != roles && roles.size() > 0) {
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < roles.size(); i++) {
						Role r = roles.get(i);
						sb.append(r.getRoleName());
						if (i != (roles.size() - 1)) {
							sb.append("，");
						}
					}
					ur.setRoleNames(sb.toString());
				}
			}
		}
		pdr.setList(urList);
		return pdr;
	}

	@Override
	public int setDelUser(Integer id, Integer isDel) {
		return this.userMapper.setDelUser(id, isDel);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 30000, rollbackFor = {
			RuntimeException.class, Exception.class })
	public String setUser(User user, String roleIds) {
		int userId;
		if (user.getId() != null) {
			// 判断用户是否已经存在
			User existUser = this.userMapper.findUserByMobile(user.getMobile());
			if (null != existUser && !String.valueOf(existUser.getId()).equals(String.valueOf(user.getId()))) {
				return "该手机号已经存在";
			}
			User exist = this.userMapper.findUserByName(user.getUsername());
			if (null != exist && !String.valueOf(existUser.getId()).equals(String.valueOf(user.getId()))) {
				return "该用户名已经存在";
			}
			// 更新用户
			userId = user.getId();
			user.setUpdateTime(new Date());
			// 设置加密密码
			if (StringUtils.isNotBlank(user.getPassword())) {
				user.setPassword(DigestUtils.md5Hex(user.getPassword()));
			}
			this.userMapper.updateByPrimaryKeySelective(user);
			// 删除之前的角色
			List<UserRoleKey> urs = this.userRoleMapper.findByUserId(userId);
			if (null != urs && urs.size() > 0) {
				for (UserRoleKey ur : urs) {
					this.userRoleMapper.deleteByPrimaryKey(ur);
				}
			}
		} else {
			// 判断用户是否已经存在
			User existUser = this.userMapper.findUserByMobile(user.getMobile());
			if (null != existUser) {
				return "该手机号已经存在";
			}
			User exist = this.userMapper.findUserByName(user.getUsername());
			if (null != exist) {
				return "该用户名已经存在";
			}
			// 新增用户
			user.setInsertTime(new Date());
			user.setIsDel(false);
			user.setIsJob(false);
			// 设置加密密码
			if (StringUtils.isNotBlank(user.getPassword())) {
				user.setPassword(DigestUtils.md5Hex(user.getPassword()));
			} else {
				user.setPassword(DigestUtils.md5Hex("654321"));
			}
			this.userMapper.insert(user);
			userId = user.getId();
		}
		// 给用户授角色
		String[] arrays = roleIds.split(",");
		for (String roleId : arrays) {
			UserRoleKey urk = new UserRoleKey();
			urk.setRoleId(Integer.valueOf(roleId));
			urk.setUserId(userId);
			this.userRoleMapper.insert(urk);
		}
		return "ok";
	}

	public static void main(String[] args) {
		System.out.println(DigestUtils.md5Hex("654321"));
	}

	@Override
	public int setJobUser(Integer id, Integer isJob) {
		return this.userMapper.setJobUser(id, isJob);
	}

	@Override
	public UserRolesVO getUserAndRoles(Integer id) {
		// 获取用户及他对应的roleIds
		return this.userMapper.getUserAndRoles(id);

	}

	@Override
	public String sendMsg(UserDTO user) {
		// 校验用户名和密码 是否正确
		User existUser = this.userMapper.findUser(user.getUsername(), DigestUtils.md5Hex(user.getPassword()));
		if (null != existUser && existUser.getMobile().equals(user.getMobile())) {
			String mobile_code = String.valueOf((Math.random() * 9 + 1) * 100000);
			// 保存短信
			existUser.setMcode(mobile_code);
			existUser.setSendTime(new Date());
			this.userMapper.updateByPrimaryKeySelective(existUser);
			// 发送短信验证码 ok、no
			return SendMsgServer.SendMsg(mobile_code + "(验证码)，如不是本人操作，请忽略此消息。", user.getMobile());
		} else {
			return "您输入的用户信息有误，请您重新输入";
		}
	}

	@Override
	public User findUserByMobile(String mobile) {
		return this.userMapper.findUserByMobile(mobile);
	}

	@Override
	public String sendMessage(int userId, String mobile) {
		String mobile_code = String.valueOf((Math.random() * 9 + 1) * 100000);
		// 保存短信
		User user = new User();
		user.setId(userId);
		user.setMcode(mobile_code);
		user.setSendTime(new Date());
		this.userMapper.updateByPrimaryKeySelective(user);
		// 发送短信验证码 ok、no
		return SendMsgServer.SendMsg(mobile_code + "(验证码)，如不是本人操作，请忽略此消息。", user.getMobile());
	}

	@Override
	public int updatePwd(Integer id, String password) {
		return this.userMapper.updatePwd(id, password);
	}

	@Override
	public int setUserLockNum(Integer id, int isLock) {
		return this.userMapper.setUserLockNum(id, isLock);
	}
}
