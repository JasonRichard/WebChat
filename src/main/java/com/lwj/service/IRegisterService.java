package com.lwj.service;

import java.util.HashMap;

import com.lwj.util.pojo.JsonResult;
import com.lwj.util.enums.ResponseType;

public interface IRegisterService {
	public	ResponseType add_user(String account, String password, String tel, HashMap<String, Object> map);
	
	public	JsonResult register(String account, String password, String tel);
	
	
}
