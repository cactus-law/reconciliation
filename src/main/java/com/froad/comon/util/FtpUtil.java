package com.froad.comon.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.net.TelnetInputStream;
import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;

/**
 * FTP下载工具类
 */
public class FtpUtil {
	
	final static Logger logger = LoggerFactory.getLogger(FtpUtil.class);
	
    /**
     * FTP客户端
     */
    private FtpClient ftpClient;
    /**
     * FTP实体
     */
    private FtpEntity ftpEntity;

    public FtpUtil(FtpEntity ftpEntity) {
        this.ftpEntity = ftpEntity;
    }

    /**
     * 服务器连接
     */
    public void connectServer() throws Exception {
        try {
            ftpClient = new FtpClient();
            ftpClient.openServer(ftpEntity.getIp(), ftpEntity.getPort());
            ftpClient.login(ftpEntity.getUserName(), ftpEntity.getPassword());
            ftpClient.setConnectTimeout(ftpEntity.getConnectionTimeout());
            ftpClient.setReadTimeout(ftpEntity.getReadTimeout());
            ftpClient.binary();
//            ftpClient.setReadTimeout(1000);
            logger.info("连接服务器成功：" + ftpEntity);
        } catch (IOException ex) {
            logger.error("连接服务器异常.[ex="+ex.getMessage()+",ftpClient="+ftpClient+"]", ex);
            throw ex;
        }
    }

    /**
     * 关闭连接
     */
    public void closeConnect() {
        try {
            ftpClient.closeServer();
        } catch (IOException ex) {
        	logger.error("连接未关闭.[ex="+ex.getMessage()+",ftpEntity="+ftpEntity+"]", ex);
        }
    }

    /**
     * 上传文件
     */
    public String upload() throws Exception {
        TelnetOutputStream os = null;
        FileInputStream is = null;
        String remoteFileName = ftpEntity.getRemoteFileName();
        String localFileName = ftpEntity.getLocalFileName();
        try {
            //将远程文件加入输出流中
            os = ftpClient.put(remoteFileName);
            
            //获取本地文件的输入流
            File file_in = new File(localFileName.substring(0, localFileName.lastIndexOf('/')));
            if (!file_in.exists()) {
            	file_in.mkdirs();
			}
            file_in = new File(localFileName);
            is = new FileInputStream(file_in);
            //创建一个缓冲区
            byte[] bytes = new byte[10240];
            int c;
            while ((c = is.read(bytes)) != -1) {
                os.write(bytes, 0, c);
            }
            logger.info("上传成功 ：" + ftpEntity);
            return remoteFileName;
        } catch (IOException ex) {
        	logger.error("上传文件失败.[ex="+ex.getMessage()+",ftpEntity="+ftpEntity+"]", ex);
            throw ex;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (os != null) {
                        os.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 下载文件
     */
    public String download() throws Exception {
        this.connectServer();
        TelnetInputStream is = null;
        FileOutputStream os = null;
        String remoteFileName = ftpEntity.getRemoteFileName();
        String localFileName = ftpEntity.getLocalFileName();
        try {
            is = ftpClient.get(remoteFileName);
            File file_in = new File(localFileName);
            if(!file_in.getParentFile().exists()){
            	file_in.getParentFile().mkdirs();
            }
            os = new FileOutputStream(file_in);
            byte[] bytes = new byte[1024];
            int c;
            while ((c = is.read(bytes)) != -1) {
                os.write(bytes, 0, c);
            }
            logger.info("文件下载成功.[ftpEntity={}]", ftpEntity);
            this.closeConnect();
            return localFileName;
        } catch (IOException ex) {
            logger.error("文件下载异常.[ex="+ex.getMessage()+"]", ex);
            throw ex;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (os != null) {
                        os.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main1(String agrs[]) throws Exception {
        FtpEntity entity = new FtpEntity();
        entity.setIp("173.4.159.10");
    	entity.setPort(21);
//        entity.setLocalFileName("/test/wuhan20150505.txt");
//        entity.setRemoteFileName("/bank_whns/20150505/wuhan20150505.txt");
    	entity.setLocalFileName("/data/ubank/config/web/reconciliation/data/20150505/20000001/wuhan20150505.txt");
    	entity.setRemoteFileName("/bank_whns/20150505/wuhan20150505.txt");
        entity.setUserName("ftpuser");
        entity.setPassword("ftpuser");
        FtpUtil fu = new FtpUtil(entity);
        fu.download();
    }
    
    public static void main(String[] args) throws Exception {
    	String fileUrl="172.16.10.8;21;tech;froadbill#$;/000000006559/output/%s/POINT_ADJRPT_000000006559_%s";
    	fileUrl = String.format(fileUrl, "20150801", "20150801");
    	String[] ftps = fileUrl.split(";");
		FtpEntity entity = new FtpEntity();
        entity.setIp(ftps[0]);//IP
    	entity.setPort(Integer.parseInt(ftps[1]));//端口
    	entity.setUserName(ftps[2]);//用户名
        entity.setPassword(ftps[3]);//密码
    	entity.setRemoteFileName(ftps[4]);//FTP文件地址
        entity.setLocalFileName("E:\\abc\\abc.txt");//下载到本地文件地址
        FtpUtil fu = new FtpUtil(entity);
        fu.download();
	}
}