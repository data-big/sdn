package zx.soft.sdn.cache.handle;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sdn.util.ExceptionUtil;
import zx.soft.sdn.util.IDUtil;
import zx.soft.sdn.util.JsonUtil;
import zx.soft.sdn.util.JsoupUtil;
import zx.soft.sdn.util.RedisUtil;

/**
 * 网页文件缓存处理类
 * 
 * @author xuran
 *
 */
public class PageCacheHandle {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(PageCacheHandle.class);

	/**
	 * 根据输入流缓存网页信息
	 * @param inputStream 输入流
	 * @return 成功 OR 失败
	 */
	public boolean cachePageInfoToRedis(InputStream inputStream) {
		//将输入流还原成HTML
		String html = this.parseHTMLFromStream(inputStream);
		//如果还原成功
		if (null != html) {
			//将HTML还原成文档对象
			Document document = JsoupUtil.parseHtmlFromString(html);
			//如果还原成功
			if (null != document) {
				//获取网页标题
				String pageTitle = document.title();
				//获取网页内容
				String pageContent = document.text();
				//构建JSON数据模型
				Map<String, String> page = new HashMap<String, String>();
				page.put("id", IDUtil.generateUniqueID());
				page.put("username", null);
				page.put("title", pageTitle);
				page.put("content", pageContent);
				page.put("timestamp", null);
				page.put("ip", null);
				page.put("identity_id", null);
				page.put("card_num", null);
				//生成JSON数据
				String json = JsonUtil.parseString(page);
				//写入Redis并返回
				return RedisUtil.getInstance().sadd("sdn.cache.pages", json) == 1 ? true : false;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 将输入流还原成HTML
	 * @param inputStream 输入流
	 * @return 成功返回html字符串，失败返回null
	 */
	private String parseHTMLFromStream(InputStream inputStream) {
		InputStreamReader reader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(reader);
		StringBuffer html = new StringBuffer();
		try {
			String lineText = null;
			while (null != (lineText = bufferedReader.readLine())) {
				html.append(lineText);
			}
			reader.close();
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception : {}", ExceptionUtil.exceptionToString(e));
			return null;
		}
		return html.toString();
	}
}
