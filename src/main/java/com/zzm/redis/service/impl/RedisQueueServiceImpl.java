package com.zzm.redis.service.impl;
/*package com.artbulb.redis.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.artbulb.redis.dao.AbstractBaseRedisDao;

//@Service
public class RedisQueueService<T> extends  AbstractBaseRedisDao<String,T>{
	
	*//** 
     * 压栈 
     *  
     * @param key 
     * @param value 
     * @return 
     *//*  
    public Long leftPush(String key, T value) {  
        return redisTemplate.opsForList().leftPush(key, value);  
    }  
  
    *//** 
     * 出栈  出队 
     *  
     * @param key 
     * @return 
     *//*  
    public T pop(String key) {  
        return redisTemplate.opsForList().leftPop(key); 
    }  
  
    *//** 
     * 入队 
     * @param key 
     * @param value 
     * @return 
     *//*  
    public Long push(String key, T value) {  
        return redisTemplate.opsForList().rightPush(key, value);
    }  
  
    *//** 
     * 栈/队列长 
     *  
     * @param key 
     * @return 
     *//*  
    public Long length(String key) {  
        return redisTemplate.opsForList().size(key);  
    }  
  
    *//** 
     * 范围检索 
     *  
     * @param key 
     * @param start 
     * @param end 
     * @return 
     *//*  
    public List<T> range(String key, int start, int end) {  
        return redisTemplate.opsForList().range(key, start, end);  
    }  
  
    *//** 
     * 移除 
     *  
     * @param key 
     * @param i 
     * @param value 
     *//*  
    public void remove(String key, long i, String value) {  
    	redisTemplate.opsForList().remove(key, i, value);  
    }  
  
    *//** 
     * 检索 
     *  
     * @param key 
     * @param index 
     * @return 
     *//*  
    public T index(String key, long index) {  
        return redisTemplate.opsForList().index(key, index);  
    }  
  
    *//** 
     * 置值 
     *  
     * @param key 
     * @param index 
     * @param value 
     *//*  
    public void set(String key, long index, T value) {  
    	redisTemplate.opsForList().set(key, index, value);  
    }  
  
    *//** 
     * 裁剪 
     *  
     * @param key 
     * @param start 
     * @param end 
     *//*  
    public void trim(String key, long start, int end) {  
    	redisTemplate.opsForList().trim(key, start, end);  
    }  
}
*/