package com.lwj.service;

import java.util.HashMap;

import com.lwj.util.pojo.JsonResult;
import com.lwj.util.enums.ResponseType;

public interface IFriendDelete {
	
	public ResponseType delete(Integer uid1, Integer uid2, HashMap<String, Object> map);
	
	public JsonResult del_friend(Integer uid1, Integer uid2);
}
