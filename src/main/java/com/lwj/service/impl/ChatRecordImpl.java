package com.lwj.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwj.persistence.dao.ChatRecordMapper;
import com.lwj.persistence.pojo.ChatRecord;
import com.lwj.service.IChatRecord;

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

}
