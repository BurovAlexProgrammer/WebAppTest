/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package ru.hostco.burovalex.webapptest.services;

import ru.hostco.burovalex.webapptest.entity.User;

public interface UserInfoService {

	/** find user by account **/
	public User findUser(String account);
	
	/** update user **/
	public User updateUser(User user);
}
