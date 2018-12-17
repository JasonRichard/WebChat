package com.lwj.util.enums;

public enum ResponseType {
	LOGIN_SUCESS(0,"登陆成功"),
	LOGIN_WRONG(10,"用户名或密码错误"),
	REGISTER_WRONG(1,"账号已存在"),
	REGISTER_SUCESS(11,"注册成功"),
	OPERATE_DONE(2,"操作完成"),
	MSG_GET(3,"得到聊天信息"),
	MATCH_ERROR(12,"匹配超时"),
	MATCH_SUCESS(20,"匹配成功"),
	
	INFO_GET(50,"得到所有信息");
	
	String msg;
	Integer code;
	
	private ResponseType(Integer code,String msg){
        this.msg = msg;
        this.code = code;
    }
	
	public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
  
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
	
}
