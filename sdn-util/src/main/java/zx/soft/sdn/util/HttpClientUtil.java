package zx.soft.sdn.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HTTP工具
 * 
 * @author xuran
 *
 */
public class HttpClientUtil {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	/**
	 * 超时时间5秒
	 */
	public static final int TIME_OUT = 5000;

	/**
	 * POST请求
	 * @param url 请求地址
	 * @param data 请求数据
	 * @param dataType xml,json
	 * @return	响应数据
	 */
	public static String doPost(String url, String requestData, String dataType) {
		StringBuffer result = new StringBuffer();
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		try {
			httpClient = HttpClients.createDefault();
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIME_OUT).setConnectTimeout(TIME_OUT)
					.build();
			httpPost = new HttpPost(url);
			StringEntity myEntity = new StringEntity(requestData, "UTF-8");
			httpPost.setConfig(requestConfig);
			//请求类型
			httpPost.addHeader("Content-Type", "application/" + dataType);
			//接收类型
			httpPost.addHeader("Accept", "application/" + dataType);
			httpPost.setEntity(myEntity);
			logger.info("request : [ url : {} data : {} ]", url, requestData);
			HttpResponse response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity resEntity = response.getEntity();
				InputStreamReader reader = new InputStreamReader(resEntity.getContent(), "UTF-8");
				char[] buff = new char[1024];
				int length = 0;
				while ((length = reader.read(buff)) != -1) {
					result.append(new String(buff, 0, length));
				}
				logger.info("response : [ url : {} data : {} ]", url, requestData);
			} else {
				result.append("fail");
				logger.error("Exception : {}", response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception : {}", ExceptionUtil.exceptionToString(e));
			result.append("fail");
		} finally {
			httpPost.abort();
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("Exception : {}", ExceptionUtil.exceptionToString(e));
			}
		}
		return result.toString();
	}

	/**
	 * GET请求
	 * @param url 请求地址
	 * @param param 请求数据
	 * @return 响应数据
	 */
	public static String doGet(String url, Map<String, String> data) {
		StringBuffer result = new StringBuffer();
		CloseableHttpClient httpClient = null;
		HttpGet httpGet = null;
		try {
			httpClient = HttpClients.createDefault();
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIME_OUT).setConnectTimeout(TIME_OUT)
					.build();
			if (null != data) {
				StringBuffer params = new StringBuffer("1=1");
				for (Iterator<Map.Entry<String, String>> it = data.entrySet().iterator(); it.hasNext();) {
					Map.Entry<String, String> entry = it.next();
					params.append("&" + entry.getKey() + "="
							+ (entry.getValue() != null ? URLEncoder.encode(entry.getValue(), "UTF-8") : ""));
				}
				httpGet = new HttpGet(url + params);
			} else {
				httpGet = new HttpGet(url);
			}
			httpGet.setConfig(requestConfig);
			logger.info("request : [ url : {} ]", url);
			HttpResponse response = httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity resEntity = response.getEntity();
				InputStreamReader reader = new InputStreamReader(resEntity.getContent(), "UTF-8");
				char[] buff = new char[1024];
				int length = 0;
				while ((length = reader.read(buff)) != -1) {
					result.append(new String(buff, 0, length));
				}
				logger.info("response : [ url : {} data : {} ]", url, result.toString());
			} else {
				result.append("fail");
				logger.error("Exception : {}", response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception : {}", ExceptionUtil.exceptionToString(e));
			result.append("fail");
		} finally {
			httpGet.abort();
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("Exception : {}", ExceptionUtil.exceptionToString(e));
			}
		}
		return result.toString();
	}

}
