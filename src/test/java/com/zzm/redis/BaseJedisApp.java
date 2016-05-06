package com.zzm.redis;

import redis.clients.jedis.JedisCluster;

import com.zzm.redis.factory.JedisClusterFactory;

public class BaseJedisApp extends BaseSpringApp {
	final static JedisClusterFactory clusterFactory = context.getBean(JedisClusterFactory.class);

	public static void main(String[] args) throws Exception {
		
		//RedisClusterService clu= context.getBean(RedisClusterService.class);
		//   System.out.println(clu.jhset("a", "b", "c"));
       	JedisCluster jedisCluster = clusterFactory.getObject();
       	jedisCluster.hset("a", "goods", "c");
//		jedisCluster.getClusterNodes()
//		Jedis jedis = new Jedis("127.0.0.1", 7000);//
//		System.out.println(jedis.pipelined());
//	//	jedisCluster.incrBy(ByteCvtUtils.ObjectToByte("user_121"), -10);
		//jedisCluster.incrBy("user_121",-10);
		 // System.out.println(jedisCluster.hkeys("user_*"));
	//	jedisCluster.incr("user_13");
//		   jedisCluster.set("aaa_123","11111");
//		   System.out.println(jedisCluster.get("user_121"));
//		  System.out.println(jedisCluster.eval("return redis.call('keys','user_*')","1"));
//		  System.out.println(jedisCluster.get("user_13"));
//		
//		
//		  System.out.println(jedisCluster.eval("return redis.call('keys','user_*')","null"));
//		  System.out.println(jedisCluster.get("user_14"));
//		  System.out.println(jedisCluster.get("user_18"));
//		  System.out.println(jedisCluster.eval("return redis.call('keys','a*')","1"));
//		  System.out.println(jedisCluster.eval("return redis.call('keys','a*')","8"));
//		  System.out.println(jedisCluster.eval("return redis.call('keys','a*')","3"));
//		  System.out.println(jedisCluster.eval("return redis.pcall('keys','a*')","0"));
	//	System.out.println(jedisCluster.get("user_12"));
		// System.out.println(jedisCluster.hvals("user_1"));
//		for (int i = 0; i < 100; i++) {
//						executorServiceLocal.execute(new Thread() {
//							@Override
//							public void run() {
//								JedisCluster jedisCluster;
//								try {
//									jedisCluster = clusterFactory.getObject();
//									jedisCluster.incrBy("user_18", 1);
//								} catch (Exception e) {
//									e.printStackTrace();
//								}
//							}
//						});
//		}
		//jedisCluster.incrBy("user_14", 1000);
//		System.out.println(jedisCluster.get("user_18"));
//		System.out.println(jedisCluster.getSet("user_18", "137"));
//		System.out.println(jedisCluster.get("user_18"));
//		jedisCluster.mset("a1","a1value","b1","b1value");
//		System.out.println(jedisCluster.mget("a1"));
//		System.out.println(jedisCluster.mget("a1value"));
//		System.out.println(jedisCluster.mget("ab1"));
//		System.out.println(jedisCluster.mget("b1value"));
		// System.out.println(jedisCluster);
//	   List<String>  lst=Lists.newArrayList();
//	   lst.add("1");
//	   lst.add("2");
//	   lst.add("3");
//	   lst.add("4");
//	   lst.add("5");
//	   jedisCluster.set(ByteCvtUtils.ObjectToByte("lst"), ByteCvtUtils.ObjectToByte(lst));
//	   List<String>  lstResult =(List<String>) ByteCvtUtils.ByteToObject(jedisCluster.get(ByteCvtUtils.ObjectToByte("lst")));
//	   System.out.println(lstResult);
	   
	   //jedisCluster.rpush("lst", lst.toArray().);
	   
	}
}
