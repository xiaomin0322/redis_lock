package com.zzm.redis;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class BaseSpringApp {
    public  static ApplicationContext                 context=null; 
    static{
        context= new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext-redis.xml" });
    }

}
