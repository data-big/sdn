package zx.soft.sdn.api.component;

/**
 * 处理结果信息
 * 
 * @author xuran
 *
 */
public class HandleResult {

	/**错误码：0（处理成功 ）其它数字（处理异常）**/
	private final int errorCode;
	/**错误信息**/
	private final String errorMessage;

	public HandleResult(int errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
