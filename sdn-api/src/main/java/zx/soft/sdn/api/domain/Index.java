package zx.soft.sdn.api.domain;

/**
 * 索引信息模型
 * 
 * @author xuran
 *
 */
public class Index {

	/**唯一标识**/
	private String id;
	/**用户姓名**/
	private String username;
	/**用户身份证号**/
	private String identity_id;
	/**用户手机号码**/
	private String phone_num;
	/**用户VPN卡卡号**/
	private String ICCID;
	/**时间：yyyy-MM-dd HH:mm:ss**/
	private String timestamp;
	/**五元组信息 源IP**/
	private String src_ip;
	/**五元组信息 目的IP**/
	private String des_ip;
	/**五元组信息 源端口**/
	private String src_port;
	/**五元组信息 目的端口**/
	private String des_port;
	/**五元组信息 协议类型**/
	private String protocol_type;
	/**头信息**/
	private String header;
	/**网址**/
	private String url;
	/**流量信息**/
	private String flow_type;
	/**资源类型**/
	private String resource_type;
	/**域名**/
	private String domain_name;
	/**响应字节码大小**/
	private String size;
	/**页面内容**/
	private String content;
	/**页面标题**/
	private String title;

	public Index() {
		super();
	}

	public Index(String id, String username, String identity_id, String phone_num, String iCCID, String timestamp,
			String src_ip, String des_ip, String src_port, String des_port, String protocol_type, String header,
			String url, String flow_type, String resource_type, String domain_name, String size, String content,
			String title) {
		super();
		this.id = id;
		this.username = username;
		this.identity_id = identity_id;
		this.phone_num = phone_num;
		ICCID = iCCID;
		this.timestamp = timestamp;
		this.src_ip = src_ip;
		this.des_ip = des_ip;
		this.src_port = src_port;
		this.des_port = des_port;
		this.protocol_type = protocol_type;
		this.header = header;
		this.url = url;
		this.flow_type = flow_type;
		this.resource_type = resource_type;
		this.domain_name = domain_name;
		this.size = size;
		this.content = content;
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIdentity_id() {
		return identity_id;
	}

	public void setIdentity_id(String identity_id) {
		this.identity_id = identity_id;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public String getICCID() {
		return ICCID;
	}

	public void setICCID(String iCCID) {
		ICCID = iCCID;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSrc_ip() {
		return src_ip;
	}

	public void setSrc_ip(String src_ip) {
		this.src_ip = src_ip;
	}

	public String getDes_ip() {
		return des_ip;
	}

	public void setDes_ip(String des_ip) {
		this.des_ip = des_ip;
	}

	public String getSrc_port() {
		return src_port;
	}

	public void setSrc_port(String src_port) {
		this.src_port = src_port;
	}

	public String getDes_port() {
		return des_port;
	}

	public void setDes_port(String des_port) {
		this.des_port = des_port;
	}

	public String getProtocol_type() {
		return protocol_type;
	}

	public void setProtocol_type(String protocol_type) {
		this.protocol_type = protocol_type;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFlow_type() {
		return flow_type;
	}

	public void setFlow_type(String flow_type) {
		this.flow_type = flow_type;
	}

	public String getResource_type() {
		return resource_type;
	}

	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}

	public String getDomain_name() {
		return domain_name;
	}

	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
