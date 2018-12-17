package com.lwj.service;

import java.util.HashMap;

import com.lwj.util.pojo.JsonResult;
import com.lwj.util.enums.ResponseType;

public interface ILoginService {
	
	public  ResponseType login_check(String account, String password ,HashMap<String, Object> map);
	
	public JsonResult login(String account, String password);
	

}
