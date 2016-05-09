package zx.soft.sdn.api.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 自定义Jackson对象转换器
 *
 * @author xuran
 *
 */
public class CustomObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = -3341366863221844412L;

	/**
	 * 日期格式转换器
	 */
	public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);

	public CustomObjectMapper() {
		super();
		//覆盖日期格式转换器
		setDateFormat(dateFormat);
	}

}
