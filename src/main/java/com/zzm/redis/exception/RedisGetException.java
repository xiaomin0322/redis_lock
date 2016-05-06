package com.zzm.redis.exception;

public class RedisGetException extends Exception {
	public  static final String  baseretCd="000179";
	public static  final String  baseretCdParam="000279";

	private static final long serialVersionUID = 1L;
	
	private String retCd; // 异常对应的返回码
	private String msgDes; // 异常对应的描述信息

	public RedisGetException() {
		super();
	}

	public RedisGetException(String message) {
		super(message);
		msgDes = message;
	}

	public RedisGetException(String retCd, String msgDes) {
		super();
		this.retCd = retCd;
		this.msgDes = msgDes;
	}

	public String getRetCd() {
		return retCd;
	}

	public String getMsgDes() {
		return msgDes;
	}

}
