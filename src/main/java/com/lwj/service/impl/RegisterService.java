package com.lwj.service.impl;

import org.springframework.stereotype.Service;

import java.util.HashMap;

import javax.annotation.Resource;

import com.lwj.service.IRegisterService;
import com.lwj.util.enums.ResponseType;
import com.lwj.util.pojo.JsonResult;
import com.lwj.persistence.dao.UserPrivateMapper;
import com.lwj.persistence.pojo.UserPrivate;

@Service
public class RegisterService implements IRegisterService{

	@Resource
	private UserPrivateMapper userPrivate_Dao;
	
	@Override
	public ResponseType add_user(String account, String password, String tel, HashMap<String, Object> map) {
		UserPrivate user_log = userPrivate_Dao.selectByAccount(account);
		if(user_log!=null)
			return ResponseType.REGISTER_WRONG;
		else {
			user_log = new UserPrivate();
			user_log.setAccount(account);
			user_log.setPassword(password);
			user_log.setTel(tel);
			user_log.setIpAdd("0");
			userPrivate_Dao.insert(user_log);
			return ResponseType.REGISTER_SUCESS;
		}
	}

	@Override
	public JsonResult register(String account, String password, String tel) {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
        ResponseType responseType = add_user(account,password,tel,data);
        
        return new JsonResult(responseType,data);
	}
	
}
