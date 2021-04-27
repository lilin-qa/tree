package com.dt.tree.util;



import  net.sf.json.JSONArray;

/**
 * HttpClient响应对象
 * @author lixiang
 */
public class HttpClientResponseObject {

	private int status;//响应状态码
	private long time;//响应时间
	private JSONArray heards;//响应头
	private String entity;//响应实体
	
	/**
	 * 构造方法
	 * @param status 响应状态码
	 * @param time 响应时间
	 * @param heards 请求头JSONArray
	 * @param entity 响应实体
	 */
	public HttpClientResponseObject(int status, long time, JSONArray heards, String entity) {
		
		this.status = status;
		this.time = time;
		this.heards = heards;
		this.entity = entity;
	}

	/**
	 * 获取响应状态码
	 * @return 响应状态码
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * 设置响应状态码
	 * @param status 响应状态码
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	/**
	 * 获取响应时间
	 * @return 响应时间
	 */
	public long getTime() {
		return time;
	}
	
	/**
	 * 设置响应时间
	 * @param  time 响应时间
	 */
	public void setTime(long time) {
		this.time = time;
	}
	
	/**
	 * 获取响应头
	 * @return 响应头
	 */
	public JSONArray getHeards() {
		return heards;
	}
	
	/**
	 * 设置响应头
	 * @param heards 响应头
	 */
	public void setHeards(JSONArray heards) {
		this.heards = heards;
	}
	
	/**
	 * 获取响应实体
	 * @return 响应实体
	 */
	public String getEntity() {
		return entity;
	}
	
	/**
	 * 设置响应实体
	 * @param entity 响应实体
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}
}
