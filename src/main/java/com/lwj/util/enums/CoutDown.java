package com.lwj.util.enums;

public class CoutDown {
	//一 用于聊天页面倒计时
	public static void countdown(int seconds) {
		//System.err.println("倒计时" + seconds + "秒,倒计时开始:");
		int i = seconds;
		while (i > 0) {
		//System.err.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
		// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i--;
		}
		
		//System.err.println(i);
		//System.err.println("倒计时结束");
	}
	//用于创建匹配时倒计时
	
}
