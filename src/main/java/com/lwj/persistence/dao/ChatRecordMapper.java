package com.lwj.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lwj.persistence.pojo.ChatRecord;
import com.lwj.persistence.pojo.ChatRecordKey;

public interface ChatRecordMapper {
    int deleteByPrimaryKey(ChatRecordKey key);

    int insert(ChatRecord record);

    int insertSelective(ChatRecord record);

    ChatRecord selectByPrimaryKey(ChatRecordKey key);

    int updateByPrimaryKeySelective(ChatRecord record);

    int updateByPrimaryKey(ChatRecord record);

	List<ChatRecord> selectByReceiver(Integer receiver);

	List<ChatRecord> selectBySenderAndReceiver(@Param(value="uid1")Integer uid1, @Param(value="uid2")Integer uid2);
}