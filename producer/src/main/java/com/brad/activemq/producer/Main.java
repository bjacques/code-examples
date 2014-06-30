package com.brad.activemq.producer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring.xml");
		MessageSender sender = (MessageSender) ac.getBean("messageSender");
		Map map = new HashMap();
		map.put("Name", "MYNAME");
		sender.send(map);
	}

}
