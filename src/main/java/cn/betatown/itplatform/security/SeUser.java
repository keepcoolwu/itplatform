package cn.betatown.itplatform.security;

/**
 * hou'tai
 * 
 * @author thinkpad
 * 
 */
public class SeUser {
	private static final long serialVersionUID = -1363793377621174845L;

	/**
	 * 数据管理级别: NO_LIMIT无限制
	 */
	public static final String DATA_LEVEL_NO_LIMIT = "NO_LIMIT";

	/**
	 * 数据管理级别: ORG商户
	 */
	public static final String DATA_LEVEL_NO_CORPORATION = "CORPORATION";

	/**
	 * 数据管理级别: MALL门店
	 */
	public static final String DATA_LEVEL_NO_MALL = "MALL";

	/**
	 * 数据管理级别: STORE店铺
	 */
	public static final String DATA_LEVEL_NO_STORE = "STORE";

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 加密盐
	 */
	private String salt;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 是否可用
	 */
	private boolean enabled = true;

	/**
	 * 是否管理员
	 */
	private boolean administrator = false;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 数据管理级别: NO_LIMIT无限制/ORG商户/MALL门店/SHOP店铺
	 */
	private String dataLevel;

	/**
	 * 地区编码
	 */
	private String regionCode;

	/**
	 * 地区全名称
	 */
	private String regionName;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * @param salt
	 *            the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName
	 *            the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the administrator
	 */
	public boolean isAdministrator() {
		return administrator;
	}

	/**
	 * @param administrator
	 *            the administrator to set
	 */
	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the dataLevel
	 */
	public String getDataLevel() {
		return dataLevel;
	}

	/**
	 * @param dataLevel
	 *            the dataLevel to set
	 */
	public void setDataLevel(String dataLevel) {
		this.dataLevel = dataLevel;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

}
