package zx.soft.sdn.api.server;

import java.io.IOException;
import java.util.Properties;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Jetty服务
 * 
 * @author xuran
 *
 */
public class JettyServer {

	private static Logger logger = LoggerFactory.getLogger(JettyServer.class);

	// 默认端口
	private static final int PORT = 8888;
	// 默认上下文路径
	private static final String CONTEXT_PATH = "/";

	/**
	 * 主函数
	 */
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.load(JettyServer.class.getClassLoader().getResourceAsStream("api.properties"));
		new JettyServer().startJetty(Integer.valueOf(props.getProperty("api.port", String.valueOf(PORT))),
				props.getProperty("api.context.path", CONTEXT_PATH));
	}

	/**
	 * 启动Jetty
	 * @param port 端口
	 * @param contextPath 根
	 * @throws Exception
	 */
	private void startJetty(int port, String contextPath) throws Exception {
		logger.info("Starting server at port {} at context path {}", port, contextPath);
		Server server = new Server(port);
		server.setHandler(getWebAppContext(contextPath));
		server.start();
		logger.info("Server started at port {} at context path {}", port, contextPath);
		server.join();
	}
	
	/**
	 * 从web.xml创建web上下文对象
	 * @param contextPath 上下文路径
	 * @return Servlet上下文处理信息
	 * @throws IOException
	 */
	private static ServletContextHandler getWebAppContext(String contextPath) throws IOException {
		// Web内容上下文对象
		WebAppContext webAppContext = new WebAppContext();
		// Web目录
		String webDir = new ClassPathResource("webapp").getURI().toString();
		// 设置Web内容上下文路径  
		webAppContext.setResourceBase(webDir);
		// 设置Web.xml路径
		webAppContext.setDescriptor(webDir + "/WEB-INF/web.xml");
		// 设置上下文路径  
		webAppContext.setContextPath(contextPath);
		//父类优先加载
		webAppContext.setParentLoaderPriority(true);
		return webAppContext;
	}

}
