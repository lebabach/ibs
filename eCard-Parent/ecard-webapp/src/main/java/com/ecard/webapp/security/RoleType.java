/**
 * 
 */
package com.ecard.webapp.security;

/**
 * @author nhat.nguyen
 *
 */
public enum RoleType {
	
	ROLE_USER(1),
	ROLE_LEADER(2),
	ROLE_OPERATOR(3),
	ROLE_SUPERVISOR(4),
	ROLE_ADMIN(5),
	ROLE_AUTHORITY_USER(6),
	ROLE_OPERATOR_MANAGER(7);
	
	//role_id: 2,3,7 ==> process (role_admin_id/user_info)
	// otherwise : ==>services, first 3 menu items
	private int value;

	private RoleType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public static String getEnumNameForValue(int value) {
		RoleType[] values = RoleType.values();
		String enumValue = null;
		for (RoleType eachValue : values) {
			if (eachValue.getValue() == value) {
				return eachValue.name();
			}
		}
		return enumValue;
	}
	
	public static String getRedirectUrlByRoleName(String roleName){		
		String url = null;
		RoleType roleType = RoleType.valueOf(roleName);
		int permissionType = roleType.getValue();
		switch (permissionType) {
		case 1:
			//comment not login with permission user
			url = "/user/home";
			break;
		case 2:
			url = "/manager/home";
			break;
		case 3:
			url = "/manager/home";
			break;
		case 4:
			url = "/manager/home";
			break;
		case 5:
			url = "/manager/home";
			break;
		case 6:
			url = "/manager/home";
			break;
		case 7:
			url = "/manager/home";
			break;
		default:
			break;
		}
		return url;
	}

}
