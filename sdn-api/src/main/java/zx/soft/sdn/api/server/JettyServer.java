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
 * 启动Jetty
 * 
 * @author xuran
 *
 */
public class JettyServer {

	private static final Logger logger = LoggerFactory.getLogger(JettyServer.class);

	// 默认端口
	private static final int DEFAULT_PORT = 8888;
	// Context路径
	private static final String CONTEXT_PATH = "/";

	/**
	 * 主函数
	 */
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.load(JettyServer.class.getClassLoader().getResourceAsStream("api.properties"));
		new JettyServer().startJetty(Integer.valueOf(props.getProperty("api.port", String.valueOf(DEFAULT_PORT))));
	}

	private void startJetty(int port) throws Exception {
		logger.info("Starting server at port {}", port);
		Server server = new Server(port);
		server.setHandler(getWebAppContext());
		server.start();
		logger.info("Server started at port {}", port);
		server.join();
	}

	/**
	 * 从web.xml创建web上下文对象
	 * @return
	 * @throws IOException
	 */
	private static ServletContextHandler getWebAppContext() throws IOException {
		// Web内容上下文对象
		WebAppContext webAppContext = new WebAppContext();
		// Web目录
		String webDir = new ClassPathResource("webapp").getURI().toString();
		// 设置Web内容上下文路径  
		webAppContext.setResourceBase(webDir);
		// 设置Web.xml路径
		webAppContext.setDescriptor(webDir + "/WEB-INF/web.xml");
		// 设置上下文路径  
		webAppContext.setContextPath(CONTEXT_PATH);
		//父类优先加载
		webAppContext.setParentLoaderPriority(true);
		return webAppContext;
	}

}
