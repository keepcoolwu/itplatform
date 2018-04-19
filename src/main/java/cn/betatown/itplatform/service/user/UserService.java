package cn.betatown.itplatform.service.user;

import java.util.List;

import cn.betatown.itplatform.model.User;

/**
 * Created by Administrator on 2017/8/16.
 */
public interface UserService {

	int addUser(User user);

	List<User> findAllUser(int pageNum, int pageSize);
}
