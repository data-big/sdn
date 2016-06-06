package zx.soft.sdn.api.domain;

/**
 * OpenTSDB PUT接口响应数据模型
 * 
 * @author xuran
 *
 */
public class TSDBPutResponse {

	/**成功个数**/
	private Integer success;
	/**失败个数**/
	private Integer failed;
	/**错误信息**/
	private String[] errors;

	public TSDBPutResponse() {
		super();
	}

	public TSDBPutResponse(Integer success, Integer failed, String[] errors) {
		super();
		this.success = success;
		this.failed = failed;
		this.errors = errors;
	}

	public Integer getSuccess() {
		return success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

	public Integer getFailed() {
		return failed;
	}

	public void setFailed(Integer failed) {
		this.failed = failed;
	}

	public String[] getErrors() {
		return errors;
	}

	public void setErrors(String[] errors) {
		this.errors = errors;
	}

}
