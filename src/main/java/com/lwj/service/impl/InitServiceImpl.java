package com.lwj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwj.service.IInitService;
import com.lwj.util.enums.ResponseType;
import com.lwj.util.pojo.JsonResult;
import com.lwj.persistence.dao.UserPublicMapper;
import com.lwj.persistence.pojo.UserPublic;
import com.alibaba.fastjson.JSON;
import com.lwj.persistence.dao.FriendMapper;
import com.lwj.persistence.pojo.Friend;


@Service
public class InitServiceImpl implements IInitService {

	@Resource
	private UserPublicMapper userPublicDao;
	
	@Resource
	private FriendMapper friendDao;
	
	public HashMap<String, Object> Info2Hash(UserPublic record){
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("uid", record.getUid());
		data.put("nickname", record.getNickname());
		data.put("gender", record.getGender());
		data.put("avatar", record.getAvatar());
		return data;
	}
	
	public HashMap<String, Object> Info2Hash(List<UserPublic> record){
		HashMap<String, Object> data = new HashMap<String, Object>();
		for(int i=0;i<record.size();++i)
		{
			data.put("friendInfo"+i,Info2Hash(record.get(i)));
			
		}
		return data;
	}
	
	@Override
	public ResponseType getInfo(Integer uid, HashMap<String, Object> map) {
		UserPublic userInfo = userPublicDao.selectByPrimaryKey(uid);
		map.put("userInfo", Info2Hash(userInfo));

		List<Friend> friendPair = friendDao.selectByUID(uid);
		List<UserPublic> friendInfo = new ArrayList<UserPublic>();
		for(int i=0;i<friendPair.size();++i) {
			Friend temp = friendPair.get(i);
			int friendId;
			if(temp.getUid1()==uid) {
				friendId=temp.getUid2();
			}else
				friendId=temp.getUid1();
//			map.put("friendInfo_"+i, Info2Hash(userPublicDao.selectByPrimaryKey(friendId)));
			friendInfo.add(userPublicDao.selectByPrimaryKey(friendId));
		}
		map.put("friendInfo", friendInfo);
		return ResponseType.INFO_GET;
	}

	//*******封装好用于给data封装数据
	public void getInfo2(Integer uid, HashMap<String, Object> map) {
		UserPublic userInfo = userPublicDao.selectByPrimaryKey(uid);
		map.put("userInfo", Info2Hash(userInfo));

		List<Friend> friendPair = friendDao.selectByUID(uid);
		List<UserPublic> friendInfo = new ArrayList<UserPublic>();
		for(int i=0;i<friendPair.size();++i) {
			Friend temp = friendPair.get(i);
			int friendId;
			if(temp.getUid1()==uid) {
				friendId=temp.getUid2();
			}else
				friendId=temp.getUid1();
//			map.put("friendInfo_"+i, Info2Hash(userPublicDao.selectByPrimaryKey(friendId)));
			friendInfo.add(userPublicDao.selectByPrimaryKey(friendId));
		}
		map.put("friendInfo", friendInfo);
	}
	
	@Override
	public JsonResult init(Integer uid) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
        ResponseType responseType = getInfo(uid,data);
        
        return new JsonResult(responseType,data);
	}
	
	
	
}