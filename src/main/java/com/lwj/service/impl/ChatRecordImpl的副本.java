package com.lwj.service.impl;

import java.util.Date;
import java.util.List;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwj.persistence.dao.ChatRecordMapper;
import com.lwj.persistence.pojo.ChatRecord;
import com.lwj.service.IChatRecord;
import com.lwj.util.enums.ResponseType;
import com.lwj.util.pojo.JsonResult;

@Service("chatService")
public class ChatRecordImpl implements IChatRecord {

	@Resource
	private ChatRecordMapper chatRecordDao;
	
	@Override
	public int storeOfflineMsg(Integer sender, Integer receiver, String content) {
		ChatRecord record = new ChatRecord();
		record.setSender(sender);
		record.setReceiver(receiver);
		Date dateTime = new Date();
		record.setTime(dateTime);
		record.setContent(content);
		return chatRecordDao.insert(record);
		
	}

	@Override
	public List<ChatRecord> readMsg(Integer sender, Integer receiver) {
//		List<ChatRecord> offlineMsg = chatRecordDao.selectByReceiver(receiver);
		List<ChatRecord> message = chatRecordDao.selectBySenderAndReceiver(sender, receiver);
		return message;
	}
	
	@Override
	public  ResponseType findMsg(Integer sender, Integer receiver, HashMap<String, Object> map) {
		map.put("offlineMsg", readMsg(sender,receiver));
		return ResponseType.MSG_GET;
	}
	
	@Override
	public JsonResult getMsg(Integer sender, Integer receiver) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
        ResponseType responseType = findMsg(sender ,receiver , data);
        
        return new JsonResult(responseType,data);
	}

	
	
}
