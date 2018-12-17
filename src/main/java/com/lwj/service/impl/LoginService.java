package com.lwj.service.impl;

import org.springframework.stereotype.Service;

import java.util.HashMap;

import javax.annotation.Resource;

import com.lwj.service.IChatRecord;
import com.lwj.service.ILoginService;
import com.lwj.util.enums.ResponseType;
import com.lwj.util.pojo.JsonResult;
import com.lwj.persistence.dao.ChatRecordMapper;
import com.lwj.persistence.dao.UserPrivateMapper;
import com.lwj.persistence.pojo.UserPrivate;
import com.lwj.service.IInitService;

@Service
public class LoginService implements ILoginService{
	
	@Resource
	private UserPrivateMapper userPrivate_Dao;
	 
	@Resource
	private ChatRecordMapper chatRecordDao;
	
	@Resource
	private IChatRecord chatRecord;
	
	@Resource
	private IInitService initService;
	
	@Override
	public  ResponseType login_check(String account, String password, HashMap<String, Object> map) {
		UserPrivate user_log = userPrivate_Dao.selectByAccount(account);
		if(user_log==null)
			return ResponseType.LOGIN_WRONG;
		if(password.equals(user_log.getPassword()))
		{
			int uid = user_log.getUid();
			map.put("uid", uid);
//			System.out.println(chatRecordDao.selectByReceiver(uid));
			map.put("offlineMsg", chatRecord.readOfflineMsg(uid));
			//获取好友列表
			initService.getInfo2(uid, map);
			return ResponseType.LOGIN_SUCESS;
		}
		else
			return ResponseType.LOGIN_WRONG;
	}
	
	@Override
	public JsonResult login(String account, String password) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
        ResponseType responseType = login_check(account,password,data);
        
        return new JsonResult(responseType,data);
	}
	
	
	
}
