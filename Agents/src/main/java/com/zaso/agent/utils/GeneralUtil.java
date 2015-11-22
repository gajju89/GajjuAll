package com.zaso.agent.utils;

import java.util.UUID;

import redis.clients.jedis.Jedis;

public class GeneralUtil {
public static UUID  uuid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
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
}
