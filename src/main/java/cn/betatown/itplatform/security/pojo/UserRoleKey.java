package cn.betatown.itplatform.security.pojo;

import java.io.Serializable;

public class UserRoleKey implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4776431817059750142L;

	private Integer userId;

    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override public String toString() {
        return "UserRoleKey{" + "userId=" + userId + ", roleId=" + roleId + '}';
    }
}