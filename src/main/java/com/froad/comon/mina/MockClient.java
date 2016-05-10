package com.froad.comon.mina;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * <pre>
 *  socket调用 
 * </pre>
 *
 * @author xueyunlong
 * @create 2015年7月9日 下午7:29:12
 * @modify
 * @since   JDK1.6
 */
public class MockClient {
	private static final Log log = LogFactory.getLog(MockClient.class);

	private static ExecutorService filterExecutor = new OrderedThreadPoolExecutor();
	
	public static final String UTF8="UTF-8";
	public static final String GBK="GBK";
	
	public static String executeCommand(String address, String port, String message,String charsetName) {
		try {
			log.info("请求报文："+message);
			return MockClient.executeCommand(address, port, message.getBytes(charsetName),charsetName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * @Title executeCommand
	 * @desc 执行客户端命令(同步)
	 * @author chenxiangde
	 * @param String
	 *            serviceAddress, String port, byte[] message
	 * @return String 返回响应的消息
	 * @throws
	 */
	public static String executeCommand(String address, String port, byte[] message,String charsetName) {
		String msg = "命令执行出错!";
		// Create TCP/IP connector.
		IoConnector connector = new NioSocketConnector();
		// 连接服务器动作
		connector.setConnectTimeoutMillis(60000);
		connector.getSessionConfig().setUseReadOperation(true);
		// 创建接收数据的过滤器
		DefaultIoFilterChainBuilder chain = connector.getFilterChain();
		chain.addLast("threadPool", new ExecutorFilter(filterExecutor));
		chain.addLast("logger", new LoggingFilter());
		// 设定客户端的消息处理器
		connector.setHandler(new IOHander());
		try {
			int p = 0;
			try {
				p = Integer.parseInt(port);
			} catch (NumberFormatException e) {
				msg = "服务器通讯端口配置有误!";
				e.printStackTrace();
				log.info("服务器端口错误:" + e);
				throw new Exception("服务器端口错误!");
			}
			// 建立连接
			log.info("连接服务器IP:" + address + ",PORT:" + port);
			ConnectFuture cf = connector.connect(new InetSocketAddress(address, p));
			// 等待连接创建完成
			cf.awaitUninterruptibly();
			// 获取会话
			IoSession session = cf.getSession();
			// 进行消息传输
			WriteFuture writeFuture = session.write(IoBuffer.wrap(message));
			writeFuture.awaitUninterruptibly();
			log.info("等待发送完成!");
			// 判断消息是否发送完成
			if (writeFuture.isWritten()) {
				log.info("发送完成，开始读取数据");
				ReadFuture readFuture = session.read();
				// 等待消息响应
				readFuture.awaitUninterruptibly();
				log.info("响应成功后接受数据：");
				// 是否响应成功
				if (readFuture.isRead()) {
					// 获取响应消息
					IoBuffer ioBuffer = (IoBuffer) readFuture.getMessage();
					byte[] b = new byte[ioBuffer.limit()];
					ioBuffer.get(b);
					String receivedMessage = new String(b, charsetName);
					msg = receivedMessage;
					log.info("同步客户端响应消息:" + receivedMessage);
				}else{
					log.info("服务器无响应数据");
					throw new Exception("服务器无响应数据!");
				}
			}
		} catch (RuntimeIoException e) {
			msg = "服务器通讯失败!";
			e.printStackTrace();
			log.info("同步客户端执行命令异常!具体异常信息:" + e);
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("同步客户端执行命令异常!具体异常信息:" + e);
			throw new RuntimeException(e.getMessage());
		} finally {
			// 关闭客户端线程
			if (null != connector) {
				log.info("关闭同步客户端连接!");
				connector.dispose();
			}
		}
		return msg;
	}
	public static void main(String[] args) throws UnsupportedEncodingException {
		final String lenStr = "0000";
		//<reconType>0 : 全部 1：末对账</reconType>
		String message = "<id>1234</id><trancode>RECON</trancode><cleardate>20150708</cleardate><reconType>0</reconType>";
		String len = String.valueOf(message.toString().getBytes("UTF-8").length);
	    String msgHead = lenStr.substring(len.length()) + len;
		System.out.println(executeCommand("127.0.0.1", "5000", msgHead+message,UTF8));
	}
}
