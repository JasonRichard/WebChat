package com.lwj.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lwj.util.pojo.JsonResult;
import com.lwj.service.*;

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
	
	@RequestMapping("/chat")
	public String chat(HttpServletRequest request,Model model){
		int room = Integer.parseInt(request.getParameter("room"));
		int uid = Integer.parseInt(request.getParameter("uid"));
		model.addAttribute("room", room);
		model.addAttribute("uid", uid);
		return "chat";
	}
	
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
		model.addAttribute("result", JSON.toJSONString(result));
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
		System.out.println(JSON.toJSONString(result));
		sendResult(response, result);
	}
	
	public void sendResult(HttpServletResponse response,JsonResult result) throws IOException{
		response.setCharacterEncoding("UTF-8");
        //IJsonSerializer serializer = WebChatFactory.createSerializer();
        response.setHeader("Access-Control-Allow-Origin", "*");//允许跨域
        //response.getWriter().write(serializer.toJSON(result));
	}
	

}
