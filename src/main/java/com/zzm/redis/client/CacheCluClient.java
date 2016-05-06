package com.zzm.redis.client;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.stereotype.Component;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class CacheCluClient extends JedisCluster {

	public CacheCluClient(HostAndPort node) {
		super(node);
		// TODO Auto-generated constructor stub
	}


}
