package com.lwj.socket;

import java.util.ArrayList;
import java.util.List;

public class UserInMatch {
	
	private static UserInMatch userlist = new UserInMatch();
	
	private int userCount;

	List<String> userList;
	
	private int flag;
	
	private UserInMatch() {
		userCount = 0;
		userList=new ArrayList<String>();
		flag = 0;
	}
	
	public void user_in(String uid) {
		if(userList.contains(uid))
			user_off(uid);
		userList.add(uid);
		userCount++;
	}
	
	public void user_off(String uid) {
		userList.remove(uid);
		userCount--;
	}
	
	public String user_match_check(String uid) {
		if(userList.size() < 2)
			return "waiting";
		if(userList.get(0) == uid)
			return userList.get(1);
		else if(userList.get(1) == uid)
			return userList.get(0);
		
		else  return "waiting";
	}
	
	public int get_UserCount(){
		return userCount;
	}
	
	public void check_flag() {
		if(flag == 2) {
			userList.remove(0);
			userList.remove(1);
			userCount -= 2;
			flag = 0;
		}
	}
	
	public void set_flag() {
		flag++;
	}
	
	public List<String> get_UserList(){
		return userList;
	}
	
	public static UserInMatch getInstance() {
		return userlist;
	}
}
