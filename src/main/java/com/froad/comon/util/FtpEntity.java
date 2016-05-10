package com.froad.comon.util;

/**
 * @author choky
 *         用途：根据数据库创建FTP实体，在下载时用
 */
public class FtpEntity {
    /**
     * 本地文件名
     */
    private String localFileName;
    /**
     * 远程文件名
     */
    private String remoteFileName;
    /**
     * FTP服务器IP
     */
    private String ip;
    /**
     * FTP服务器端口,默认21
     */
    private int port = 21;
    /**
     * FTP登录名
     */
    private String userName;
    /**
     * FTP密码
     */
    private String password;
    /**
     * 连接超时时间，默认10秒钟
     */
    private int connectionTimeout = 10000;
    /**
     * 读取超时时间，默认1分钟
     */
    private int readTimeout = 60000;

    public FtpEntity() {
        super();
    }

    /**
     * @param localFileName
     * @param remoteFileName
     * @param ip
     * @param userName
     * @param password
     */
    public FtpEntity(String localFileName, String remoteFileName, String ip, String userName, String password) {
        super();
        this.localFileName = localFileName;
        this.remoteFileName = remoteFileName;
        this.ip = ip;
        this.userName = userName;
        this.password = password;
    }

    public String getLocalFileName() {
        return localFileName;
    }

    public void setLocalFileName(String localFileName) {
        this.localFileName = localFileName;
    }

    public String getRemoteFileName() {
        return remoteFileName;
    }

    public void setRemoteFileName(String remoteFileName) {
        this.remoteFileName = remoteFileName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FtpEntity [connectionTimeout=");
        builder.append(connectionTimeout);
        builder.append(", ip=");
        builder.append(ip);
        builder.append(", localFileName=");
        builder.append(localFileName);
        builder.append(", password=");
        builder.append(password);
        builder.append(", port=");
        builder.append(port);
        builder.append(", readTimeout=");
        builder.append(readTimeout);
        builder.append(", remoteFileName=");
        builder.append(remoteFileName);
        builder.append(", userName=");
        builder.append(userName);
        builder.append("]");
        return builder.toString();
    }


}
