package com.lwj.socket;

import java.util.ArrayList;
import java.util.List;

public class UserOnline {
	
	private static UserOnline userlist = new UserOnline();
	
	private int userCount;

	List<Integer> userOnline;
	
	private UserOnline () {
		userCount = 0;
		userOnline=new ArrayList<>();
	}
	
	public void user_in(int uid) {
		userOnline.add(uid);
		userCount++;
	}
	
	public void user_off(int uid) {
		userOnline.remove(uid);
		userCount++;
	}
	
	public boolean user_check_online(int uid) {
		if(userOnline.contains(uid))
			return true;
		else
			return false;
	}
	
	public int get_UserCount(){
		return userCount;
	}
	
	public List<Integer> get_UserList(){
		return userOnline;
	}
	
	public static UserOnline getInstance() {
		return userlist;
	}
	
}
