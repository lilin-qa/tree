package com.dt.tree.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Header;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dt.tree.util.HttpClientResponseObject;
import net.sf.json.JSONArray;

import net.sf.json.JSONObject;

public class MyHttpClientUtils {
	
	private static final Logger gaiaLogger = LoggerFactory.getLogger(MyHttpClientUtils.class);
	private static RequestConfig requestConfig = null;
	private static final int timeout = 10000; //请求和传输超时时间
	
	static {
		//设置请求和传输超时时间
		requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
	}

	/**
	 * 创建可使用https协议的HttpClient对象
	 * @return 返回可使用https协议的HttpClient对象
	 */
	private static HttpClient SSLClient(){
		
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new MyX509TrustManager();
			ctx.init(null,new TrustManager[]{tm}, null);
			SSLConnectionSocketFactory ssf = new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);
			CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(ssf).build();
			return httpClient;
		}
		catch (NoSuchAlgorithmException ex){
			gaiaLogger.error("没有这样的算法异常", ex);
		}
		catch (KeyManagementException ex){
			gaiaLogger.error("密钥管理异常", ex);
		}
		
		return HttpClients.createDefault();
	}
	
	/**
	 * 获取HttpClient
	 * @param requestAddress 请求地址
	 * @return 根据请求协议返回HttpClient对象
	 */
	private static HttpClient getHttpClient(String requestAddress){
		
		CloseableHttpClient httpClient = null;
		
		//截取请求地址中的协议
		gaiaLogger.info("requestAddress: " + requestAddress);
		int end = requestAddress.indexOf(":") == -1 ? 0 : requestAddress.indexOf(":");
		String protocol = requestAddress.substring(0, end);
		
		if ("https".equalsIgnoreCase(protocol)){
			httpClient = (CloseableHttpClient)SSLClient();
		}
		else {
			httpClient = HttpClients.createDefault();
		}
		
		return httpClient;
	}
	
	/**
	 * 发送post请求
	 * @param requestAddress 请求地址
	 * @param parameter 请求参数
	 * @param requestHeadersJSONArr 请求头JSON数组
	 * @param encoding 编码字符集
	 * @return 响应对象 
	 * @throws UnsupportedEncodingException 编码异常
	 * @throws ClientProtocolException 客户端协议异常
	 * @throws MalformedURLException 没有指定协议
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws IllegalArgumentException 请求地址异常
	 */
	public static HttpClientResponseObject sendPost(String requestAddress, String parameter, 
			JSONArray requestHeadersJSONArr, String encoding) 
					throws UnsupportedEncodingException, ClientProtocolException, MalformedURLException, URISyntaxException, 
					IOException, IllegalArgumentException {
		
		gaiaLogger.info("post请求地址: " + requestAddress);
		//获取响应结果
		HttpClientResponseObject httpClientResponseObject = httpPostRequest(requestAddress, parameter, 
				requestHeadersJSONArr, encoding);
		return httpClientResponseObject;
	}
	
	/**
	 * 发送get请求
	 * @param url url地址
	 * @param path 路径
	 * @param parameter 参数
	 * @param requestHeadersJSONArr 请求头JSON数组
	 * @param encoding 编码字符集
	 * @return httpClient响应对象
	 * @throws UnsupportedEncodingException 编码异常
	 * @throws ClientProtocolException 客户端协议异常
	 * @throws MalformedURLException 没有指定协议
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws IllegalArgumentException 请求地址异常
	 */
	public static HttpClientResponseObject sendGet(String url, String path, String parameter, 
			JSONArray requestHeadersJSONArr, String encoding) 
					throws UnsupportedEncodingException, ClientProtocolException, MalformedURLException, URISyntaxException, 
					IOException, IllegalArgumentException {

		//解析请求地址
		String requestAddress = getRequestAddress(url, path, parameter);
		gaiaLogger.info("get请求地址: " + requestAddress);

		//获取响应内容
		HttpClientResponseObject httpClientResponseObject = httpGetRequest(requestAddress, requestHeadersJSONArr, encoding);
		return httpClientResponseObject;
	}
	
	/**
	 * 获取请求地址
	 * @param url url地址
	 * @param path 路径
	 * @param getParameter get参数
	 * @return 请求地址
	 */
	public static String getRequestAddress(String url, String path, String getParameter) {
		
		if (getParameter != null && !"".equals(getParameter)){
			getParameter = "?" + getParameter;
		}
		getParameter = getParameter == null ? "" : getParameter;
		
		path = path == null ? "" : path;
		//解析请求地址
		String requestAddress = url + path + getParameter;
		gaiaLogger.info("拼装get请求地址: " + requestAddress);
		
		return requestAddress;
	}
	
	/**
	 * http get请求
	 * @param requestAddress 请求地址
	 * @param requestHeadersJSONArr 请求头JSON数组
	 * @param encoding 编码字符集
	 * @return httpClient响应对象 
	 * @throws UnsupportedEncodingException 编码异常
	 * @throws MalformedURLException 没有指定协议
	 * @throws URISyntaxException
	 * @throws ClientProtocolException 客户端协议异常
	 * @throws IOException
	 * @throws IllegalArgumentException 请求地址异常
	 */ 
	private static HttpClientResponseObject httpGetRequest(String requestAddress, 
			JSONArray requestHeadersJSONArr, String encoding) 
				throws UnsupportedEncodingException, ClientProtocolException, MalformedURLException, URISyntaxException, 
				IOException, IllegalArgumentException{
		
		//请求地址编码
		requestAddress = MyHttpRequestUtils.getURLEncoder(requestAddress);
		
		CloseableHttpClient httpClient = (CloseableHttpClient)getHttpClient(requestAddress);
		
		HttpGet httpGet = new HttpGet(requestAddress);
		
		httpGet.setConfig(requestConfig);
		
		if (requestHeadersJSONArr != null) {
			//请求头
			for(int i = 0, len = requestHeadersJSONArr.size(); i < len; i++) {
				JSONObject json = requestHeadersJSONArr.getJSONObject(i);
				httpGet.addHeader(json.getString("KEY"), json.getString("VALUE"));
			}
		}
		
		CloseableHttpResponse response = null;
		HttpClientResponseObject httpClientResponseObject = null;
		try {
			long begin_time = System.currentTimeMillis();; //System.currentTimeMillis();
			response = httpClient.execute(httpGet);
			long end_time = System.currentTimeMillis();;
			long cost_time = (end_time - begin_time);

			//响应码
			int status = response.getStatusLine().getStatusCode();
			//响应头
			Header[] heards = response.getAllHeaders();
			JSONArray jsonArrHeards = new JSONArray();
			for (int i = 0, len = heards.length; i < len; i++) {
				
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("name", heards[i].getName());
				jsonObj.put("value", heards[i].getValue());
				jsonArrHeards.add(jsonObj);
			}
			//响应实体
			String entity = EntityUtils.toString(response.getEntity(), encoding);
			gaiaLogger.info("响应实体: " + entity);
			httpClientResponseObject = new HttpClientResponseObject(status, cost_time, jsonArrHeards, entity);
		}
		finally{
			httpGet.releaseConnection();
        }
		
		return httpClientResponseObject;
	}
	
	/**
	 * http post请求
	 * @param requestAddress 请求地址
	 * @param parameter 请求参数
	 * @param requestHeadersJSONArr 请求头JSON数组
	 * @param encoding 编码字符集
	 * @return 响应对象
	 * @throws UnsupportedEncodingException 编码异常
	 * @throws MalformedURLException 没有指定协议
	 * @throws URISyntaxException
	 * @throws ClientProtocolException 客户端协议异常
	 * @throws IOException
	 * @throws IllegalArgumentException 请求地址异常
	 */
	private static HttpClientResponseObject httpPostRequest(String requestAddress, String parameter, 
			JSONArray requestHeadersJSONArr, String encoding) 
					throws UnsupportedEncodingException, ClientProtocolException, MalformedURLException, URISyntaxException, 
					IOException, IllegalArgumentException {
		
		//请求地址编码
		requestAddress = MyHttpRequestUtils.getURLEncoder(requestAddress);
		
		CloseableHttpClient httpClient = (CloseableHttpClient)getHttpClient(requestAddress);
		HttpPost httpPost = new HttpPost(requestAddress);
		httpPost.setConfig(requestConfig);

		if (requestHeadersJSONArr != null) {
			//请求头
			for(int i = 0, len = requestHeadersJSONArr.size(); i < len; i++) {
				JSONObject json = requestHeadersJSONArr.getJSONObject(i);
				httpPost.addHeader(json.getString("KEY"), json.getString("VALUE"));
			}
		}
		
		if (parameter != null){
			StringEntity entity = new StringEntity(parameter, encoding);
			entity.setContentEncoding(encoding);
//			entity.setContentType(contentType); //可能会出现问题
			httpPost.setEntity(entity);
		}
		
		HttpClientResponseObject httpClientResponseObject = null;
		try {
			long begin_time = System.currentTimeMillis();
			CloseableHttpResponse response = httpClient.execute(httpPost);
			long end_time = System.currentTimeMillis();// SystemClock.millisClock().now();
			long cost_time = (end_time - begin_time);
			
			//响应码
			int status = response.getStatusLine().getStatusCode();
			//响应头
			Header[] heards = response.getAllHeaders();
			JSONArray jsonArrHeards = new JSONArray();
			for (int i = 0, len = heards.length; i < len; i++) {
				
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("name", heards[i].getName());
				jsonObj.put("value", heards[i].getValue());
				jsonArrHeards.add(jsonObj);
			}
			//响应实体
			String entity = EntityUtils.toString(response.getEntity(), encoding);
			gaiaLogger.info("响应实体: " + entity);
			httpClientResponseObject = new HttpClientResponseObject(status, cost_time, jsonArrHeards, entity);
		}
		finally{
			httpPost.releaseConnection();
        }
		
		return httpClientResponseObject;
	}
}

