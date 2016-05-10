package com.froad.comon.util;  
  
import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.util.TimeZone;  

import org.apache.commons.net.ftp.FTPClient;  
import org.apache.commons.net.ftp.FTPClientConfig;  
import org.apache.commons.net.ftp.FTPFile;  
import org.apache.commons.net.ftp.FTPReply;  
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
  
public class FtpUtils {  
    private FTPClient ftpClient;  
    private String strIp;  
    private int intPort;  
    private String user;  
    private String password;  
    private Integer connectTimeout;
    private Integer scocketTimeout;
  
    private final static Logger logger = LoggerFactory.getLogger(FtpUtils.class);
    /* * 
     * Ftp构造函数 
     */  
    public FtpUtils(String strIp, int intPort, String user, String password) {  
        this.strIp = strIp;  
        this.intPort = intPort;  
        this.user = user;  
        this.password = password;  
        this.ftpClient = new FTPClient();  
        String connectTimeout=PropertyUtil.getProperties("downLoadConnectTimeout");
		String scocketTimeout=PropertyUtil.getProperties("downLoadSocketTimeout");
		this.connectTimeout=Integer.parseInt(connectTimeout);
		this.scocketTimeout=Integer.parseInt(scocketTimeout);
    }  
    
    public FtpUtils( String strIp, int intPort, String user,
			String password, Integer connectTimeout, Integer scocketTimeout) {
		this.strIp = strIp;
		this.intPort = intPort;
		this.user = user;
		this.password = password;
		  this.ftpClient = new FTPClient();  
		this.connectTimeout = connectTimeout;
		this.scocketTimeout = scocketTimeout;
	}

