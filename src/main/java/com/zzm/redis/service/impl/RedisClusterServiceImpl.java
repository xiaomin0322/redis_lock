package com.zzm.redis.service.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisCluster;

import com.zzm.redis.dao.JedisClusterDao;
import com.zzm.redis.exception.RedisException;
import com.zzm.redis.service.RedisService;

@Service
@SuppressWarnings("all")
public class RedisClusterServiceImpl extends JedisClusterDao implements
		RedisService{

	final static Logger logger = LoggerFactory
			.getLogger(RedisClusterServiceImpl.class);

	@PostConstruct
	public void init() {
		logger.debug("redis init");
	}

	@Override
	public String set(String key, Object value, Long liveTime) {
		return jset(key, value, liveTime);
	}

	@Override
	public Object get(String key) {
		return jget(key);
	}


	@Override
	public Long del(String... keys) {
		return jdel(keys);
	}

	@Override
	public Object hget(String key, String field) {
		return jhget(key, field);
	}


	@Override
	public Long hset(String key, String field, Object value) {
		return jhset(key, field, value);
	}

	@Override
	public boolean exists(String key) {
		try {
			return getObject().exists(key);
		} catch (Exception e) {
			logger.error("exists Exception",e);
		}
		return false;
	}

	@Override
	public long dbSize() {
		try {
			return getObject().dbSize();
		} catch (Exception e) {
			logger.error("exists Exception",e);
		}
		return 0;
	}

	@Override
	public <O> O get(String key, Class<O> t) {
		return (O) jget(key);
	}

	@Override
	public <O> O hget(String key,final String field, Class<O> t) {
		return (O)jhget(key, field);
	}

	@Override
	public Long hdel(String key, String field) {
		return jhdel(key, field);
	}

	@Override
	public String set(String key, Object value) {
		return set(key, value,null);
	}

	@Override
	public JedisCluster getCacheClient()  throws RedisException {   
		return clusterFactory.getObject();
	}
	
}
