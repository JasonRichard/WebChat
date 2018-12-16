package com.lwj.service;

import java.util.List;

import com.lwj.persistence.pojo.ChatRecord;

public interface IChatRecord {
	
	public int storeOfflineMsg(Integer sender, Integer receiver, String content);
	
	public List<ChatRecord> readOfflineMsg(Integer receiver);
	
}