	/** 
     * @return 判断是否登入成功 
     * */  
    public boolean ftpLogin() {  
    	
        boolean isLogin = false;  
        FTPClientConfig ftpClientConfig = new FTPClientConfig();  
        ftpClientConfig.setServerTimeZoneId(TimeZone.getDefault().getID());  
        this.ftpClient.setControlEncoding("GBK");  
        this.ftpClient.configure(ftpClientConfig);  
        this.ftpClient.setConnectTimeout(this.connectTimeout);
        this.ftpClient.setDataTimeout(this.scocketTimeout);
        try {  
            if (this.intPort > 0) {  
                this.ftpClient.connect(this.strIp, this.intPort);  
            } else {  
                this.ftpClient.connect(this.strIp);  
            }  
            // FTP服务器连接回答  
            int reply = this.ftpClient.getReplyCode();  
            if (!FTPReply.isPositiveCompletion(reply)) {  
                this.ftpClient.disconnect();  
                logger.error("登录FTP服务失败！");  
                return isLogin;  
            }  
            this.ftpClient.login(this.user, this.password);  
            // 设置传输协议  
            this.ftpClient.enterLocalPassiveMode();  
            this.ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);  
            logger.info("恭喜" + this.user + "成功登陆FTP服务器");  
            isLogin = true;  
        } catch (Exception e) {  
            logger.error(this.user + "登录FTP服务失败！" + e.getMessage(),e);  
        }  
        this.ftpClient.setBufferSize(1024 * 2);  
        this.ftpClient.setDataTimeout(30 * 1000);  
        return isLogin;  
    }  
  
    /** 
     * @退出关闭服务器链接 
     * */  
    public void ftpLogOut() {  
        if (null != this.ftpClient && this.ftpClient.isConnected()) {  
            try {  
                boolean reuslt = this.ftpClient.logout();// 退出FTP服务器  
                if (reuslt) {  
                    logger.info("成功退出服务器");  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
                logger.warn("退出FTP服务器异常！" + e.getMessage());  
            } finally {  
                try {  
                    this.ftpClient.disconnect();// 关闭FTP服务器的连接  
                } catch (IOException e) {  
                    e.printStackTrace();  
                    logger.warn("关闭FTP服务器的连接异常！");  
                }  
            }  
        }  
    }  
  
    /*** 
     * 上传Ftp文件 
     * @param localFile 当地文件 
     * @param romotUpLoadePath上传服务器路径 - 应该以/结束 
     * */  
    public boolean uploadFile(File localFile, String romotUpLoadePath) {  
        BufferedInputStream inStream = null;  
        boolean success = false;  
        try {  
            this.ftpClient.changeWorkingDirectory(romotUpLoadePath);// 改变工作路径  
            inStream = new BufferedInputStream(new FileInputStream(localFile));  
            logger.info(localFile.getName() + "开始上传.....");  
            success = this.ftpClient.storeFile(localFile.getName(), inStream);  
            if (success == true) {  
                logger.info(localFile.getName() + "上传成功");  
                return success;  
            }  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
            logger.error(localFile + "未找到",e);  
        } catch (IOException e) {  
        	 logger.error("error", e);  
        } finally {  
            if (inStream != null) {  
                try {  
                    inStream.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return success;  
    }  
  
    /*** 
     * 下载文件 
     * @param remoteFileName   待下载文件名称 
     * @param localDires 下载到当地那个路径下 
     * @param remoteDownLoadPath remoteFileName所在的路径 
     * */  
  
    public boolean downloadFile(String remoteFileName, String localDires,  
            String remoteDownLoadPath) {  
        String strFilePath = localDires + remoteFileName;  
        BufferedOutputStream outStream = null;  
        boolean success = false;  
        try {  
            this.ftpClient.changeWorkingDirectory(remoteDownLoadPath);  
            outStream = new BufferedOutputStream(new FileOutputStream(  
                    strFilePath));  
            logger.info(remoteFileName + "开始下载....");  
            success = this.ftpClient.retrieveFile(remoteFileName, outStream);  
            if (success == true) {  
                logger.info(remoteFileName + "成功下载到" + strFilePath);  
                return success;  
            }  
        } catch (Exception e) {  
            logger.error(remoteFileName + "下载失败",e);  
        } finally {  
            if (null != outStream) {  
                try {  
                    outStream.flush();  
                    outStream.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        if (success == false) {  
            logger.error(remoteFileName + "下载失败!!!");  
        }  
        return success;  
    }  
    
    
    /*** 
     * 下载文件 
     * @param remoteFileName   待下载文件名称 
     * @param localDires 下载到当地那个路径下 
     * @param remoteDownLoadPath remoteFileName所在的路径 
     * */  
  
    public boolean downloadFile( String localPath,  
            String remoteDownLoadPath) {  
        BufferedOutputStream outStream = null;  
        boolean success = false;  
        try {  
        	File file=new File(localPath);
        	if(!file.getParentFile().exists()){
        		file.getParentFile().mkdirs();
        	}
            outStream = new BufferedOutputStream(new FileOutputStream(file));  
            logger.info(remoteDownLoadPath + "开始下载....");  
            success = this.ftpClient.retrieveFile(remoteDownLoadPath, outStream);  
            if (success == true) {  
                logger.info(remoteDownLoadPath + "成功下载到" + localPath);  
                return success;  
            }  
        } catch (Exception e) {  
            logger.error(remoteDownLoadPath + "下载失败",e);  
        } finally {  
            if (null != outStream) {  
                try {  
                    outStream.flush();  
                    outStream.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        if (success == false) {  
            logger.error(remoteDownLoadPath + "下载失败!!!");  
        }  
        return success;  
    } 
  
    /*** 
     * @上传文件夹 
     * @param localDirectory 
     *            当地文件夹 
     * @param remoteDirectoryPath 
     *            Ftp 服务器路径 以目录"/"结束 
     * */  
    public boolean uploadDirectory(String localDirectory,  
            String remoteDirectoryPath) {  
        File src = new File(localDirectory);  
        try {  
            remoteDirectoryPath = remoteDirectoryPath + src.getName() + "/";  
            this.ftpClient.makeDirectory(remoteDirectoryPath);  
            // ftpClient.listDirectories();  
        } catch (IOException e) {  
            e.printStackTrace();  
            logger.info(remoteDirectoryPath + "目录创建失败");  
        }  
        File[] allFile = src.listFiles();  
        for (int currentFile = 0; currentFile < allFile.length; currentFile++) {  
            if (!allFile[currentFile].isDirectory()) {  
                String srcName = allFile[currentFile].getPath().toString();  
                uploadFile(new File(srcName), remoteDirectoryPath);  
            }  
        }  
        for (int currentFile = 0; currentFile < allFile.length; currentFile++) {  
            if (allFile[currentFile].isDirectory()) {  
                // 递归  
                uploadDirectory(allFile[currentFile].getPath().toString(),  
                        remoteDirectoryPath);  
            }  
        }  
        return true;  
    }  
  
    /*** 
     * @下载文件夹 
     * @param localDirectoryPath本地地址 
     * @param remoteDirectory 远程文件夹 
     * */  
    public boolean downLoadDirectory(String localDirectoryPath,String remoteDirectory) {  
        try {  
            String fileName = new File(remoteDirectory).getName();  
            localDirectoryPath = localDirectoryPath + fileName + "//";  
            new File(localDirectoryPath).mkdirs();  
            FTPFile[] allFile = this.ftpClient.listFiles(remoteDirectory);  
            for (int currentFile = 0; currentFile < allFile.length; currentFile++) {  
                if (!allFile[currentFile].isDirectory()) {  
                    downloadFile(allFile[currentFile].getName(),localDirectoryPath, remoteDirectory);  
                }  
            }  
            for (int currentFile = 0; currentFile < allFile.length; currentFile++) {  
                if (allFile[currentFile].isDirectory()) {  
                    String strremoteDirectoryPath = remoteDirectory + "/"+ allFile[currentFile].getName();  
                    downLoadDirectory(localDirectoryPath,strremoteDirectoryPath);  
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
            logger.info("下载文件夹失败");  
            return false;  
        }  
        return true;  
    }  
    // FtpClient的Set 和 Get 函数  
    public FTPClient getFtpClient() {  
        return ftpClient;  
    }  
    public void setFtpClient(FTPClient ftpClient) {  
        this.ftpClient = ftpClient;  
    }  
      
    
    /**下载文件**/
    public static void downloadFile(FtpEntity entity){
    	FtpUtils ftp=new FtpUtils(entity.getIp(),entity.getPort(),entity.getUserName(),entity.getPassword());
    	ftp.ftpLogin();  
    	//下载文件夹  
    	ftp.downloadFile(entity.getLocalFileName(),entity.getRemoteFileName() );  
    	ftp.ftpLogOut();    
    }
    public static void main(String[] args) throws IOException {  
    	
    	File file=new File("E:\\abc.txt");
    	if(!file.getParentFile().exists()){
    		file.getParentFile().mkdirs();
    	}
    	BufferedOutputStream  outStream = new BufferedOutputStream(new FileOutputStream(file));  
    	
    	outStream.write(123);
    	
    	outStream.close();
    }  
    
    public static void main1(String[] args) {
    	String fileUrl="172.16.10.8;21;tech;froadbill#$;/000000006559/output/%s/POINT_ADJRPT_000000006559_%s";
    	fileUrl = String.format(fileUrl, "20150801", "20150801");
    	String[] ftps = fileUrl.split(";");
		FtpEntity entity = new FtpEntity();
        entity.setIp(ftps[0]);//IP
    	entity.setPort(Integer.parseInt(ftps[1]));//端口
    	entity.setUserName(ftps[2]);//用户名
        entity.setPassword(ftps[3]);//密码
    	entity.setRemoteFileName(ftps[4]);//FTP文件地址
        entity.setLocalFileName("E:\\abcd\\abc.txt");//下载到本地文件地址
    	
    	 FtpUtils ftp=new FtpUtils(entity.getIp(),entity.getPort(),entity.getUserName(),entity.getPassword(),60000,60000);
         ftp.ftpLogin();  
         //下载文件夹  
         ftp.downloadFile(entity.getLocalFileName(),entity.getRemoteFileName() );  
         ftp.ftpLogOut();    
	}
    
   
}  