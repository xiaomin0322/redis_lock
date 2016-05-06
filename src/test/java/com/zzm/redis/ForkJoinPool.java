package com.zzm.redis;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class ForkJoinPool<T> {

	private final static Logger logger = LoggerFactory
			.getLogger(ForkJoinPool.class);

	public static final int AVAILABLE_PROCESSORS_SIZE = Runtime.getRuntime()
			.availableProcessors();

	private ListeningExecutorService executorService = null;

	private ThreadLocal<List<ListenableFuture<T>>> futuresThreadLocal = new ThreadLocal<List<ListenableFuture<T>>>(){
		protected java.util.List<com.google.common.util.concurrent.ListenableFuture<T>> initialValue() {
			 return Lists.newArrayList();
		};
	};

	public ForkJoinPool() {
		this(AVAILABLE_PROCESSORS_SIZE*2);
	}

	public ForkJoinPool(int poolSize) {
		executorService = MoreExecutors
				.listeningDecorator(Executors
						.newFixedThreadPool(poolSize));
	}

	public void createTask() {
	}
	
	
	/**
	 * 
	 * @description
	 * @return ListenableFuture<T>
	 * @Exception
	 */
	public ForkJoinPool<T> addTaskList(final List<Callable<T>> callables) {
		if(callables!=null){
			for(Callable<T> c:callables){
				addTask(c);
			}
		}
		return this;
	}

	/**
	 * 
	 * @description
	 * @return ListenableFuture<T>
	 * @Exception
	 */
	public ForkJoinPool<T> addTask(final Callable<T> callable) {
		ListenableFuture<T> listenableFuture = executorService.submit(callable);
		futuresThreadLocal.get().add(listenableFuture);
		return this;
	}

	/**
	 * 多线程执行商品生成信息
	 * 
	 * @description
	 * @return
	 * @Exception
	 */
	public List<T> executeTask(List<ListenableFuture<T>> futures) {
		long gstartTime = System.currentTimeMillis();
		ListenableFuture<List<T>> successfulQueries = Futures
				.successfulAsList(futures);
		try {
			// 获取所有线程的执行结果
			List<T> lists = successfulQueries.get();
			return lists;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} 
		logger.info(" executeTask ! cost time:"
				+ (System.currentTimeMillis() - gstartTime));

		return null;
	}
	
	/**
	 * 多线程执行商品生成信息
	 * 
	 * @description
	 * @return
	 * @Exception
	 */
	public List<T> executeTask() {
		List<ListenableFuture<T>> futures = futuresThreadLocal.get();
		try {
			return executeTask(futures);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			futuresThreadLocal.remove();
		}
		return null;
	}
	
	
	
	/**
	 * 拆分任务
	 * 
	 * @param tasks
	 * @param 拆分数量
	 * @return
	 */
	public static <T> List<T> mergeTask(List<List<T>> tasks) {
		if(tasks==null){
			return null;
		}
		List<T> list = Lists.newArrayList();
		for(List<T> l:tasks){
			if(l!=null){
				list.addAll(l);
			}
		}
		return list;
	}

	/**
	 * 拆分任务
	 * 
	 * @param tasks
	 * @param 拆分数量
	 * @return
	 */
	public static <T> List<List<T>> splitTask(List<T> tasks, Integer taskSize) {
		List<List<T>> list = Lists.newArrayList();
        if(tasks==null || taskSize <= 0){
			return list;
		} 
        if(tasks.size() < taskSize){
        	list.add(tasks);
        	return list;
        }
         
        
		int baseNum = tasks.size() / taskSize; // 每个list的最小size
		int remNum = tasks.size() % taskSize; // 得到余数

		int index = 0;
		for (int i = 0; i < taskSize; i++) {
			int arrNum = baseNum; // 每个list对应的size
			if (i < remNum) {
				arrNum += 1;
			}
			List<T> ls = Lists.newArrayList();
			for (int j = index; j < arrNum + index; j++) {
				ls.add(tasks.get(j));
			}
			list.add(ls);
			index += arrNum;
		}
		return list;
	}


	public void shutdown() {
		this.executorService.shutdown();
	}
}
