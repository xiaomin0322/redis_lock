package com.zzm.redis.factory;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import com.zzm.redis.exception.RedisException;
import com.zzm.redis.service.impl.RedisClusterServiceImpl;
import com.zzm.redis.utils.ByteCvtUtils;

@SuppressWarnings("all")
public class JedisClusterFactory implements FactoryBean<JedisCluster>,
		InitializingBean {

	final static Logger logger = LoggerFactory
			.getLogger(RedisClusterServiceImpl.class);

	private Resource addressConfig;
	private String addressKeyPrefix;

	private JedisCluster jedisCluster;
	private Integer timeout;
	private Integer maxRedirections;
	private GenericObjectPoolConfig genericObjectPoolConfig;

	private Pattern p = Pattern.compile("^.+[:]\\d{1,5}\\s*$");

	@Override
	public JedisCluster getObject() throws RedisException {
		return jedisCluster;
	}

	@Override
	public Class<? extends JedisCluster> getObjectType() {
		return (this.jedisCluster != null ? this.jedisCluster.getClass()
				: JedisCluster.class);
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public void hset(final String key, final String field, final Object value) {
		jedisCluster.hset(key.getBytes(), field.getBytes(),
				ByteCvtUtils.ObjectToByte(value));
	}

	public boolean hdel(final String key, final String field) {
		try {
			logger.debug(" key:" + key + "  field  " + field);
			jedisCluster.hdel(key, key);
			return true;
		} catch (Exception e) {
			logger.error("REdisRedisException", e);
			return false;
		}
	}

	private Set<HostAndPort> parseHostAndPort() throws RedisException {
		try {
			Properties prop = new Properties();
			prop.load(this.addressConfig.getInputStream());

			Set<HostAndPort> haps = new HashSet<HostAndPort>();
			for (Object key : prop.keySet()) {

				if (!((String) key).startsWith(addressKeyPrefix)) {
					continue;
				}

				String val = (String) prop.get(key);

				boolean isIpPort = p.matcher(val).matches();

				if (!isIpPort) {
					throw new IllegalArgumentException("ip 或 port 不合法");
				}
				String[] ipAndPort = val.split(":");

				HostAndPort hap = new HostAndPort(ipAndPort[0],
						Integer.parseInt(ipAndPort[1]));
				haps.add(hap);
			}

			return haps;
		} catch (Exception ex) {
			logger.error("解析 jedis 配置文件失败", ex);
			throw  new RedisException("解析 jedis 配置文件失败");
		}
	}

	@Override
	public void afterPropertiesSet() throws RedisException {
		Set<HostAndPort> haps = this.parseHostAndPort();
		jedisCluster = new JedisCluster(haps, timeout, maxRedirections,
				genericObjectPoolConfig);

	}

	public void setAddressConfig(Resource addressConfig) {
		this.addressConfig = addressConfig;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void setMaxRedirections(int maxRedirections) {
		this.maxRedirections = maxRedirections;
	}

	public void setAddressKeyPrefix(String addressKeyPrefix) {
		this.addressKeyPrefix = addressKeyPrefix;
	}

	public void setGenericObjectPoolConfig(
			GenericObjectPoolConfig genericObjectPoolConfig) {
		this.genericObjectPoolConfig = genericObjectPoolConfig;
	}

}
