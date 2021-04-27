package com.dt.tree.util;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;


/**
 * 请求工具
 * @author lixiang
 */
public class MyHttpRequestUtils {
	
	private static final Logger gaiaLogger = LoggerFactory.getLogger(MyHttpRequestUtils.class);
	
	/**
	 * 获取参数与值的JSON结果
	 * @param request 请求对象
	 * @return 参数与值的JSON结果
	 */
	public static JSONObject getJSONParameters(HttpServletRequest request) {
		
		JSONObject json = new JSONObject();
		Enumeration<String> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String parameterName = e.nextElement();
			json.put(parameterName, request.getParameter(parameterName));
		}
		
		return json;
	}
	
	/**
	 * 获取参数与值的Map结果
	 * @param request 请求对象
	 * @return 参数与值的Map结果
	 */
	public static Map<String, String> getMapParameters(HttpServletRequest request) {
		
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String parameterName = e.nextElement();
			map.put(parameterName, request.getParameter(parameterName));
		}
		
		return map;
	}
	
	/**
	 * 获取参数与值的Map结果
	 * @param jsonObj 请求jsonObj对象,必须是String:String格式
	 * @return 参数与值的Map结果
	 */
	public static Map<String, String> getMapParameters(JSONObject jsonObj) {
		
		Map<String, String> map = new HashMap<String, String>();
		Iterator<?> it = jsonObj.keys();
		while (it.hasNext()) {
			String key = (String)it.next();
			map.put(key, jsonObj.getString(key));
		}
		
		return map;
	}
	
	/**
	 * 给URL中的参数进行Encoder编码(已废弃)
	 * @param str 需要编码的字符串
	 * @return 编码后的url字符串
	 * @throws UnsupportedEncodingException 编码异常
	 */
	@Deprecated
	public static String getURLEncoderOld(String str) throws UnsupportedEncodingException {
		
		gaiaLogger.info("请求地址(解码前): " + str);
		str = URLDecoder.decode(str, "UTF-8");
		gaiaLogger.info("请求地址(解码后): " + str);
		
		gaiaLogger.info("请求地址(编码前): " + str);
		int index = str.indexOf("?");
		
		if (index < 0) { //没有参数就无需编码
			return str;
		}
		
		String url = str.substring(0, index);
		String params = str.substring(index + 1);
		
		params = getEncoderParams(params);
		params = params.replaceAll(" ", "%20");//以防地址里很多无用空格
//		
		String result = url + "?" + params;

		gaiaLogger.info("请求地址(编码后): " + result);
		return result;
    }
	
	/**
	 * 给URL中的参数进行Encoder编码
	 * @param str 需要编码的字符串
	 * @return 编码后的url字符串
	 * @throws UnsupportedEncodingException 编码异常
	 * @throws MalformedURLException 没有指定协议
	 * @throws URISyntaxException
	 */
	public static String getURLEncoder(String str) throws 
		UnsupportedEncodingException, MalformedURLException, URISyntaxException {
		
		gaiaLogger.info("请求地址(解码前): " + str);
		String decodeUrl = URLDecoder.decode(str, StandardCharsets.UTF_8.name());
		gaiaLogger.info("请求地址(解码后): " + decodeUrl);
			
		URL urlObj = new URL(decodeUrl);
		gaiaLogger.info("URL对象: " + urlObj);

	    URI uri = new URI(urlObj.getProtocol(), urlObj.getUserInfo(),
	    	urlObj.getHost(), urlObj.getPort(), urlObj.getPath(),
	    	urlObj.getQuery(), urlObj.getRef());
	        
	     String result = uri.toString();
		gaiaLogger.info("请求地址(构造URI对象编码后): " + result);
        
        return result;
    }
	
	/**
	 * 获取编码后的参数
	 * @param params get参数
	 * @return 编码后的get参数
	 * @throws UnsupportedEncodingException 编码异常
	 */
	private static String getEncoderParams(String params) throws UnsupportedEncodingException{
		
		StringBuffer sb = new StringBuffer();
		String[] pairs = params.split("&");
		
		for (int i = 0, len = pairs.length; i < len; i++) {
			
			int pos = pairs[i].indexOf("=");
			if (pos < 0) {
				continue;
			}
			
			String key = pairs[i].substring(0, pos);
			String value = pairs[i].substring(pos + 1);
	//		key = URLEncoder.encode(key, "UTF-8");//这里是否也需要编码
			value = URLEncoder.encode(value, "UTF-8");
			
			sb.append(key + "=" + value);
			
			if (i < len - 1) {
				sb.append("&");
			}
		}
		
		return sb.toString();
	}
}
