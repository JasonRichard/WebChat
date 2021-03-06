package com.lwj.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwj.persistence.dao.ChatRecordMapper;
import com.lwj.persistence.pojo.ChatRecord;
import com.lwj.service.IChatRecord;
import com.lwj.util.enums.ResponseType;
import com.lwj.util.pojo.JsonResult;
import com.lwj.util.pojo.MsgInf;

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
	public List<ChatRecord> readMsg(Integer uid1, Integer uid2) {
//		List<ChatRecord> offlineMsg = chatRecordDao.selectByReceiver(receiver);
		List<ChatRecord> message = chatRecordDao.selectBySenderAndReceiver(uid1, uid2);
		return message;
	}

	@Override
	public ResponseType findMsg(Integer sender, Integer receiver, HashMap<String, Object> map) {
		List<ChatRecord> message = readMsg(sender,receiver);
		List<MsgInf> message2 = new ArrayList<MsgInf>();
		int record_no = 0;
		for(ChatRecord record: message) {
			MsgInf temp = new MsgInf();
			if(record.getSender() == sender)
				temp.setFromWho(true);
			else
				temp.setFromWho(false);
			temp.setId(record_no++);
			temp.setText(record.getContent());
			message2.add(temp);
		}
		map.put("message", message2);
		return ResponseType.MSG_GET;
	}

	@Override
	public JsonResult getMsg(Integer sender, Integer receiver) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
        ResponseType responseType = findMsg(sender ,receiver , data);
        
        return new JsonResult(responseType,data);
	}

}
