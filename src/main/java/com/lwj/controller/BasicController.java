package com.lwj.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Queue;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lwj.util.enums.ResponseType;
import com.lwj.util.pojo.JsonResult;
import com.lwj.persistence.dao.ChatRecordMapper;
import com.lwj.persistence.pojo.ChatRecord;
import com.lwj.service.*;
import com.lwj.socket.UserOnline;
import com.lwj.socket.UserInMatch;
import com.alibaba.fastjson.JSON;

//import com.lwj.service.impl.RegisterService;

@Controller
@RequestMapping("/basic")
public class BasicController {
	
	@Resource
	ILoginService loginService;
	
	@Resource
	IInitService initService;

	@Resource
	IRegisterService registerService;
	
	@Resource
	IFriendDelete friendDelete;
	
	@Resource
	IChatRecord chatRecord;
	
	@RequestMapping("/chat")
	public String chat(HttpServletRequest request,Model model){
		int uid1 = Integer.parseInt(request.getParameter("uid1"));//uid1为当前用户uid
		int uid2 = Integer.parseInt(request.getParameter("uid2"));
		/*判断uid2是否在线
		UserOnline userList = UserOnline.getInstance();
		if(!userList.user_check_online(uid2)) {

		}*/
		
		String u1=String.valueOf(uid1);
		String u2=String.valueOf(uid2);
		String room = uid1 < uid2 ? u1+"_"+u2: u2+"_"+u1;
		model.addAttribute("room", room);
		model.addAttribute("uid", uid1);
		return "chat";
	}
	
	@RequestMapping("/anonymous")
	public String chat(HttpServletRequest request, HttpServletResponse response, Model model){
		String uid = request.getParameter("uid");//uid为当前用户uid
		UserInMatch userList = UserInMatch.getInstance();
		
		userList.user_in(uid);
		
		String uid1 = uid;
		String uid2 = "waiting";
		while(userList.user_match_check(uid).equals(uid2))
		{
			/*Calendar current_time = Calendar.getInstance();
			if(current_time.get - start_time.getSeconds() > 300) {
				HashMap<String, Object> data = new HashMap<String, Object>();
				JsonResult result =  new JsonResult(ResponseType.MATCH_ERROR,data);
				sendResult(response, result);		
			}
			/*
			 * 放一个计时器 作为匹配用户时候的max time 超过time 报错send Response
			 * 
			 */
		}
		//若果uid在队列 0 ，1的位置，设置另一用户为
		uid2 = userList.user_match_check(uid);
		
		System.out.println(userList.get_UserList().toString());
		
//		String u1=String.valueOf(uid1);
//		String u2=String.valueOf(uid2);
//		System.out.println((uid1.compareTo(uid2)<0)+" "+uid1+" "+uid2);
		String room = (uid1.compareTo(uid2)<0) ? uid1+"_"+uid2: uid2+"_"+uid1;
		userList.user_off(uid);
		model.addAttribute("room", room);
		model.addAttribute("uid", uid);
		return "chat";
	}
	
//	@RequestMapping("/chat")
//	public String chat(HttpServletRequest request,Model model){
//		int room = Integer.parseInt(request.getParameter("room"));
//		int uid = Integer.parseInt(request.getParameter("uid"));
//		model.addAttribute("room", room);
//		model.addAttribute("uid", uid);
//		return "chat";
//	}
	
	@RequestMapping("/register")
	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String account  = request.getParameter("account");
		String password  = request.getParameter("password");
		String tel  = request.getParameter("tel");
		JsonResult result = registerService.register(account, password,tel);
		System.out.println(JSON.toJSONString(result));
		sendResult(response, result);
	}
	
	@RequestMapping("/login2")
	public String login2(HttpServletRequest request,Model model){
		String account  = request.getParameter("account");
		String password  = request.getParameter("password");
		JsonResult result = loginService.login(account, password);
		model.addAttribute("result", JSON.toJSONString(result));
		return "login2";
	}
	
	@RequestMapping("/info")
	public void info(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int uid = Integer.parseInt(request.getParameter("uid"));
		JsonResult result = initService.init(uid);
		sendResult(response, result);
	}
	
	@RequestMapping("/info2")
	public String info(HttpServletRequest request,Model model){
		int uid = Integer.parseInt(request.getParameter("uid"));
		JsonResult result = initService.init(uid);
		model.addAttribute("result", result);
		
		return "info2";
	}
	
	@RequestMapping("/login")
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String account  = request.getParameter("account");	
		String password  = request.getParameter("password");
		JsonResult result = loginService.login(account, password); 
		sendResult(response, result);	
	}
	
	@RequestMapping("/deleteFriend")
	public void deleteFriend(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int uid1 = Integer.parseInt(request.getParameter("uid1"));//uid1为当前用户uid
		int uid2 = Integer.parseInt(request.getParameter("uid2"));
		friendDelete.del_friend(uid1, uid2);
		JsonResult result = initService.init(uid1);
		sendResult(response, result);
	}
	
	public void sendResult(HttpServletResponse response,JsonResult result) throws IOException{
		response.setCharacterEncoding("UTF-8");
        //IJsonSerializer serializer = WebChatFactory.createSerializer();
        response.setHeader("Access-Control-Allow-Origin", "*");//允许跨域
        response.getWriter().write(JSON.toJSONString(result));
        //response.getWriter().write(serializer.toJSON(result));
	}
	

}
