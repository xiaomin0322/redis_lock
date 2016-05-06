package com.zzm.redis.dao;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.JedisCluster;

import com.zzm.redis.exception.RedisException;
import com.zzm.redis.factory.JedisClusterFactory;
import com.zzm.redis.service.impl.RedisClusterServiceImpl;
import com.zzm.redis.utils.ByteCvtUtils;

@Repository
@SuppressWarnings("all")
public class JedisClusterDao {

	final static Logger logger = LoggerFactory
			.getLogger(RedisClusterServiceImpl.class);

	@Resource
	public JedisClusterFactory clusterFactory;

	public JedisCluster getObject() throws RedisException {
		return clusterFactory.getObject();
	}
	public JedisCluster getJedisClient() throws RedisException {
		return clusterFactory.getObject();
	}

	public String jset(final String key, final Object value, final Long liveTime) {
		String rs = null;
		try {
			rs = getJedisClient().set(key.getBytes(),
					ByteCvtUtils.ObjectToByte(value));
			if (liveTime != null && liveTime > 0) {
				clusterFactory.getObject().expire(key, liveTime.intValue());
			}
		} catch (RedisException e) {
			logger.error("set RedisException", e);
		}
		return rs;
	}

	public Object jget(final String key) {
		try {
			byte[] b = getJedisClient().get(key.getBytes());
			if (b == null)
				return null;
			return ByteCvtUtils.ByteToObject(b);
		} catch (RedisException e) {
			logger.error("get RedisException", e);
		}
		return null;
	}

	public long jdel(final String... keys) {
		logger.debug("public long del(final String... keys):" + keys);
		long result = 0;
		for (int i = 0; i < keys.length; i++) {
			try {
				result +=getJedisClient().del(keys[i].getBytes());
				logger.debug("public long del(final String... keys) result:"
						+ result);
			} catch (RedisException e) {
				logger.error("del RedisException", e);
			}
		}
		return result;
	}

	public Object jhget(final String key, final String field) {
		try {
			byte[] b = getJedisClient().hget(key.getBytes(),
					field.getBytes());
			if (b == null)
				return null;
			return ByteCvtUtils.ByteToObject(b);
		} catch (RedisException e) {
			logger.error("hget RedisException", e);
		}
		return null;
	}

	public Long jhset(final String key, final String field, final Object value) {
		try {
			return getJedisClient().hset(key.getBytes(), field.getBytes(),
					ByteCvtUtils.ObjectToByte(value));
		} catch (RedisException e) {
			logger.error("hset RedisException", e);
		}
		return null;
	}

	public Long jhdel(final String key, final String field) {
		try {
			logger.debug(" key:" + key + "  field  " + field);
			return getJedisClient().hdel(key, key);
		} catch (RedisException e) {
			logger.error("REdisRedisException", e);
		}
	    return null;
	}

}
