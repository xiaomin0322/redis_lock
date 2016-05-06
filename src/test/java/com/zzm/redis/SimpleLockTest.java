package com.zzm.redis;

import java.util.concurrent.Callable;

import com.zzm.redis.exception.RedisException;
import com.zzm.redis.factory.JedisClusterFactory;
import com.zzm.redis.utils.JedisLock;
import com.zzm.redis.utils.JedisSimpleLock;

/**
 * @author zzm
*/
public class SimpleLockTest extends BaseSpringApp {
	final static JedisClusterFactory clusterFactory = context.getBean(JedisClusterFactory.class);
	
	static ForkJoinPool<Boolean> forkJoinPool = new ForkJoinPool<Boolean>(100);
	
    public static void main(String[] args)throws Exception {
        
        final String key = "test";
        
        for(int i=0;i<500;i++){
        	forkJoinPool.addTask(new Callable<Boolean>() {
				
				@Override
				public Boolean call() throws Exception {
					 JedisSimpleLock lock=null;
						try {
							lock = new JedisSimpleLock(key,8000,800,clusterFactory.getObject());
						} catch (RedisException e1) {
							e1.printStackTrace();
						}
	        			 lock.wrap(new Runnable() {
	        	                @Override
	        	                public void run() {
	        	                	try {
	        	                		System.out.println("start》》》》》》》》》》》》》》》》》》》》");
	        	    					clusterFactory.getObject().set("aaa", "11111");
	        	    					System.out.println("end《《《《《《《《《《《《《《《《《《《《《《");
	        	    				} catch (RedisException e) {
	        	    					e.printStackTrace();
	        	    				}
	        	                }
	        	            });
					return null;
				}
			});
        			
        	
        }
        
       forkJoinPool.executeTask();
       
       System.out.println(JedisLock.map);
        
       
        
        
    }

}
