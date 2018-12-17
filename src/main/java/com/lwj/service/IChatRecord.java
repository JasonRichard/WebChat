package com.lwj.service;

import java.util.HashMap;
import java.util.List;


import com.lwj.persistence.pojo.ChatRecord;
import com.lwj.util.enums.ResponseType;
import com.lwj.util.pojo.JsonResult;

public interface IChatRecord {
	
	public int storeOfflineMsg(Integer sender, Integer receiver, String content);
	
//	public List<ChatRecord> readOfflineMsg(Integer receiver);

	List<ChatRecord> readMsg(Integer uid1, Integer uid2);
	
	public  ResponseType findMsg(Integer sender, Integer receiver, HashMap<String, Object> map);
	
	public JsonResult getMsg(Integer sender, Integer receiver);
}
