package zx.soft.sdn.cache.handle;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sdn.util.ExceptionUtil;
import zx.soft.sdn.util.JsoupUtil;

/**
 * 网页文件解析处理类
 * 
 * @author xuran
 *
 */
public class PageHandle {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(PageHandle.class);

	/**
	 * 输入流获转换成Map
	 * @param inputStream 输入流
	 * @return 成功返回Map ( title：网页标题 content：网页内容 )，失败返回null
	 */
	public static Map<String, String> parseMap(InputStream inputStream) {
		//读取输入流
		InputStreamReader reader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(reader);
		StringBuilder html = new StringBuilder();
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
		//将HTML还原成文档对象
		Document document = JsoupUtil.parseHtmlFromString(html.toString());
		//如果还原成功
		if (null != document) {
			//构建数据
			Map<String, String> pageInfo = new Hashtable<String, String>();
			pageInfo.put("title", document.title());
			pageInfo.put("content", document.text());
			return pageInfo;
		} else {
			return null;
		}
	}
}
