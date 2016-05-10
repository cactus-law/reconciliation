package com.froad.flow.service;

import com.froad.comon.util.FtpEntity;

/**
 * @author choky
 *         文件传输服务
 */
public interface FileService {
    /**
     * 获取银联和银联数据
     *
     * @param localname
     * @param dateStr8
     * @return
     * @throws Exception
     */
    public FtpEntity getFtpEntity(String localname, String dateStr8) throws Exception;

    /**
     * 积分和AS400获取文件保存地址
     *
     * @param localname        文件名常量
     * @param dateStr8         清算日期
     * @param downloadFilename 下载文件名
     * @return
     * @throws Exception
     */
    public String getFileName(String localname, String dateStr8, String downloadFilename) throws Exception;
}
