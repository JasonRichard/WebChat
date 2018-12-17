package com.lwj.service.impl;

import org.springframework.stereotype.Service;

import java.util.HashMap;

import javax.annotation.Resource;
import com.lwj.service.IFriendDelete;
import com.lwj.service.IInitService;
import com.lwj.util.enums.ResponseType;
import com.lwj.util.pojo.JsonResult;
import com.alibaba.fastjson.JSON;
import com.lwj.persistence.dao.FriendMapper;

@Service
public class FriendDelete implements IFriendDelete{

	@Resource
	FriendMapper friend_Dao;
	
	@Resource
	private IInitService initService;
	
	@Override
	public ResponseType delete(Integer uid1, Integer uid2, HashMap<String, Object> map) {
		friend_Dao.deleteByUID(uid1, uid2);
		friend_Dao.deleteByUID(uid2, uid1);
		return ResponseType.OPERATE_DONE;
		
	}

	@Override
	public JsonResult del_friend(Integer uid1, Integer uid2) {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
        ResponseType responseType = delete(uid1,uid2,data);        
        
        return new JsonResult(responseType,data);
	}

}
