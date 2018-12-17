package com.lwj.socket;

import java.util.ArrayList;
import java.util.List;

public class UserInMatch {
	
	private static UserInMatch userlist = new UserInMatch();
	
	private int userCount;

	List<Integer> userList;
	
	private UserInMatch() {
		userCount = 0;
		userList=new ArrayList<>();
	}
	
	public void user_in(int uid) {
		if(userList.contains(uid))
			user_off(uid);
		userList.add(uid);
		userCount++;
	}
	
	public void user_off(int uid) {
		userList.remove(uid);
		userCount--;
	}
	
	public int user_match_check(int uid) {
		if(userList.get(0) == uid)
			return userList.get(1);
		else if(userList.get(1) == uid)
			return userList.get(0);
		
		else  return -1;
	}
	
	public int get_UserCount(){
		return userCount;
	}
	
	public List<Integer> get_UserList(){
		return userList;
	}
	
	public static UserInMatch getInstance() {
		return userlist;
	}
	
}
