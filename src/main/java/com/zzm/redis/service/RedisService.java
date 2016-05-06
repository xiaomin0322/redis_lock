package com.zzm.redis.service;

import redis.clients.jedis.JedisCluster;

import com.zzm.redis.exception.RedisException;


public interface RedisService {

	
	/**
	 * 新增缓存方法
	 * @param key
	 * @param value
	 * @return 从 Redis 2.6.12 版本开始， SET 在设置操作成功完成时，才返回 OK 。
                 如果设置了 NX 或者 XX ，但因为条件没达到而造成设置操作未执行，那么命令返回空批量回复（NULL Bulk Reply）。
	 */
	public String set(final String key, final Object value);
	
	
	/**
	 * 新增缓存方法
	 * @param key
	 * @param value
	 * @param liveTime 超时时间 小于等于0 则永久存储
	 * @return 从 Redis 2.6.12 版本开始， SET 在设置操作成功完成时，才返回 OK 。
                 如果设置了 NX 或者 XX ，但因为条件没达到而造成设置操作未执行，那么命令返回空批量回复（NULL Bulk Reply）。
	 */
	public String set(final String key, final Object value, final Long liveTime);

	/**
	 * 根据key 获取value
	 * @param key
	 * @return Object
	 */
	public Object get(final String key);
	

	/**
	 * 根据key 获取value
	 * @param key
	 * @return O
	 */
	public <O> O  get(final String key,Class<O> t);
	
	/**
	 * 删除keys
	 * @param keys
	 * @return 被删除 key 的数量
	 */
	public Long del(final String... keys);

	/**
	 * 根据 key 获取  hash field 字段值
	 * @param key
	 * @param field
	 * @return Object
	 */
	public Object hget(final String key, final String field);
	
	/**
	 * 根据 key 获取  hash field 字段值
	 * @param key
	 * @param field
	 * @return O
	 */
	public <O> O  hget(final String key,final String field,Class<O> t);
	
	
	/**
	 * 根据 key 设置  hash field 字段值
	 * @param key
	 * @param field
	 * @return Long
	 */
	public Long hset(final String key, final String field, final Object value);
	
	
	/**
	 * 删除keys hash 中 字段的值
	 * @param keys
	 * @param field
	 * @return 被成功移除的域的数量，不包括被忽略的域。
	 */
	public Long hdel(final String key, final String field) ;

	/**
	 * 是否存在
	 * @param key
	 * @return true 为空  false 有值
	 */
	boolean exists(String key);
	
	/**
	 * 获取dbsize
	 * @return long
	 */
	@Deprecated
	public long dbSize();
	
	
	public JedisCluster getCacheClient() throws RedisException;


}
