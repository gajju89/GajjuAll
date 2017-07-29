package com.zaso.agent.utils;

import java.util.Random;
import java.util.UUID;

import redis.clients.jedis.Jedis;

public class GeneralUtil {
public static UUID  uuid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");

public static String mixed="0123456789abqwertyuiopasdfghjklzxcvbnm@#$%&";
public static char[] mixedArray=mixed.toCharArray();
public static String uniqueNumber="";
static Random r=new Random();

public static String mixedOrder="0123456789";
public static char[] mixedOrderArray=mixedOrder.toCharArray();

public static String generateUUid()
{
	return uuid.randomUUID().toString();
}

public static String getLastUserId() {
	Jedis jedis = new Jedis("localhost");
	if (jedis.get("common") == null) {
		jedis.set("common", "1");
	} else {
		jedis.incr("common");
	}

	return jedis.get("common");
}

public static String generateAccessToken()
{
	return uuid.randomUUID().toString();
}

public static  String confirmationCode()
{
	uniqueNumber="";
	for(int i=0;i<6;i++)
	{
		char next=mixed.charAt((int)(mixed.length()*Math.random()));
		uniqueNumber=uniqueNumber+next;
		System.out.print(next);
	}
	return uniqueNumber;
	
	
}

 public static String uniqueOrder="";

public static  String generateOrderId()
{
	uniqueOrder="ej";
	for(int i=0;i<4;i++)
	{
		char next=mixedOrder.charAt((int)(mixedOrder.length()*Math.random()));
		uniqueOrder=uniqueOrder+next;
		System.out.print(next);
	}
	return uniqueOrder;
	
	
}
public static String uniqueOtp="";

public static  String generateOtp()
{
	uniqueOtp="";
	for(int i=0;i<6;i++)
	{
		char next=mixedOrder.charAt((int)(mixedOrder.length()*Math.random()));
		uniqueOtp=uniqueOtp+next;
		System.out.print(next);
	}
	return uniqueOtp;
	
	
}

}
