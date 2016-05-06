package com.zzm.redis;

import com.zzm.redis.service.RedisService;

public class RedisTest extends BaseSpringApp {
	// @Resource
	// JedisClusterFactory jedisClusterFactory;
	public static void main(String[] args) throws Exception {
		RedisService clusterFactory = context.getBean(RedisService.class);

		clusterFactory.hset("H_NAME","111aaa", new User());
		
		User user=clusterFactory.hget("H_NAME","111aaa",User.class);
		
		
		System.out.println("==="+user.name);
		
		System.out.println(clusterFactory.set("111dsda", "123", 132l));

	}

}
//