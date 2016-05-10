package com.froad.comon.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SFTPUtils {

	final static Logger logger = LoggerFactory.getLogger(SFTPUtils.class);
	private Session session = null;
	private ChannelSftp channel = null;
	/**
	 * 连接sftp服务器
	 * @param host
	 *            主机
	 * @param port
	 *            端口
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @throws Exception 
	 */
	public void connect(String host, int port, String username,
			String password) throws Exception {
		try {
			String connectTimeout=PropertyUtil.getProperties("downLoadConnectTimeout");
			Integer  connectTimeoutInt=0;
			if(StringUtils.isNotEmpty(connectTimeout)){
				connectTimeoutInt=Integer.parseInt(connectTimeout);
			}else{
				connectTimeoutInt=1000*60*2;
			}
			
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			session= jsch.getSession(username, host, port);
			System.out.println("Session created.");
			session.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			session.setConfig(sshConfig);
			session.connect();
			session.setTimeout(connectTimeoutInt);
			System.out.println("Session connected.");
			System.out.println("Opening Channel.");
			Channel channel = session.openChannel("sftp");
			channel.connect();
			this.channel = (ChannelSftp) channel;
			System.out.println("Connected to " + host + ".");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw e;
		}
	}
	
	public void closeChannel()  {
		try {
			if (channel != null) {
				channel.disconnect();
			}
			if (session != null) {
				session.disconnect();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param directory
	 *            上传的目录
	 * @param uploadFile
	 *            要上传的文件
	 * @param sftp
	 * @throws Exception 
	 */
	public void upload(String directory, String uploadFile) throws Exception {
		try {
			channel.cd(directory);
			File file = new File(uploadFile);
			channel.put(new FileInputStream(file), file.getName());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw e;
		}
	}
	
	/**
	 * @param host ip地址
	 * @param port  端口号
	 * @param username  用户名
	 * @param password 密码
	 * @param directory 下载文件目录
	 * @param downloadFile 文件的名字
	 * @param saveFile  保存文件的全路径
	 * 下载文件
	 * @throws Exception
	 */
	public static void downloadFile(String host,Integer port,String username,String password,
			String downloadFile,
			String saveFile) throws Exception{
		SFTPUtils sf=new SFTPUtils();
		try{
			sf.connect(host, port, username, password);
			sf.download(downloadFile, saveFile);
		}finally{
			sf.closeChannel();
		}
	} 

	/**
	 * 下载文件
	 * 
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件
	 * @param saveFile
	 *            存在本地的路径
	 * @param sftp
	 * @throws Exception 
	 */
	public void download( String downloadFile,
			String saveFile) throws Exception {
		logger.info("sftp服务器下载文件全路径："+downloadFile);
		logger.info("保存文件地址："+saveFile);
		try {
			File file = new File(saveFile);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			channel.get(downloadFile, new FileOutputStream(file));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw e;
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param directory
	 *            要删除文件所在目录
	 * @param deleteFile
	 *            要删除的文件
	 * @param sftp
	 * @throws Exception 
	 */
	public void delete(String directory, String deleteFile) throws Exception {
		try {
			channel.cd(directory);
			channel.rm(deleteFile);
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw e;
		} 
	}

	/**
	 * 列出目录下的文件
	 * 
	 * @param directory
	 *            要列出的目录
	 * @param sftp
	 * @return
	 * @throws Exception 
	 * @throws SftpException
	 */
	public Vector listFiles(String directory) throws Exception{
		try {
			return channel.ls(directory);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw e;
		} 
	}

	public static void main(String[] args) throws Exception {
		String host = "10.43.1.138";
		int port = 22;
		String username = "sqyh";
		String password = "PgiXdrlKO1Lxj7nVE1tv";
//		String directory = "/data/ubank/sqyh/";
		String downloadFile = "/data/ubank/sqyh/debuginf.txt";
		String saveFile = "D:\\tmp\\debuginfo.txt";
		downloadFile(host, port, username, password, downloadFile, saveFile);
	}
}
