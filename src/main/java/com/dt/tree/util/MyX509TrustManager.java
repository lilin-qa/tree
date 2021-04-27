package com.dt.tree.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * 实现自带的X509TrustManager
 * @author LiXiang
 */
public class MyX509TrustManager implements X509TrustManager {

	@Override
	public void checkClientTrusted(X509Certificate[] chain, String anthType) throws CertificateException{}
	
	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException{}
	
	@Override
	public X509Certificate[] getAcceptedIssuers(){
		return null;
	}
}

